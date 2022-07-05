package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.tile.TileEntityPipe;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class BlockPipe extends SixWayBlock implements ITileEntityProvider, IFluidHandler {

    // Block property initializing
    public static final BooleanProperty JUNCTION0 = BooleanProperty.create("junction0");
    public static final BooleanProperty JUNCTION1 = BooleanProperty.create("junction1");

    // Block state creation (registering properties)
    @Override
    final protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(Direction.NORTH);
        builder.add(Direction.EAST);
        builder.add(Direction.SOUTH);
        builder.add(Direction.WEST);
        builder.add(Direction.UP);
        builder.add(Direction.DOWN);
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
                .with(Direction.NORTH, false)
                .with(Direction.EAST, false)
                .with(Direction.SOUTH, false)
                .with(Direction.WEST, false)
                .with(Direction.UP, false)
                .with(Direction.DOWN, false)
                .with(JUNCTION0, false)
                .with(JUNCTION1, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.makeConnections(context.getLevel(), context.getPos());
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
    public BlockEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Override
    public BlockEntity createNewTileEntity(IBlockReader world) {
        return new TileEntityPipe();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockState makeConnections(BlockReader blockReader, BlockPos pos) {
        Block blockDown = blockReader.getBlockState(pos.down()).getBlock();
        Block blockUp = blockReader.getBlockState(pos.up()).getBlock();
        Block blockNorth = blockReader.getBlockState(pos.north()).getBlock();
        Block blockEast = blockReader.getBlockState(pos.east()).getBlock();
        Block blockSouth = blockReader.getBlockState(pos.south()).getBlock();
        Block blockWest = blockReader.getBlockState(pos.west()).getBlock();
        
        return this.getDefaultState().with(Direction.DOWN, blockDown instanceof IFluidHandler)
                .with(Direction.UP, blockUp instanceof IFluidHandler)
                .with(Direction.NORTH, blockNorth instanceof IFluidHandler)
                .with(Direction.EAST, blockEast instanceof IFluidHandler)
                .with(Direction.SOUTH, blockSouth instanceof IFluidHandler)
                .with(Direction.WEST, blockWest instanceof IFluidHandler);
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
