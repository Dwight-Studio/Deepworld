package fr.dwightstudio.deepworld.common.blocks.tanks;

import fr.dwightstudio.deepworld.common.blockentities.tanks.TankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TankBlock extends Block {

    protected final VoxelShape shape = makeShape();

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public TankBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, BlockStateProperties.LIT);
    }


    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource p_222948_) {
        updateTank(blockState, level, blockPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(BlockStateProperties.LIT, false);
    }

    @Override
    public void playerWillDestroy(Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Player player) {
        if (!level.isClientSide && !player.isCreative() && level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof TankBlockEntity simpleTank) {
                ItemStack itemstack = new ItemStack(this);

                simpleTank.saveToItem(itemstack);
                ItemEntity itementity = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemstack);
                itementity.setDefaultPickUpDelay();
                level.addFreshEntity(itementity);
            }
        }
    }

    public TankBlockEntity getBlockEntity(Level level, BlockPos blockPos) {
        return (TankBlockEntity) level.getBlockEntity(blockPos);
    }


    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (!player.getItemInHand(interactionHand).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {
            return InteractionResult.FAIL;
        } else {
            if (level.isClientSide()) return InteractionResult.SUCCESS;

            LazyOptional<IFluidHandlerItem> inputContainer = FluidUtil.getFluidHandler(player.getItemInHand(interactionHand));

            if (inputContainer.isPresent() && inputContainer.map((fluidContainer) -> fluidContainer.getFluidInTank(0).isEmpty()).get()) {
                level.levelEvent(1047, blockPos, 0);
                if (FluidUtil.tryFillContainer(player.getItemInHand(interactionHand), this.getBlockEntity(level, blockPos), this.getBlockEntity(level, blockPos).getCapacity(), player, true) == FluidActionResult.FAILURE)
                    return InteractionResult.FAIL;
            } else {
                if (FluidUtil.tryEmptyContainer(player.getItemInHand(interactionHand), this.getBlockEntity(level, blockPos), this.getBlockEntity(level, blockPos).getCapacity(), player, true) == FluidActionResult.FAILURE)
                    return InteractionResult.FAIL;
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

    public VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 1, 0.8125), BooleanOp.OR);

        return shape;
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos blockPos, boolean p_60514_) {
        level.scheduleTick(pos, this, 1);
    }

    @Override
    public void onPlace(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState oldBlockState, boolean p_60570_) {
        level.scheduleTick(blockPos, this, 1);
    }

    protected void updateTank(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos) {
        BlockState newBlockState = blockState;

        if (level.getBlockEntity(blockPos.below()) instanceof TankBlockEntity blockEntity) {
            if (getBlockEntity(level, blockPos).canConnect(blockEntity)) {
                newBlockState = newBlockState.setValue(DOWN, true);
            } else {
                newBlockState = newBlockState.setValue(DOWN, false);
            }
        } else {
            newBlockState = newBlockState.setValue(DOWN, false);
        }
        if (level.getBlockEntity(blockPos.above()) instanceof TankBlockEntity blockEntity) {
            if (getBlockEntity(level, blockPos).canConnect(blockEntity)) {
                newBlockState = newBlockState.setValue(UP, true);
                if (!blockState.getValue(UP) && !blockEntity.isEmpty()) getBlockEntity(level, blockPos).rearrangeFluid();
            } else {
                newBlockState = newBlockState.setValue(UP, false);
            }
        } else {
            newBlockState = newBlockState.setValue(UP, false);
        }

        level.setBlock(blockPos, newBlockState, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
    }
}
