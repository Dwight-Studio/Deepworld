/*
 *     ____           _       __    __     _____ __            ___
 *    / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *   / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *  / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 * /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                  /____/
 * Copyright (c) 2022-2022 Dwight Studio's Team <support@dwight-studio.fr>
 *
 * This Source Code From is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 */

package fr.dwightstudio.deepworld.common.blocks;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blockentities.PipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronPipeBlock extends PipeBlock implements EntityBlock {

    private VoxelShape shape = makeShape();

    public IronPipeBlock() {
        super(0.3125F, Properties.of(Material.METAL));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState otherState, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos p_60545_, @NotNull BlockPos p_60546_) {
        boolean flag = otherState.is(this);
        blockState = blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), flag);
        shape = makeShape(blockState.getValue(NORTH), blockState.getValue(SOUTH), blockState.getValue(EAST), blockState.getValue(WEST), blockState.getValue(UP), blockState.getValue(DOWN));
        return  blockState;
    }



    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new PipeBlockEntity(blockPos, blockState);
    }

    private VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();

        shape = Shapes.join(shape, Shapes.box(0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875), BooleanOp.OR); // Center Cube

        return shape;
    }

    private VoxelShape makeShape(boolean N, boolean S, boolean E, boolean W, boolean U, boolean D){
        VoxelShape shape = Shapes.empty();

        if (S) {
            Deepworld.LOGGER.log(Level.DEBUG, "S");
            shape = Shapes.join(shape, Shapes.box(0.1875, 0.1875, 0, 0.8125, 0.8125, 0.0625), BooleanOp.OR); // SOUTH Plate
            shape = Shapes.join(shape, Shapes.box(0.1875, 0.1875, 0.9375, 0.8125, 0.8125, 1), BooleanOp.OR); // SOUTH Junction
        }

        if (N) {
            Deepworld.LOGGER.log(Level.DEBUG, "N");
            shape = Shapes.join(shape, Shapes.box(0.6875, 0.3125, 0.3125, 1, 0.6875, 0.6875), BooleanOp.OR); // NORTH Plate
            shape = Shapes.join(shape, Shapes.box(0.9375, 0.1875, 0.1875, 1, 0.8125, 0.8125), BooleanOp.OR); // NORTH Junction
        }

        if (W) {
            Deepworld.LOGGER.log(Level.DEBUG, "W");
            shape = Shapes.join(shape, Shapes.box(0, 0.3125, 0.3125, 0.3125, 0.6875, 0.6875), BooleanOp.OR); // WEST Plate
            shape = Shapes.join(shape, Shapes.box(0, 0.1875, 0.1875, 0.0625, 0.8125, 0.8125), BooleanOp.OR); // WEST Junction
        }

        if (E) {
            Deepworld.LOGGER.log(Level.DEBUG, "E");
            shape = Shapes.join(shape, Shapes.box(0.1875, 0.9375, 0.1875, 0.8125, 1, 0.8125), BooleanOp.OR); // EAST Plate
            shape = Shapes.join(shape, Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.0625, 0.8125), BooleanOp.OR); // EAST Junction
        }

        if (U) {
            Deepworld.LOGGER.log(Level.DEBUG, "U");
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.6875, 0.3125, 0.6875, 1, 0.6875), BooleanOp.OR); // UP Plate
            shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.3125, 0.6875), BooleanOp.OR); // UP Junction
        }

        if (D) {
            Deepworld.LOGGER.log(Level.DEBUG, "D");
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.3125, 0.0625, 0.6875, 0.6875, 0.375), BooleanOp.OR); // DOWN Plate
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.3125, 0.625, 0.6875, 0.6875, 0.9375), BooleanOp.OR); // DOWN Junction
        }

        shape = Shapes.join(shape, Shapes.box(0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875), BooleanOp.OR); // Center Cube

        return shape;
    }
}
