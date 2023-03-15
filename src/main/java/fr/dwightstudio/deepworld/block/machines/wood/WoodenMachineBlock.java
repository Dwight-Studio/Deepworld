package fr.dwightstudio.deepworld.block.machines.wood;

import fr.dwightstudio.deepworld.blockentities.DeepworldEntities;
import fr.dwightstudio.deepworld.blockentities.machines.wood.WoodenLatheBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WoodenMachineBlock extends BlockWithEntity implements BlockEntityProvider{

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final Property<Boolean> WORKING = BooleanProperty.of("working");

    public WoodenMachineBlock() {
        super(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(3.0f, 2.0f).nonOpaque());

        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(WORKING, false));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx){
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation){
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror){
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(FACING, WORKING);
    }

    /* BLOCK ENTITY */

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState){
        return BlockRenderType.MODEL;
    }

    // TO CHANGE
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WoodenLatheBlockEntity) { // <- Incorrect
                ItemScatterer.spawn(world, pos, (WoodenLatheBlockEntity)blockEntity); // <- Incorrect
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // TO CHANGE
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((WoodenLatheBlockEntity) world.getBlockEntity(pos)); // <- Incorrect
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    // TO CHANGE
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, DeepworldEntities.WOODEN_LATHE, WoodenLatheBlockEntity::tick); // <- Incorrect
    }

}
