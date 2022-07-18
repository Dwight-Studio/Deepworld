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
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import fr.dwightstudio.deepworld.common.recipes.serializers.MachineRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeSerializers {

    public static RegistryObject<RecipeSerializer<MachineRecipe>> LATHING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> PRESSING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> GEAR_SHAPING;

    public DeepworldRecipeSerializers() {
        LATHING = Deepworld.RECIPE_SERIALIZERS.register("lathing", () -> new MachineRecipeSerializer(DeepworldRecipeTypes.LATHING.get()));
        PRESSING = Deepworld.RECIPE_SERIALIZERS.register("pressing", () -> new MachineRecipeSerializer(DeepworldRecipeTypes.PRESSING.get()));
        GEAR_SHAPING = Deepworld.RECIPE_SERIALIZERS.register("gear_shaping", () -> new MachineRecipeSerializer(DeepworldRecipeTypes.GEAR_SHAPING.get()));
    }
}
