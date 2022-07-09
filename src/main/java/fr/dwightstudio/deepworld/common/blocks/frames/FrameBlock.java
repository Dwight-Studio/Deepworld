package fr.dwightstudio.deepworld.common.blocks.frames;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FrameBlock extends HorizontalDirectionalBlock {

    public static final DirectionProperty FACING =  HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty COVER = IntegerProperty.create("cover", 0, 6);

    public FrameBlock(Properties properties) {
        super(properties);

        this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(COVER, 0);
    }

    protected Property<?>[] getComponentProperties() {
        return new Property[] {};
    }

    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, COVER);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        level.markAndNotifyBlock(blockPos, level.getChunkAt(blockPos), level.getBlockState(blockPos), blockState, Block.UPDATE_ALL, Block.UPDATE_CLIENTS);
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }
}
