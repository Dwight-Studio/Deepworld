package fr.dwightstudio.deepworld.client;

import com.mojang.blaze3d.platform.ScreenManager;
import fr.dwightstudio.deepworld.client.container.ContainerScreenWoodenGearShaper;
import fr.dwightstudio.deepworld.client.container.ContainerScreenWoodenLathe;
import fr.dwightstudio.deepworld.client.container.ContainerScreenWoodenPress;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldContainers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class DeepworldClient extends Deepworld {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        MenuScreens.register(DeepworldContainers.WOODEN_PRESS_CONTAINER, ContainerScreenWoodenPress::new);
        ScreenManager.registerFactory(DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER, ContainerScreenWoodenGearShaper::new);
        ScreenManager.registerFactory(DeepworldContainers.WOODEN_LATHE_CONTAINER, ContainerScreenWoodenLathe::new);
    }
}
