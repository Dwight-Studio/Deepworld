package fr.dwightstudio.deepworld.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameBlock extends DirectionalBlock {

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
        List<Property<?>> list = new ArrayList<>();

        list.add(FACING);
        list.add(COVER);

        Collections.addAll(list, getComponentProperties());

        builder.add(list.toArray(new Property[0]));
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
