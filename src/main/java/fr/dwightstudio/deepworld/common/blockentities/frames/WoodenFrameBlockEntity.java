package fr.dwightstudio.deepworld.common.blockentities.frames;

import fr.dwightstudio.deepworld.common.registries.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.blocks.frames.FrameBlock;
import fr.dwightstudio.deepworld.common.blocks.frames.WoodenFrameBlock;
import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
            applyCraft();
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
            this.level.setBlock(this.worldPosition, newBlockState, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
        }
    }

    // Apply craft
    private void applyCraft() {
        Block result = WoodenFrameComponent.getResultFromTile(this);
        BlockState state = result.defaultBlockState();

        if (state.getProperties().contains(HorizontalDirectionalBlock.FACING)) {
            assert this.level != null;
            state = state.setValue(HorizontalDirectionalBlock.FACING, this.level.getBlockState(this.worldPosition).getValue(FrameBlock.FACING));
        }


        this.level.setBlock(this.worldPosition, state, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
        sendUpdate();
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

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
    }

    private void sendUpdate() {
        setChanged();
        getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 512);
    }

}
