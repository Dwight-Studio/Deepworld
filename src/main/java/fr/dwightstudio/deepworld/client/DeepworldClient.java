package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.client.container.ContainerScreenWoodenPress;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldContainers;
import fr.dwightstudio.deepworld.client.container.ContainerScreenWoodenGearShaper;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class DeepworldClient extends Deepworld {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(DeepworldContainers.WOODEN_PRESS_CONTAINER, ContainerScreenWoodenPress::new);
        ScreenManager.registerFactory(DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER, ContainerScreenWoodenGearShaper::new);
    }
}
