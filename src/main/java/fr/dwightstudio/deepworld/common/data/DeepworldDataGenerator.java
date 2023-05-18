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

package fr.dwightstudio.deepworld.common.data;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Deepworld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepworldDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        event.includeServer();
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        ExistingFileHelper efh = event.getExistingFileHelper();

        gen.addProvider(
                event.includeClient(),
                new DeepworldBlockStateProvider(packOutput, efh)
        );

        gen.addProvider(
                event.includeServer(),
                new DeepworldRecipeProvider(packOutput)
        );

    }

}
