package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockFrame extends Block {

    // Block property initializing
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final IProperty<Boolean> BOTTOM = PropertyBool.create("bottom");
    public static final IProperty<Boolean> TOP = PropertyBool.create("top");
    public static final IProperty<Boolean> FRONT = PropertyBool.create("front");
    public static final IProperty<Boolean> BACK = PropertyBool.create("back");
    public static final IProperty<Boolean> LEFT = PropertyBool.create("left");
    public static final IProperty<Boolean> RIGHT = PropertyBool.create("right");

    // Constructor
    public BlockFrame(Material material) {
        super(material);
        this.setCreativeTab(Deepworld.creativeTab);

        this.setDefaultState(
                this.setComponentPropertiesDefaultValues(this.getBlockState().getBaseState()
                        .withProperty(FACING, EnumFacing.NORTH)
                        .withProperty(BOTTOM, false)
                        .withProperty(TOP, false)
                        .withProperty(FRONT, false)
                        .withProperty(BACK, false)
                        .withProperty(LEFT, false)
                        .withProperty(RIGHT, false)
        ));
    }

    // Override this method to add new proterties
    protected IProperty[] getComponentsProperties() {
        return new IProperty[] {};
    }

    // Override this method to add default properties values
    protected IBlockState setComponentPropertiesDefaultValues(IBlockState state) {
        return state;
    }

    // Block state creation (registering properties)
    @Override
    protected final BlockStateContainer createBlockState() {
        List<IProperty> list = new ArrayList<IProperty>();

        list.add(FACING);
        list.add(BOTTOM);
        list.add(TOP);
        list.add(FRONT);
        list.add(BACK);
        list.add(LEFT);
        list.add(RIGHT);

        for (IProperty property : getComponentsProperties()) {
            list.add(property);
        }

        return new BlockStateContainer(this, list.toArray(new IProperty[list.size()]));
    }

    /*
     * Those functions are required but may be optional if
     * block state is saved in a Tile Entity.
     * (functions must be declared anyway to avoid crashing but
     * can just return default state & 0)
     *
     * Here, only the facing property is saved within the metadata
     */
    @Override
    public final IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.NORTH;

        switch (meta) {
            case 0:
                facing = EnumFacing.NORTH;
                break;
            case 1:
                facing = EnumFacing.EAST;
                break;
            case 2:
                facing = EnumFacing.SOUTH;
                break;
            case 3:
                facing = EnumFacing.WEST;
                break;
        }

        IBlockState state = this.getDefaultState()
                .withProperty(FACING, facing);

        return state;
    }

    @Override
    public final int getMetaFromState(IBlockState state) {
        int meta = 0;

        switch (state.getValue(FACING)) {
            case NORTH:
                meta = 0;
                break;
            case EAST:
                meta = 1;
                break;
            case SOUTH:
                meta = 2;
                break;
            case WEST:
                meta = 3;
                break;
        }

        return meta;
    }

    // Override default methode to delay block deletion (to run get drops before block deletion)
    @Override
    public final boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }
    @Override
    public final void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack tool)
    {
        super.harvestBlock(world, player, pos, state, te, tool);
        world.setBlockToAir(pos);
    }

    // Setting visual block properties
    @Override
    @Deprecated
    public final boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    // Allow transparency
    @Override
    public final BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    // Deny torch placing
    @Override
    public final boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    // Notify block update when activated
    @Override
    public final boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
        world.markBlockRangeForRenderUpdate(pos, pos);
        return true;
    }

    // Enable directional placement
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
}
