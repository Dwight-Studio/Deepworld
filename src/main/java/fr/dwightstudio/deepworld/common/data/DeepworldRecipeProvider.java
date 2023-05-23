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
import fr.dwightstudio.deepworld.common.data.recipes.ArmorBuilder;
import fr.dwightstudio.deepworld.common.data.recipes.ToolBuilder;
import fr.dwightstudio.deepworld.common.data.recipes.WoodenTier;
import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import fr.dwightstudio.deepworld.common.registries.DeepworldTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DeepworldRecipeProvider extends RecipeProvider {
    public DeepworldRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {
        // Steel
        new ArmorBuilder(exporter, DeepworldTags.Items.INGOTS_STEEL)
                .helmet(DeepworldItems.STEEL_HELMET.get())
                .chestplate(DeepworldItems.STEEL_CHESTPLATE.get())
                .leggings(DeepworldItems.STEEL_LEGGINGS.get())
                .boots(DeepworldItems.STEEL_BOOTS.get());
        new ToolBuilder(exporter, DeepworldTags.Items.INGOTS_STEEL)
                .pickaxe(DeepworldItems.STEEL_PICKAXE.get())
                .axe(DeepworldItems.STEEL_AXE.get())
                .shovel(DeepworldItems.STEEL_SHOVEL.get())
                .hoe(DeepworldItems.STEEL_HOE.get())
                .sword(DeepworldItems.STEEL_SWORD.get());
        blockFrom9(exporter, DeepworldTags.Items.INGOTS_STEEL, DeepworldItems.STEEL_BLOCK.get());
        //nuggets(exporter, DeepworldTags.Items.INGOTS_STEEL, DeepworldItems.STEEL_NUGGET.get()); TODO: Ajouter Nuggets

        // Obsidian Infused Steel
        new ArmorBuilder(exporter, DeepworldTags.Items.INGOTS_OBSIDIAN_INFUSED_STEEL)
                .helmet(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET.get())
                .chestplate(DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE.get())
                .leggings(DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS.get())
                .boots(DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS.get());
        new ToolBuilder(exporter, DeepworldTags.Items.INGOTS_OBSIDIAN_INFUSED_STEEL)
                .pickaxe(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE.get())
                .axe(DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE.get())
                .shovel(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL.get())
                .hoe(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE.get())
                .sword(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD.get());
        blockFrom9(exporter, DeepworldTags.Items.INGOTS_OBSIDIAN_INFUSED_STEEL, DeepworldItems.OBSIDIAN_INFUSED_STEEL_BLOCK.get());
        //nuggets(exporter, DeepworldTags.Items.INGOTS_OBSIDIAN_INFUSED_STEEL, DeepworldItems.OBSIDIAN_INFUSED_NUGGET.get());

        WoodenTier.generate(exporter);
    }

    public static void blockFrom4(@NotNull Consumer<FinishedRecipe> exporter, TagKey<Item> material, ItemLike block) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("##")
                .pattern("##")
                .define('#', material)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(block.asItem().toString()));
    }

    public static void blockFrom9(@NotNull Consumer<FinishedRecipe> exporter, TagKey<Item> material, ItemLike block) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', material)
                    .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                    .save(exporter, DeepworldRecipeProvider.getResourceLocation(block.asItem().toString()));
    }

    public static void nuggets(@NotNull Consumer<FinishedRecipe> exporter, TagKey<Item> material, ItemLike nugget) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, nugget, 9)
                .requires(material)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(nugget.asItem().toString()));
    }

    public static void nail(@NotNull Consumer<FinishedRecipe> exporter, TagKey<Item> material, ItemLike nail) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, nail)
                .pattern("#")
                .pattern("#")
                .define('#', material)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(nail.asItem().toString()));
    }

    public static InventoryChangeTrigger.TriggerInstance has(@NotNull TagKey<Item> item) {
        return RecipeProvider.has(item);
    }

    public static InventoryChangeTrigger.TriggerInstance has(@NotNull ItemLike item) {
        return RecipeProvider.has(item);
    }

    public static ResourceLocation getResourceLocation(String name) {
        return new ResourceLocation(Deepworld.MOD_ID, name);
    }
}
