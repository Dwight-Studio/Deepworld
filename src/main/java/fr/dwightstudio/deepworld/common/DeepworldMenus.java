package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.menus.WoodenLatheMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldMenus {

    public static RegistryObject<MenuType<WoodenLatheMenu>> WOODEN_LATHE_MENU;

    public DeepworldMenus() {
        WOODEN_LATHE_MENU = Deepworld.MENU.register("wooden_lathe", () -> new MenuType<>(WoodenLatheMenu::new));
    }
}
