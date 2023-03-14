package fr.dwightstudio.deepworld.blockentities.frames;

import fr.dwightstudio.deepworld.block.frames.DeepworldFrame;
import fr.dwightstudio.deepworld.block.frames.DeepworldWoodenFrame;
import fr.dwightstudio.deepworld.blockentities.DeepworldEntities;
import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.block.Block.*;

public class WoodenFrameBlockEntity extends BlockEntity {

    // TileEntity vars
    private int covers = 0;
    private int primaryComponent = 0;
    private int secondaryComponent = 0;
    private int tertiaryComponent = 0;

    public WoodenFrameBlockEntity(BlockPos pos, BlockState state) {
        super(DeepworldEntities.WOODEN_FRAME, pos, state);
    }

    // Convert NBT to internal vars
    @Override
    public void readNbt(@NotNull NbtCompound compound){
        super.readNbt(compound);

        covers = compound.getInt("Cover");
        primaryComponent = compound.getInt("PrimaryComponent");
        secondaryComponent = compound.getInt("SecondaryComponent");
        tertiaryComponent = compound.getInt("TertiaryComponent");
    }

    // Convert internal vars to NBT
    @Override
    public void writeNbt(@NotNull NbtCompound compound) {
        compound.putInt("cover", covers);
        compound.putInt("PrimaryComponent", primaryComponent);
        compound.putInt("SecondaryComponent", secondaryComponent);
        compound.putInt("TertiaryComponent", tertiaryComponent);
        super.writeNbt(compound);
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
        assert this.world != null;
        BlockState currentBlockState = this.world.getBlockState(this.pos);
        BlockState newBlockState = currentBlockState
                .with(DeepworldFrame.COVER, covers)
                .with(DeepworldWoodenFrame.PRIMARY_COMPONENT, primaryComponent)
                .with(DeepworldWoodenFrame.SECONDARY_COMPONENT, secondaryComponent)
                .with(DeepworldWoodenFrame.TERTIARY_COMPONENT, tertiaryComponent);
        if (!newBlockState.equals(currentBlockState)) {
            this.world.setBlockState(this.pos, newBlockState, NOTIFY_ALL, NOTIFY_LISTENERS);
        }
    }

    // Apply craft
    private void applyCraft() {
        Block result = DeepworldWoodenFrameComponent.getResultFromTile(this);
        assert result != null;
        BlockState state = result.getDefaultState();

        if (state.getProperties().contains(HorizontalFacingBlock.FACING)) {
            assert this.world != null;
            state = state.with(HorizontalFacingBlock.FACING, this.world.getBlockState(this.pos).get(DeepworldFrame.FACING));
        }

        assert this.world != null;
        this.world.setBlockState(this.pos, state, NOTIFY_ALL, NOTIFY_LISTENERS);
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

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    private void sendUpdate() {
        assert world != null;
        markDirty();
        world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
    }

}
