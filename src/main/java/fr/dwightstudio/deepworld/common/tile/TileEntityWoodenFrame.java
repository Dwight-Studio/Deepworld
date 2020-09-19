package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import fr.dwightstudio.deepworld.common.block.BlockFrame;
import fr.dwightstudio.deepworld.common.block.BlockWoodenFrame;
import fr.dwightstudio.deepworld.common.block.BlockWoodenPress;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import javafx.geometry.HorizontalDirection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.util.Constants;

public class TileEntityWoodenFrame extends TileEntity {

    // TileEntity vars
    private int covers = 0;

    private int primaryComponent = 0;
    private int secondaryComponent = 0;
    private int tertiaryComponent = 0;

    public TileEntityWoodenFrame() {
        super(DeepworldTileEntities.WOODEN_FRAME);
    }

    // Convert NBT to internal vars
    @Override
    public void read(CompoundNBT compound) {

        super.read(compound); // The super call is required to load the tiles location

        covers = compound.getInt("Cover");
        primaryComponent = compound.getInt("PrimaryComponent");
        secondaryComponent = compound.getInt("SecondaryComponent");
        tertiaryComponent = compound.getInt("TertiaryComponent");
    }

    // Convert internal vars to NBT
    @Override
    public CompoundNBT write(CompoundNBT compound) {

        super.write(compound); // The super call is required to save the tiles location

        compound.putInt("cover", covers);

        compound.putInt("PrimaryComponent", primaryComponent);
        compound.putInt("SecondaryComponent", secondaryComponent);
        compound.putInt("TertiaryComponent", tertiaryComponent);

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

        BlockState currentBlockState = world.getBlockState(this.pos);
        BlockState newBlockState = currentBlockState
                .with(BlockFrame.COVER, covers)
                .with(BlockWoodenFrame.PRIMARY_COMPONENT, primaryComponent)
                .with(BlockWoodenFrame.SECONDARY_COMPONENT, secondaryComponent)
                .with(BlockWoodenFrame.TERTIARY_COMPONENT, tertiaryComponent);
        if (!newBlockState.equals(currentBlockState)) {
            world.setBlockState(this.pos, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);
            markDirty();
        }
    }

    // Apply craft
    private void ApplyCraft() {
        Block result = WoodenFrameComponent.getResultFromTile(this);
        BlockState state = result.getDefaultState();

        if (state.getProperties().contains(HorizontalBlock.HORIZONTAL_FACING)) {
            state = state.with(HorizontalBlock.HORIZONTAL_FACING, world.getBlockState(pos).get(BlockFrame.FACING));
        }

        world.setBlockState(this.pos, state);

        world.setBlockState(this.pos, state, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);
        markDirty();
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
