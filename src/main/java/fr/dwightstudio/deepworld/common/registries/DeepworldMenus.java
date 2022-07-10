package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.menus.WoodenMachineMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldMenus {

    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_LATHE;
    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_PRESS;
    public static RegistryObject<MenuType<WoodenMachineMenu>> WOODEN_GEAR_SHAPER;

    public DeepworldMenus() {
        WOODEN_LATHE = Deepworld.MENU.register("wooden_lathe", () -> new MenuType<>(WoodenMachineMenu::new));
        WOODEN_PRESS = Deepworld.MENU.register("wooden_press", () -> new MenuType<>(WoodenMachineMenu::new));
        WOODEN_GEAR_SHAPER = Deepworld.MENU.register("wooden_gear_shaper", () -> new MenuType<>(WoodenMachineMenu::new));
    }
}
