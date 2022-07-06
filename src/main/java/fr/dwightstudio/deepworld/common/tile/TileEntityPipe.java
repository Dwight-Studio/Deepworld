package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TileEntityPipe extends BlockEntity implements BlockEntityTicker {

    public TileEntityPipe(BlockPos blockPos, BlockState blockState) {
        super(DeepworldTileEntities.PIPE, blockPos, blockState);
    }

    // Convert NBT to internal vars
    @Override
    public void load(@NotNull CompoundTag compound) {

        super.load(compound); // The super call is required to load the tiles location
    }

    // Convert internal vars to NBT
    @Override
    public void saveAdditional(@NotNull CompoundTag compound) {
        super.saveAdditional(compound); // The super call is required to save the tiles location
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
        this.saveAdditional(compound);

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
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    // Update BlockState
    /*private void updateBlockState() {

        BlockState currentBlockState = this.level.getBlockState(this.worldPosition);
        BlockState newBlockState = currentBlockState;

        if (!newBlockState.equals(currentBlockState)) {
            this.level.setBlocksDirty(this.worldPosition, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 *//*SEND_TO_CLIENT*//* | Constants.BlockFlags.RERENDER_MAIN_THREAD);
        }
    }*/

    @Override
    public void tick(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull BlockEntity blockEntity) {

    }
}
