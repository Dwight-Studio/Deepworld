package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.menus.WoodenMachineMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldMenus {

    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_LATHE_MENU;
    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_PRESS_MENU;

    public DeepworldMenus() {
        WOODEN_LATHE_MENU = Deepworld.MENU.register("wooden_lathe", () -> new MenuType<>(WoodenMachineMenu::new));
        WOODEN_PRESS_MENU = Deepworld.MENU.register("wooden_press", () -> new MenuType<>(WoodenMachineMenu::new));
    }
}
