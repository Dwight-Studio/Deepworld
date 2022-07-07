package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraftforge.eventbus.api.IEventBus;

public class DeepworldClient {

    public static final IEventBus MOD_EVENT_BUS = Deepworld.MOD_EVENT_BUS;

    public DeepworldClient() {

        registerClientAssets();

    }

    public static void registerClientAssets() {

    }
}
