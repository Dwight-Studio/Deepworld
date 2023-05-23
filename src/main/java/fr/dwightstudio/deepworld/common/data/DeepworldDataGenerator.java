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
import fr.dwightstudio.deepworld.common.data.tags.DeepworldBlockTagsProvider;
import fr.dwightstudio.deepworld.common.data.tags.DeepworldItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Deepworld.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeepworldDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        event.includeServer();
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        gen.addProvider(
                event.includeClient(),
                new DeepworldBlockModelProvider(packOutput, efh)
        );

        gen.addProvider(
                event.includeClient(),
                new DeepworldBlockStateProvider(packOutput, efh)
        );

        gen.addProvider(
                event.includeClient(),
                new DeepworldItemModelProvider(packOutput, efh)
        );

        gen.addProvider(
                event.includeServer(),
                new DeepworldRecipeProvider(packOutput)
        );

        DeepworldBlockTagsProvider blockTagsProvider = new DeepworldBlockTagsProvider(packOutput, lookupProvider, efh);

        gen.addProvider(
                event.includeServer(),
                blockTagsProvider
        );

        gen.addProvider(
                event.includeServer(),
                new DeepworldItemTagsProvider(packOutput, lookupProvider, blockTagsProvider, efh)
        );

        gen.addProvider(
                event.includeServer(),
                new LootTableProvider(
                        packOutput,
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(DeepworldBlockLootProvider::new, LootContextParamSets.BLOCK))
                )
        );

    }

}
