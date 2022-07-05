package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import fr.dwightstudio.deepworld.common.block.BlockFrame;
import fr.dwightstudio.deepworld.common.block.BlockWoodenFrame;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityWoodenFrame extends BlockEntity {

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
    public void read(CompoundTag compound) {

        super.read(compound); // The super call is required to load the tiles location

        covers = compound.getInt("Cover");
        primaryComponent = compound.getInt("PrimaryComponent");
        secondaryComponent = compound.getInt("SecondaryComponent");
        tertiaryComponent = compound.getInt("TertiaryComponent");
    }

    // Convert internal vars to NBT
    @Override
    public CompoundTag write(CompoundTag compound) {

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

        BlockState currentBlockState = this.level.getBlockState(this.worldPosition);
        BlockState newBlockState = currentBlockState
                .with(BlockFrame.COVER, covers)
                .with(BlockWoodenFrame.PRIMARY_COMPONENT, primaryComponent)
                .with(BlockWoodenFrame.SECONDARY_COMPONENT, secondaryComponent)
                .with(BlockWoodenFrame.TERTIARY_COMPONENT, tertiaryComponent);
        if (!newBlockState.equals(currentBlockState)) {
            this.level.setBlocksDirty(this.worldPosition, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);
        }
    }

    // Apply craft
    private void ApplyCraft() {
        Block result = WoodenFrameComponent.getResultFromTile(this);
        BlockState state = result.getDefaultState();

        if (state.getProperties().contains(HorizontalDirectionalBlock.FACING)) {
            state = state.setValue(HorizontalDirectionalBlock.FACING, this.level.getBlockState(this.worldPosition).getValue(BlockFrame.FACING));
        }

        this.level.setBlockAndUpdate(this.worldPosition, state);

        this.level.setBlocksDirty(this.worldPosition, state, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);
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
