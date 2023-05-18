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
import fr.dwightstudio.deepworld.common.menus.WoodenMachineMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldMenus {

    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_LATHE;
    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_PRESS;
    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_GEAR_SHAPER;

    private DeepworldMenus() {
    }

    public static void register() {
        WOODEN_LATHE = Deepworld.MENU_TYPES.register("wooden_lathe", () -> new MenuType<>(WoodenMachineMenu::new));
        WOODEN_PRESS = Deepworld.MENU_TYPES.register("wooden_press", () -> new MenuType<>(WoodenMachineMenu::new));
        WOODEN_GEAR_SHAPER = Deepworld.MENU_TYPES.register("wooden_gear_shaper", () -> new MenuType<>(WoodenMachineMenu::new));
    }
}
