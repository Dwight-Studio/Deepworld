package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.block.BlockWoodenPress;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;

public class TileEntityWoodenFrame extends TileEntity implements ITickable {

    // TileEntity vars
    private boolean bottomCover = false;
    private boolean topCover = false;
    private boolean frontCover = false;
    private boolean backCover = false;
    private boolean leftCover = false;
    private boolean rightCover = false;

    private int primaryComponent = 0;
    private int secondaryComponent = 0;
    private int tertiaryComponent = 0;

    // Convert NBT to internal vars
    @Override
    public void readFromNBT(NBTTagCompound compound) {

        super.readFromNBT(compound); // The super call is required to load the tiles location
        
        if (compound.hasKey("BottomCover")) {
            bottomCover = compound.getBoolean("BottomCover");
        }

        if (compound.hasKey("TopCover")) {
            topCover = compound.getBoolean("TopCover");
        }

        if (compound.hasKey("FrontCover")) {
            frontCover = compound.getBoolean("FrontCover");
        }
        if (compound.hasKey("BackCover")) {
            backCover = compound.getBoolean("BackCover");
        }
        if (compound.hasKey("LeftCover")) {
            leftCover = compound.getBoolean("LeftCover");
        }

        if (compound.hasKey("RightCover")) {
            rightCover = compound.getBoolean("RightCover");
        }

        if (compound.hasKey("PrimaryComponent")) {
            primaryComponent = compound.getInteger("PrimaryComponent");
        }

        if (compound.hasKey("SecondaryComponent")) {
            secondaryComponent = compound.getInteger("SecondaryComponent");
        }

        if (compound.hasKey("TertiaryComponent")) {
            tertiaryComponent = compound.getInteger("TertiaryComponent");
        }

    }

    // Convert internal vars to NBT
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound); // The super call is required to save the tiles location

        compound.setBoolean("BottomCover", bottomCover);
        compound.setBoolean("TopCover", topCover);
        compound.setBoolean("FrontCover", frontCover);
        compound.setBoolean("BackCover", backCover);
        compound.setBoolean("LeftCover", leftCover);
        compound.setBoolean("RightCover", rightCover);

        compound.setInteger("PrimaryComponent", primaryComponent);
        compound.setInteger("SecondaryComponent", secondaryComponent);
        compound.setInteger("TertiaryComponent", tertiaryComponent);

        return compound;
    }

    /*
     * Network handling
     *
     * getUpdatePacket() and onDataPacket() are used for one-at-a-time TileEntity updates
     * getUpdateTag() and handleUpdateTag() are used by vanilla to collate together into a single chunk update packet
     */
    @Override
    public NBTTagCompound getUpdateTag() {

        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);

        return compound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound compound) {
        this.readFromNBT(compound);
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    // Get placed covers
    public boolean[] getCovers() {

        boolean[] covers = new boolean[6];

        covers[0] = bottomCover;
        covers[1] = topCover;
        covers[2] = frontCover;
        covers[3] = backCover;
        covers[4] = leftCover;
        covers[5] = rightCover;

        return covers;
    }

    // Place a cover
    public boolean addCover() {
        if (!bottomCover) {
            bottomCover = true;
        } else if (!topCover) {
            topCover = true;
        } else if (!leftCover) {
            leftCover = true;
        } else if (!backCover) {
            backCover = true;
        } else if (!rightCover) {
            rightCover = true;
        } else if (!frontCover) {
            frontCover = true;
        } else {
            return false;
        }
        this.markDirty();
        this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
        return true;
    }

    // Update method (ran every tick)
    @Override
    public void update() {
        world.markBlockRangeForRenderUpdate(pos, pos);

        Boolean complete = bottomCover && topCover && frontCover && backCover && rightCover && leftCover;
        Block machineBlock = getMachineBlock();

        // Check if valid and set the block
        if (machineBlock != null && complete) {
            world.setBlockState(pos, machineBlock.getDefaultState().withProperty(BlockWoodenPress.FACING, world.getBlockState(pos).getValue(BlockWoodenPress.FACING)));
        }
    }


    // Component management (getters and setters)
    public int getPrimaryComponent() {
        return primaryComponent;
    }

    public void setPrimaryComponent(int primaryComponent) {
        this.markDirty();
        this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
        this.primaryComponent = primaryComponent;
    }

    public int getSecondaryComponent() {
        return secondaryComponent;
    }

    public void setSecondaryComponent(int secondaryComponent) {
        this.markDirty();
        this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
        this.secondaryComponent = secondaryComponent;
    }

    public int getTertiaryComponent() {
        return tertiaryComponent;
    }

    public void setTertiaryComponent(int tertiaryComponent) {
        this.markDirty();
        this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
        this.tertiaryComponent = tertiaryComponent;
    }

    // Check if the assembly of the components is coherent
    public Block getMachineBlock() {
        for (Block primaryComponentBlock : WoodenFrameComponent.getByID(primaryComponent).getMachineBlocks()) {
            for (Block secondaryComponentBlock : WoodenFrameComponent.getByID(secondaryComponent).getMachineBlocks()) {
                for (Block tertiaryComponentBlock : WoodenFrameComponent.getByID(tertiaryComponent).getMachineBlocks()) {
                    if (primaryComponentBlock == secondaryComponentBlock && secondaryComponentBlock == tertiaryComponentBlock) {
                        return primaryComponentBlock;
                    }
                }
            }
        }

        return null;
    }
}
