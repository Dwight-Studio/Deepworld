package fr.dwightstudio.deepworld.common.blockentity;

import fr.dwightstudio.deepworld.common.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.block.FrameBlock;
import fr.dwightstudio.deepworld.common.block.WoodenFrameBlock;
import fr.dwightstudio.deepworld.common.block.component.WoodenFrameComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WoodenFrameBlockEntity extends BlockEntity {

    // TileEntity vars
    private int covers = 0;

    private int primaryComponent = 0;
    private int secondaryComponent = 0;
    private int tertiaryComponent = 0;

    public WoodenFrameBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_FRAME.get(), blockPos, blockState);
    }

    // Convert NBT to internal vars
    @Override
    public void load(@NotNull CompoundTag compound) {

        super.load(compound); // The super call is required to load the tiles location

        covers = compound.getInt("Cover");
        primaryComponent = compound.getInt("PrimaryComponent");
        secondaryComponent = compound.getInt("SecondaryComponent");
        tertiaryComponent = compound.getInt("TertiaryComponent");
    }

    // Convert internal vars to NBT
    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {

        super.saveAdditional(compound); // The super call is required to save the tiles location

        compound.putInt("cover", covers);

        compound.putInt("PrimaryComponent", primaryComponent);
        compound.putInt("SecondaryComponent", secondaryComponent);
        compound.putInt("TertiaryComponent", tertiaryComponent);
    }

    /*
     * Network handling
     *
     * getUpdatePacket() and onDataPacket() are used for one-at-a-time TileEntity updates
     * getUpdateTag() and handleUpdateTag() are used by vanilla to collate together into a single chunk update packet
     */
    @Override
    public CompoundTag  getUpdateTag() {

        CompoundTag  compound = new CompoundTag();
        saveAdditional(compound);

        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundTag compound) {
        this.load(compound);

        if (this.level != null) this.level.markAndNotifyBlock(this.worldPosition, this.level.getChunkAt(this.worldPosition), this.level.getBlockState(this.worldPosition), this.getBlockState(), Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        saveAdditional(Objects.requireNonNull(pkt.getTag()));
    }

    // Get placed covers
    public int getCovers() {
        return covers;
    }

    // Place a cover
    public boolean addCover() {
        if (covers >= 0 && covers < 6) {
            covers++;
        } else {
            return false;
        }

        if (covers == 6) {
            ApplyCraft();
        } else {
            updateBlockState();
        }
        return true;
    }

    // Update BlockState
    private void updateBlockState() {

        assert this.level != null;
        BlockState currentBlockState = this.level.getBlockState(this.worldPosition);
        BlockState newBlockState = currentBlockState
                .setValue(FrameBlock.COVER, covers)
                .setValue(WoodenFrameBlock.PRIMARY_COMPONENT, primaryComponent)
                .setValue(WoodenFrameBlock.SECONDARY_COMPONENT, secondaryComponent)
                .setValue(WoodenFrameBlock.TERTIARY_COMPONENT, tertiaryComponent);
        if (!newBlockState.equals(currentBlockState)) {
            this.level.setBlocksDirty(this.worldPosition, this.level.getBlockState(this.worldPosition), newBlockState);
        }
    }

    // Apply craft
    private void ApplyCraft() {
        Block result = WoodenFrameComponent.getResultFromTile(this);
        BlockState state = result.defaultBlockState();

        if (state.getProperties().contains(HorizontalDirectionalBlock.FACING)) {
            assert this.level != null;
            state = state.setValue(HorizontalDirectionalBlock.FACING, this.level.getBlockState(this.worldPosition).getValue(FrameBlock.FACING));
        }

        assert this.level != null;
        this.level.setBlockAndUpdate(this.worldPosition, state);

        this.level.setBlocksDirty(this.worldPosition, this.level.getBlockState(this.worldPosition), state);
    }


    // Component management (getters and setters)
    public int getPrimaryComponent() {
        return primaryComponent;
    }

    public void setPrimaryComponent(int primaryComponent) {
        this.primaryComponent = primaryComponent;
        updateBlockState();
    }

    public int getSecondaryComponent() {
        return secondaryComponent;
    }

    public void setSecondaryComponent(int secondaryComponent) {
        this.secondaryComponent = secondaryComponent;
        updateBlockState();
    }

    public int getTertiaryComponent() {
        return tertiaryComponent;
    }

    public void setTertiaryComponent(int tertiaryComponent) {
        this.tertiaryComponent = tertiaryComponent;
        updateBlockState();
    }
}
