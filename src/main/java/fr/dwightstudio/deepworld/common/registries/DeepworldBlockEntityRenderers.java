package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.blockentities.renderers.FluidTankRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class DeepworldBlockEntityRenderers {

    @SubscribeEvent
    public void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DeepworldBlockEntities.IRON_TANK.get(), FluidTankRenderer::new);
    }

}
