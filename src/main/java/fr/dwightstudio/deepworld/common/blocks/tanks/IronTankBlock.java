package fr.dwightstudio.deepworld.common.blocks.tanks;

import fr.dwightstudio.deepworld.common.blockentities.tanks.IronTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class IronTankBlock extends Block implements EntityBlock {

    private final VoxelShape shape = makeShape();

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    public IronTankBlock() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .lightLevel(litBlockEmission(15)));
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightLevel) {
        return (p_50763_) -> p_50763_.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new IronTankBlockEntity(blockPos, blockState);
    }

    public IronTankBlockEntity getBlockEntity(Level level, BlockPos blockPos) {
        return (IronTankBlockEntity) level.getBlockEntity(blockPos);
    }


    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (!player.getItemInHand(interactionHand).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return InteractionResult.FAIL;
        } else {
            if (level.isClientSide()) return InteractionResult.SUCCESS;

            LazyOptional<IFluidHandlerItem> inputContainer = FluidUtil.getFluidHandler(player.getItemInHand(interactionHand));

            if (inputContainer.isPresent() && inputContainer.map((fluidContainer) -> fluidContainer.getFluidInTank(0).isEmpty()).get()) {
                level.levelEvent(1047, blockPos, 0);
                if (FluidUtil.tryFillContainer(player.getItemInHand(interactionHand), this.getBlockEntity(level, blockPos), this.getBlockEntity(level, blockPos).getCapacity(), player, true) == FluidActionResult.FAILURE) return InteractionResult.FAIL;
            } else {
                if (FluidUtil.tryEmptyContainer(player.getItemInHand(interactionHand), this.getBlockEntity(level, blockPos), this.getBlockEntity(level, blockPos).getCapacity(), player, true) == FluidActionResult.FAILURE) return InteractionResult.FAIL;
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState p_60555_, @NotNull BlockGetter p_60556_, @NotNull BlockPos p_60557_, @NotNull CollisionContext p_60558_) {
        return shape;
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext p_60575_) {
        return shape;
    }

    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 1, 0.8125), BooleanOp.OR);

        return shape;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block p_60512_, BlockPos p_60513_, boolean p_60514_) {
        this.onPlace(state, level, pos, defaultBlockState(), p_60514_);
    }

    @Override
    public void onPlace(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState oldBlockState, boolean p_60570_) {
        BlockState newBlockState = blockState;

        if (level.getBlockEntity(blockPos.above()) instanceof IronTankBlockEntity) {
            IronTankBlockEntity blockEntity = (IronTankBlockEntity) level.getBlockEntity(blockPos.above());
            newBlockState = newBlockState.setValue(UP, blockEntity.canConnect(getBlockEntity(level, blockPos).getFluid()));
            if (!blockEntity.isEmpty()) {
                int accepted = getBlockEntity(level, blockPos).fill(blockEntity.drain(Integer.MAX_VALUE, IFluidHandler.FluidAction.SIMULATE), IFluidHandler.FluidAction.SIMULATE);
                if (accepted != 0) {
                    getBlockEntity(level, blockPos).fill(blockEntity.drain(accepted, IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        } else {
            newBlockState = newBlockState.setValue(UP, false);
        }
        if (level.getBlockEntity(blockPos.below()) instanceof IronTankBlockEntity) {
            IronTankBlockEntity blockEntity = (IronTankBlockEntity) level.getBlockEntity(blockPos.below());
            newBlockState = newBlockState.setValue(DOWN, blockEntity.canConnect(getBlockEntity(level, blockPos).getFluid()));
            if (!getBlockEntity(level, blockPos).isEmpty()) {
                int accepted = blockEntity.fill(getBlockEntity(level, blockPos).getFluid(), IFluidHandler.FluidAction.SIMULATE);
                if (accepted != 0) {
                    blockEntity.fill((getBlockEntity(level, blockPos).drain(accepted, IFluidHandler.FluidAction.EXECUTE)), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        } else {
            newBlockState = newBlockState.setValue(DOWN, false);
        }

        level.setBlock(blockPos, newBlockState, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, BlockStateProperties.LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(BlockStateProperties.LIT, false);
    }
}
