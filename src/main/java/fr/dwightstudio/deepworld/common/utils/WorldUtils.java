/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

package fr.dwightstudio.deepworld.common.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class WorldUtils {

    public static List<BlockEntity> getConnectedBlockEntities(LevelAccessor level, BlockPos pos, Predicate<BlockEntity> predicate) {
        ArrayList<BlockEntity> list = new ArrayList<>();
        recusiveGetConnectedBlockEntities(list, level, pos, predicate);
        return list;
    }

    private static void recusiveGetConnectedBlockEntities(ArrayList<BlockEntity> list, LevelAccessor level, BlockPos pos, Predicate<BlockEntity> predicate) {
        for (Direction dir : Direction.values()) {
            BlockEntity entity = level.getBlockEntity(pos.relative(dir));
            if (entity != null && predicate.test(entity)) {
                if (!list.contains(entity)) {
                    list.add(entity);
                    recusiveGetConnectedBlockEntities(list, level, entity.getBlockPos(), predicate);
                }
            }
        }
    }

    public static <E extends BlockEntity> List<E> getBlockEntities(LevelAccessor level, List<BlockPos> pos) {
        return pos.stream().map(level::getBlockEntity).map(blockEntity -> {
            try {
                return (E) blockEntity;
            } catch (ClassCastException ignored) {
                return null;
            }
        }).filter(Objects::nonNull).toList();
    }

    public static CompoundTag savePos(CompoundTag tag, BlockPos pos) {
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        return tag;
    }

    public static BlockPos posFromTag(CompoundTag tag) {
        return new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
    }
}
