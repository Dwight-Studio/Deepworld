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

package fr.dwightstudio.deepworld.common.recipes;

import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeSerializers;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class PressRecipe extends MachineRecipe {

    public PressRecipe(RecipeType<?> recipeType, ResourceLocation resourceLocation, Ingredient ingredient, int ingredientCount, ItemStack resultItem, int processTime, MachineTier machineTier) {
        super(recipeType, resourceLocation, ingredient, ingredientCount, resultItem, processTime, machineTier);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return DeepworldRecipeSerializers.PRESSING.get();
    }
}
