package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.client.screens.WoodenMachineScreen;
import fr.dwightstudio.deepworld.common.DeepworldMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class DeepworldClient {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        MenuScreens.register(DeepworldMenus.WOODEN_LATHE_MENU.get(), WoodenMachineScreen::new);
        MenuScreens.register(DeepworldMenus.WOODEN_PRESS_MENU.get(), WoodenMachineScreen::new);
    }

}
