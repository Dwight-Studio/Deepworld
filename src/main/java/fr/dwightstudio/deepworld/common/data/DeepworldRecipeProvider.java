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

import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
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
        // Steel
        armor(exporter,
                DeepworldItems.STEEL_INGOT.get(),
                DeepworldItems.STEEL_HELMET.get(),
                DeepworldItems.STEEL_CHESTPLATE.get(),
                DeepworldItems.STEEL_LEGGINGS.get(),
                DeepworldItems.STEEL_BOOTS.get()
        );
        tools(exporter,
                DeepworldItems.STEEL_INGOT.get(),
                DeepworldItems.STEEL_PICKAXE.get(),
                DeepworldItems.STEEL_AXE.get(),
                DeepworldItems.STEEL_SHOVEL.get(),
                DeepworldItems.STEEL_HOE.get(),
                DeepworldItems.STEEL_SWORD.get()
        );
        block(exporter,
                DeepworldItems.STEEL_INGOT.get(),
                DeepworldItems.STEEL_BLOCK.get()
        );

        // Obsidian Infused Steel
        armor(exporter,
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS.get()
        );
        tools(exporter,
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD.get()
        );
        block(exporter,
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT.get(),
                DeepworldItems.OBSIDIAN_INFUSED_STEEL_BLOCK.get()
        );
    }

    private static void armor(@NotNull Consumer<FinishedRecipe> exporter, ItemLike material, ItemLike helmet,
                              ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        // Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("###")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, helmet.asItem().toString());

        // Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, chestplate.asItem().toString());

        // Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, leggings.asItem().toString());

        // Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("# #")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, boots.asItem().toString());

    }

    private static void tools(@NotNull Consumer<FinishedRecipe> exporter, ItemLike material, ItemLike pickaxe,
                              ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike sword) {
        // Pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, pickaxe.asItem().toString());

        // Axe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("##")
                .pattern("#|")
                .pattern(" |")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, axe.asItem().toString());

        // Shovel
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("#")
                .pattern("|")
                .pattern("|")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, shovel.asItem().toString());

        // Hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("##")
                .pattern(" |")
                .pattern(" |")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, hoe.asItem().toString());

        // Sword
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("#")
                .pattern("#")
                .pattern("|")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("creteria", RecipeProvider.has(material))
                .save(exporter, sword.asItem().toString());
    }

    private static void block(@NotNull Consumer<FinishedRecipe> exporter, ItemLike material, ItemLike block) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', material)
                .unlockedBy("criteria", RecipeProvider.has(material))
                .save(exporter, block.asItem().toString());
    }
}
