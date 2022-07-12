package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.client.screens.WoodenMachineScreen;
import fr.dwightstudio.deepworld.client.renderers.FluidTankRenderer;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.registries.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.registries.DeepworldMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class DeepworldClient {

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        registerMenuTypes();
        setRenderLayers();
    }

    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DeepworldBlockEntities.IRON_TANK.get(), FluidTankRenderer::new);
    }

    private static void registerMenuTypes() {
        MenuScreens.register(DeepworldMenus.WOODEN_LATHE.get(), WoodenMachineScreen::new);
        MenuScreens.register(DeepworldMenus.WOODEN_PRESS.get(), WoodenMachineScreen::new);
        MenuScreens.register(DeepworldMenus.WOODEN_GEAR_SHAPER.get(), WoodenMachineScreen::new);
    }

    private static void setRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_FRAME.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_LATHE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_PRESS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.WOODEN_GEAR_SHAPER.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(DeepworldBlocks.IRON_TANK.get(), RenderType.cutout());
    }

}
