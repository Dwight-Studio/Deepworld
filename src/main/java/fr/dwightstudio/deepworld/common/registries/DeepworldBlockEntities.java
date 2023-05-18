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

package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blockentities.PipeBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenGearShaperBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenLatheBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenPressBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.tanks.IronTankBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlockEntities {

    public static RegistryObject<BlockEntityType<WoodenFrameBlockEntity>> WOODEN_FRAME;

    public static RegistryObject<BlockEntityType<WoodenLatheBlockEntity>> WOODEN_LATHE;
    public static RegistryObject<BlockEntityType<WoodenPressBlockEntity>> WOODEN_PRESS;
    public static RegistryObject<BlockEntityType<WoodenGearShaperBlockEntity>> WOODEN_GEAR_SHAPER;

    public static RegistryObject<BlockEntityType<IronTankBlockEntity>> IRON_TANK;
    public static RegistryObject<BlockEntityType<PipeBlockEntity>> PIPE;

    private DeepworldBlockEntities() {
    }

    public static void register() {
        WOODEN_FRAME = Deepworld.BLOCK_ENTITY_TYPES.register("wooden_frame", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenFrameBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_FRAME.get()).build(null));

        WOODEN_LATHE = Deepworld.BLOCK_ENTITY_TYPES.register("wooden_lathe", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenLatheBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_LATHE.get()).build(null));
        WOODEN_PRESS = Deepworld.BLOCK_ENTITY_TYPES.register("wooden_press", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenPressBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_PRESS.get()).build(null));
        WOODEN_GEAR_SHAPER = Deepworld.BLOCK_ENTITY_TYPES.register("wooden_gear_shaper", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenGearShaperBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_GEAR_SHAPER.get()).build(null));

        IRON_TANK = Deepworld.BLOCK_ENTITY_TYPES.register("iron_tank", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new IronTankBlockEntity(blockPos, blockState), DeepworldBlocks.IRON_TANK.get()).build(null));
        PIPE = Deepworld.BLOCK_ENTITY_TYPES.register("pipe", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new PipeBlockEntity(blockPos, blockState), DeepworldBlocks.PIPE.get()).build(null));
    }
}
