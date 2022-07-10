package fr.dwightstudio.deepworld.common.blocks;

import fr.dwightstudio.deepworld.common.blockentities.tanks.IronTankBlockEntity;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronTankBlock extends Block implements EntityBlock {

    private IronTankBlockEntity blockEntity;
    private final VoxelShape shape = makeShape();

    public IronTankBlock() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.GLASS)
                .noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        this.blockEntity = new IronTankBlockEntity(blockPos, blockState);
        return this.blockEntity;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (!player.getItemInHand(interactionHand).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent()) {
            return InteractionResult.FAIL;
        } else {
            if (level.isClientSide()) return InteractionResult.SUCCESS;

            LazyOptional<IFluidHandlerItem> inputContainer = FluidUtil.getFluidHandler(player.getItemInHand(interactionHand));

            if (inputContainer.map((fluidContainer) -> fluidContainer.getFluidInTank(0).isEmpty()).get()) {
                level.levelEvent(1047, blockPos, 0);
                if (FluidUtil.tryFillContainer(player.getItemInHand(interactionHand), this.blockEntity, this.blockEntity.getCapacity(), player, true) == FluidActionResult.FAILURE) return InteractionResult.FAIL;
            } else {
                if (FluidUtil.tryEmptyContainer(player.getItemInHand(interactionHand), this.blockEntity, this.blockEntity.getCapacity(), player, true) == FluidActionResult.FAILURE) return InteractionResult.FAIL;
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
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.25, 0.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.75, 0.25, 0.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.1875, 0.8125, 0.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.75, 0.8125, 0.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.0625, 0.75, 0.8125, 1, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 1, 0.75, 0.8125, 1.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 1, 0.1875, 0.8125, 1.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0.0625, 0.1875, 0.8125, 1, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 1, 0.75, 0.25, 1.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.75, 0.25, 1, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 1, 0.1875, 0.25, 1.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.1875, 0.25, 1, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.25, 0.25, 0.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.25, 0.8125, 0.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.75, 0.75, 0.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.1875, 0.75, 0.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 1, 0.1875, 0.75, 1.0625, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 1, 0.25, 0.8125, 1.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 1, 0.75, 0.75, 1.0625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 1, 0.25, 0.25, 1.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0.0625, 0.25, 0.3125, 1, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.0625, 0.25, 0.75, 1, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.25, 0.6875, 1, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.6875, 0.6875, 1, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 1, 0.25, 0.75, 1.0625, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.0625, 0.75), BooleanOp.OR);

        return shape;
    }
}
