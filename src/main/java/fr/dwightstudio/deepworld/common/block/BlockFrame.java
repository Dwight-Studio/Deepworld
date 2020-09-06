package fr.dwightstudio.deepworld.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockFrame extends Block {

    // Block property initializing
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final IProperty<Boolean> BOTTOM = BooleanProperty.create("bottom");
    public static final IProperty<Boolean> TOP = BooleanProperty.create("top");
    public static final IProperty<Boolean> FRONT = BooleanProperty.create("front");
    public static final IProperty<Boolean> BACK = BooleanProperty.create("back");
    public static final IProperty<Boolean> LEFT = BooleanProperty.create("left");
    public static final IProperty<Boolean> RIGHT = BooleanProperty.create("right");

    // Constructor
    public BlockFrame(Block.Properties properties) {
        super(properties);

        this.setDefaultState(setComponentPropertiesDefaultValues(this.getStateContainer().getBaseState()
                        .with(FACING, Direction.NORTH)
                        .with(BOTTOM, false)
                        .with(TOP, false)
                        .with(FRONT, false)
                        .with(BACK, false)
                        .with(LEFT, false)
                        .with(RIGHT, false)
        ));
    }

    // Override this method to add new properties
    protected IProperty<?>[] getComponentsProperties() {
        return new IProperty[] {};
    }

    // Override this method to add default properties values
    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state;
    }

    // Block state creation (registering properties)
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        List<IProperty<?>> list = new ArrayList<IProperty<?>>();

        list.add(FACING);
        list.add(BOTTOM);
        list.add(TOP);
        list.add(FRONT);
        list.add(BACK);
        list.add(LEFT);
        list.add(RIGHT);

        Collections.addAll(list, getComponentsProperties());

        builder.add(list.toArray(new IProperty[0]));
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
