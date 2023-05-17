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
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DeepworldRecipeProvider extends RecipeProvider {
    public DeepworldRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {

        armor(exporter,
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS.get());
        tools(exporter,
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD.get());

        armor(exporter,
                DeepworldItems.STEEL_INGOT.get(),
                DeepworldItems.STEEL_HELMET.get(),
                DeepworldItems.STEEL_CHESTPLATE.get(),
                DeepworldItems.STEEL_LEGGINGS.get(),
                DeepworldItems.STEEL_BOOTS.get());
        tools(exporter,
                DeepworldItems.STEEL_INGOT.get(),
                DeepworldItems.STEEL_PICKAXE.get(),
                DeepworldItems.STEEL_AXE.get(),
                DeepworldItems.STEEL_SHOVEL.get(),
                DeepworldItems.STEEL_HOE.get(),
                DeepworldItems.STEEL_SWORD.get());
    }

    private static void armor(@NotNull Consumer<FinishedRecipe> exporter, ItemLike material, ItemLike helmet,
                                      ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        // Helmet
        ShapedRecipeBuilder.shaped(helmet)
                .pattern("###")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/armors/" + material.asItem() + "_helmet");

        // Chestplate
        ShapedRecipeBuilder.shaped(chestplate)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/armors/" + material.asItem() + "_chestplate");

        // Leggings
        ShapedRecipeBuilder.shaped(leggings)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/armors/" + material.asItem() + "_leggings");

        // Boots
        ShapedRecipeBuilder.shaped(boots)
                .pattern("# #")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/armors/" + material.asItem() + "_boots");

    }

    private static void tools(@NotNull Consumer<FinishedRecipe> exporter, ItemLike material, ItemLike pickaxe,
                              ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike sword) {
        // Pickaxe
        ShapedRecipeBuilder.shaped(pickaxe)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/tools/" + material.asItem() + "_pickaxe");

        // Axe
        ShapedRecipeBuilder.shaped(axe)
                .pattern("## ")
                .pattern("#| ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/tools/" + material.asItem() + "_axe");

        // Shovel
        ShapedRecipeBuilder.shaped(shovel)
                .pattern(" # ")
                .pattern(" | ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/tools/" + material.asItem() + "_shovel");

        // Hoe
        ShapedRecipeBuilder.shaped(hoe)
                .pattern("## ")
                .pattern(" | ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/tools/" + material.asItem() + "_hoe");

        // Sword
        ShapedRecipeBuilder.shaped(sword)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, Deepworld.MOD_ID + "/tools/" + material.asItem() + "_sword");
    }
}
