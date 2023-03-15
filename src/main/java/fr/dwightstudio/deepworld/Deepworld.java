package fr.dwightstudio.deepworld;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.blockentities.DeepworldEntities;
import fr.dwightstudio.deepworld.item.DeepworldItemGroup;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import fr.dwightstudio.deepworld.screen.DeepworldScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deepworld implements ModInitializer {

    public static final String MOD_ID = "deepworld";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        DeepworldItemGroup.registerItemGroups();
        DeepworldItems.registerModItems();
        DeepworldBlocks.registerModBlocks();
        DeepworldEntities.registerBlockEntities();
        DeepworldScreenHandlers.registerAllScreenHandlers();
    }
}
