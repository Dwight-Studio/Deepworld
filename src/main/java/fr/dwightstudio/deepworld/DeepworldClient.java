package fr.dwightstudio.deepworld;

import fr.dwightstudio.deepworld.blockentities.DeepworldEntities;
import fr.dwightstudio.deepworld.blockentities.client.WoodenLatheBlockEntityRenderer;
import fr.dwightstudio.deepworld.screen.DeepworldScreenHandlers;
import fr.dwightstudio.deepworld.screen.WoodenLatheScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class DeepworldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(DeepworldScreenHandlers.WOODEN_LATHE_SCREEN_HANDLER, WoodenLatheScreen::new);

        BlockEntityRendererFactories.register(DeepworldEntities.WOODEN_LATHE, WoodenLatheBlockEntityRenderer::new);

    }
}
