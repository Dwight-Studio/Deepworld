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
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeTypes {

    public static RegistryObject<RecipeType<MachineRecipe>> LATHING;
    public static RegistryObject<RecipeType<MachineRecipe>> PRESSING;
    public static RegistryObject<RecipeType<MachineRecipe>> GEAR_SHAPING;

    private DeepworldRecipeTypes() {
    }

    public static void register() {
        LATHING = Deepworld.RECIPE_TYPES.register("lathing", () -> createRecipeType("lathing"));
        PRESSING = Deepworld.RECIPE_TYPES.register("pressing", () -> createRecipeType("pressing"));
        GEAR_SHAPING = Deepworld.RECIPE_TYPES.register("gear_shaping", () -> createRecipeType("gear_shaping"));
    }

    public static <T extends Recipe<? extends Container>> RecipeType<T> createRecipeType(String name) {
        return new RecipeType<T>() {
            public String toString() {
                return name;
            }
        };
    }
}
