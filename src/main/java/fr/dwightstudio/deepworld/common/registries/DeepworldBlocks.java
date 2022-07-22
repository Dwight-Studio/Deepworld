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

package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blocks.ObsidianInfusedSteelBlockBlock;
import fr.dwightstudio.deepworld.common.blocks.SteelBlockBlock;
import fr.dwightstudio.deepworld.common.blocks.frames.WoodenFrameBlock;
import fr.dwightstudio.deepworld.common.blocks.machines.wood.WoodenGearShaperBlock;
import fr.dwightstudio.deepworld.common.blocks.machines.wood.WoodenLatheBlock;
import fr.dwightstudio.deepworld.common.blocks.machines.wood.WoodenPressBlock;
import fr.dwightstudio.deepworld.common.blocks.tanks.IronTankBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlocks {

    public static RegistryObject<Block> STEEL_BLOCK;
    public static RegistryObject<Block> OBSIDIAN_INFUSED_STEEL_BLOCK;

    // Frames
    public static RegistryObject<Block> WOODEN_FRAME;
    public static RegistryObject<Block> IRON_FRAME; // TODO: Add this
    public static RegistryObject<Block> STEEL_FRAME;
    public static RegistryObject<Block> OBSIDIAN_INFUSED_STEEL_FRAME;

    //Machines
    public static RegistryObject<Block> WOODEN_PRESS;
    public static RegistryObject<Block> WOODEN_GEAR_SHAPER;
    public static RegistryObject<Block> WOODEN_LATHE;

    public static RegistryObject<Block> IRON_TANK;


    public DeepworldBlocks() {
        STEEL_BLOCK = Deepworld.BLOCKS.register("steel_block", SteelBlockBlock::new);
        OBSIDIAN_INFUSED_STEEL_BLOCK = Deepworld.BLOCKS.register("obsidian_infused_steel_block", ObsidianInfusedSteelBlockBlock::new);

        // Frames
        WOODEN_FRAME = Deepworld.BLOCKS.register("wooden_frame", WoodenFrameBlock::new);
        //STEEL_FRAME = Deepworld.BLOCKS.register("steel_frame", SteelFrameBlock::new);
        //OBSIDIAN_INFUSED_STEEL_FRAME = Deepworld.BLOCKS.register("obsidian_infused_steel_frame", ObsidianInfusedSteelFrame::new);

        // Machines
        WOODEN_PRESS = Deepworld.BLOCKS.register("wooden_press", WoodenPressBlock::new);
        WOODEN_GEAR_SHAPER = Deepworld.BLOCKS.register("wooden_gear_shaper", WoodenGearShaperBlock::new);
        WOODEN_LATHE = Deepworld.BLOCKS.register("wooden_lathe", WoodenLatheBlock::new);

        IRON_TANK = Deepworld.BLOCKS.register("iron_tank", IronTankBlock::new);
    }
}
