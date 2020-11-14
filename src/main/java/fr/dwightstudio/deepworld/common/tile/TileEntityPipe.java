package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityPipe extends TileEntity implements ITickableTileEntity {

    public TileEntityPipe() {
        super(DeepworldTileEntities.PIPE);
    }

    // Convert NBT to internal vars
    @Override
    public void read(CompoundNBT compound) {

        super.read(compound); // The super call is required to load the tiles location
    }

    // Convert internal vars to NBT
    @Override
    public CompoundNBT write(CompoundNBT compound) {

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
    public CompoundNBT  getUpdateTag() {

        CompoundNBT  compound = new CompoundNBT();
        write(compound);

        return compound;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound) {
        this.read(compound);

        if (world != null) world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return new SUpdateTileEntityPacket(this.pos, 0, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
    }

    // Update BlockState
    private void updateBlockState() {

        BlockState currentBlockState = world.getBlockState(this.pos);
        BlockState newBlockState = currentBlockState;

        if (!newBlockState.equals(currentBlockState)) {
            world.setBlockState(this.pos, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);
            markDirty();
        }
    }

    @Override
    public void tick() {

    }
}
