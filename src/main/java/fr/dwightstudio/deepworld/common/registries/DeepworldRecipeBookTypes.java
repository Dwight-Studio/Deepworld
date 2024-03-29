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

import net.minecraft.world.inventory.RecipeBookType;

public class DeepworldRecipeBookTypes {
    public static RecipeBookType LATHE;
    public static RecipeBookType PRESS;
    public static RecipeBookType GEAR_SHAPER;

    private DeepworldRecipeBookTypes() {
    }

    public static void register() {
        LATHE = RecipeBookType.create("LATHE");
        PRESS = RecipeBookType.create("PRESS");
        GEAR_SHAPER = RecipeBookType.create("GEAR_SHAPER");
    }
}
