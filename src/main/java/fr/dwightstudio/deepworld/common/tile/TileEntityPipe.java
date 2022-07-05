package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityPipe extends BlockEntity implements BlockEntityTicker {

    public TileEntityPipe() {
        super(DeepworldTileEntities.PIPE);
    }

    // Convert NBT to internal vars
    @Override
    public void read(CompoundTag compound) {

        super.read(compound); // The super call is required to load the tiles location
    }

    // Convert internal vars to NBT
    @Override
    public CompoundTag write(CompoundTag compound) {

        super.write(compound); // The super call is required to save the tiles location
        return compound;
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
        write(compound);

        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundTag compound) {
        this.read(compound);

        if (this.level != null) this.level.notifyBlockUpdate(this.worldPosition, this.level.getBlockState(this.worldPosition), this.level.getBlockState(this.worldPosition), Constants.BlockFlags.DEFAULT);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        CompoundTag nbtTagCompound = new CompoundTag();
        write(nbtTagCompound);
        return new SUpdateTileEntityPacket(this.worldPosition, 0, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
    }

    // Update BlockState
    private void updateBlockState() {

        BlockState currentBlockState = this.level.getBlockState(this.worldPosition);
        BlockState newBlockState = currentBlockState;

        if (!newBlockState.equals(currentBlockState)) {
            this.level.setBlocksDirty(this.worldPosition, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);
        }
    }

    @Override
    public void tick() {

    }
}
