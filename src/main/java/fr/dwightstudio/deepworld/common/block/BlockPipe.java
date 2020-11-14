package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.tile.TileEntityPipe;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class BlockPipe extends SixWayBlock implements ITileEntityProvider, IFluidHandler {

    // Block property initializing
    public static final BooleanProperty JUNCTION0 = BooleanProperty.create("junction0");
    public static final BooleanProperty JUNCTION1 = BooleanProperty.create("junction1");

    // Block state creation (registering properties)
    @Override
    final protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NORTH);
        builder.add(EAST);
        builder.add(SOUTH);
        builder.add(WEST);
        builder.add(UP);
        builder.add(DOWN);
        builder.add(JUNCTION0);
        builder.add(JUNCTION1);
    }

    // Constructor
    public BlockPipe() {
        super(0.3125F,
                Block.Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(3, 2));

        this.setDefaultState(this.getStateContainer().getBaseState()
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
                .with(JUNCTION0, false)
                .with(JUNCTION1, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.makeConnections(context.getWorld(), context.getPos());
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), facingState.getBlock() instanceof IFluidHandler);
    }

    // Assign the TileEntity
    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new TileEntityPipe();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockState makeConnections(IBlockReader blockReader, BlockPos pos) {
        Block blockDown = blockReader.getBlockState(pos.down()).getBlock();
        Block blockUp = blockReader.getBlockState(pos.up()).getBlock();
        Block blockNorth = blockReader.getBlockState(pos.north()).getBlock();
        Block blockEast = blockReader.getBlockState(pos.east()).getBlock();
        Block blockSouth = blockReader.getBlockState(pos.south()).getBlock();
        Block blockWest = blockReader.getBlockState(pos.west()).getBlock();
        
        return this.getDefaultState().with(DOWN, blockDown instanceof IFluidHandler)
                .with(UP, blockUp instanceof IFluidHandler)
                .with(NORTH, blockNorth instanceof IFluidHandler)
                .with(EAST, blockEast instanceof IFluidHandler)
                .with(SOUTH, blockSouth instanceof IFluidHandler)
                .with(WEST, blockWest instanceof IFluidHandler);
    }

    @Override
    public int getTanks() {
        return 0;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return null;
    }

    @Override
    public int getTankCapacity(int tank) {
        return 0;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return false;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return 0;
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return null;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return null;
    }
}
