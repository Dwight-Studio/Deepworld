package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.blockentity.WoodenLatheBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class WoodenLatheBlock extends DirectionalBlock implements EntityBlock {

    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final Property<Boolean> WORKING = BooleanProperty.create("working");

    public WoodenLatheBlock() {
        super(Properties.of(Material.WOOD)
                .sound(SoundType.WOOD)
                .strength(3, 2));

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WORKING, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WORKING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WoodenLatheBlockEntity(blockPos, blockState);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = level.getBlockEntity(blockPos);
            if (tileentity instanceof WoodenLatheBlockEntity woodenLatheBlockEntity) {
                dropResources(woodenLatheBlockEntity.getBlockState(), woodenLatheBlockEntity.getLevel(), woodenLatheBlockEntity.getBlockPos(), woodenLatheBlockEntity);
            }
            super.onRemove(state, level, blockPos, newState, isMoving);  // call it last, because it removes the TileEntity
        }

        super.onRemove(state, level, blockPos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS; // on client side, don't do anything

        MenuProvider namedContainerProvider = this.getMenuProvider(blockState, level, blockPos);
        if (namedContainerProvider != null) {

            ServerPlayer serverPlayerEntity = (ServerPlayer)player;
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, (packetBuffer)->{});
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }
}
