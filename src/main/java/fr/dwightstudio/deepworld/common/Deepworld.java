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

package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.client.DeepworldClient;
import fr.dwightstudio.deepworld.common.data.DeepworldDataGenerator;
import fr.dwightstudio.deepworld.common.multiblocks.MultiblocksManager;
import fr.dwightstudio.deepworld.common.registries.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Deepworld.MOD_ID)
@Mod.EventBusSubscriber()
public class Deepworld {

    // Mod info
    public static final String MOD_ID = "deepworld";
    public static final String MOD_NAME = "Deep World";
    public static final String LOG_PREFIX = MOD_NAME;
    public static final CreativeModeTab MOD_TAB = new DeepworldCreativeTab();
    public static final Logger LOGGER = LogManager.getLogger(LOG_PREFIX);

    public static IEventBus MOD_EVENT_BUS;


    // Registering
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);

    public Deepworld() {
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        // Registries
        Deepworld.registerAssets();

        BLOCKS.register(MOD_EVENT_BUS);
        ITEMS.register(MOD_EVENT_BUS);
        BLOCK_ENTITY_TYPES.register(MOD_EVENT_BUS);
        MENU_TYPES.register(MOD_EVENT_BUS);
        RECIPE_TYPES.register(MOD_EVENT_BUS);
        RECIPE_SERIALIZERS.register(MOD_EVENT_BUS);
        SOUND_EVENTS.register(MOD_EVENT_BUS);

        MOD_EVENT_BUS.register(DeepworldClient.class);
        MOD_EVENT_BUS.register(MultiblocksManager.class);
        MOD_EVENT_BUS.register(DeepworldDataGenerator.class);
    }

    public static void registerAssets() {
        new DeepworldBlocks();
        new DeepworldItems();
        new DeepworldBlockEntities();
        new DeepworldMenus();
        new DeepworldRecipeTypes();
        new DeepworldRecipeBookTypes();
        new DeepworldRecipeSerializers();
        new DeepworldSoundEvents();
    }
}
