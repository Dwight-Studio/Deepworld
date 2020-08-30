package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber()
public class DeepworldClient extends Deepworld {

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {

        logger.debug("Registering items models...");

        // Register itemBlocks
        registerBlockModel(DeepworldItems.WOODEN_FRAME, 0, DeepworldBlocks.WOODEN_FRAME);
        registerBlockModel(DeepworldItems.WOODEN_PRESS, 0, DeepworldBlocks.WOODEN_PRESS);
        registerBlockModel(DeepworldItems.IRON_FRAME, 0, DeepworldBlocks.IRON_FRAME);
        registerBlockModel(DeepworldItems.STEEL_FRAME, 0, DeepworldBlocks.STEEL_FRAME);
        registerBlockModel(DeepworldItems.OBSIDIAN_INFUSED_STEEL_FRAME, 0, DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME);

        // Register items
        registerItemModel(DeepworldItems.WOODEN_CASE_PANEL, 0);
        registerItemModel(DeepworldItems.WOODEN_GEARBOX, 0);
        registerItemModel(DeepworldItems.WOODEN_CRANK, 0);
        registerItemModel(DeepworldItems.SIMPLE_PRESSING_CHAMBER, 0);

        logger.debug("Done!");
    }

    private static void registerBlockModel(Item item, int metadata, Block block) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(block.getRegistryName(),"inventory"));
    }

    private static void registerItemModel(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }

}
