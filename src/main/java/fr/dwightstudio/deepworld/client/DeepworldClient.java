package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.client.screens.WoodenLatheScreen;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldMenus;
import fr.dwightstudio.deepworld.common.menus.WoodenLatheMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class DeepworldClient {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        MenuScreens.register(DeepworldMenus.WOODEN_LATHE_MENU.get(), WoodenLatheScreen::new);
    }

    /*public DeepworldClient() {

        registerScreens();

    }

    public static void registerScreens() {
        MenuScreens.register(DeepworldMenus.WOODEN_LATHE_MENU.get(), WoodenLatheScreen::new);
    }*/
}
