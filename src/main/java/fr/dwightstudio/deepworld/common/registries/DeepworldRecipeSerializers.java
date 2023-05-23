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

import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import fr.dwightstudio.deepworld.common.recipes.serializers.MachineRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeSerializers {

    public static RegistryObject<RecipeSerializer<MachineRecipe>> LATHING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> PRESSING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> GEAR_SHAPING;

    private DeepworldRecipeSerializers() {
    }

    public static void register() {
        LATHING = MachineRecipeSerializer.register("lathing", DeepworldRecipeTypes.LATHING);
        PRESSING = MachineRecipeSerializer.register("pressing", DeepworldRecipeTypes.PRESSING);
        GEAR_SHAPING = MachineRecipeSerializer.register("gear_shaping", DeepworldRecipeTypes.GEAR_SHAPING);
    }
}
