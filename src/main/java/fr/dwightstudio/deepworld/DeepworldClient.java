package fr.dwightstudio.deepworld;

import fr.dwightstudio.deepworld.screen.DeepworldScreenHandlers;
import fr.dwightstudio.deepworld.screen.WoodenLatheScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class DeepworldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(DeepworldScreenHandlers.WOODEN_LATHE_SCREEN_HANDLER, WoodenLatheScreen::new);
    }
}
