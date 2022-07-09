package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.client.screens.WoodenMachineScreen;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
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
        registerMenuTypes();
        setRenderLayers();
    }

    private static void registerMenuTypes() {
        MenuScreens.register(DeepworldMenus.WOODEN_LATHE.get(), WoodenMachineScreen::new);
        MenuScreens.register(DeepworldMenus.WOODEN_PRESS.get(), WoodenMachineScreen::new);
        MenuScreens.register(DeepworldMenus.WOODEN_GEAR_SHAPER.get(), WoodenMachineScreen::new);
    }

    private static void setRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_LATHE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_PRESS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_GEAR_SHAPER.get(), RenderType.cutout());

    }

}
