package fr.dwightstudio.deepworld;

import fr.dwightstudio.deepworld.item.DeepworldItems;
import org.slf4j.Logger;
import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;

public class Deepworld implements ModInitializer {

    public static final String MOD_ID = "deepworld";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        DeepworldItems.registerModItems();
    }
}
