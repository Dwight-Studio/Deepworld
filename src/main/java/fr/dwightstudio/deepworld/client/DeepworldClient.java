package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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

        logger.info("Registering items models...");

        // Register itemBlocks
        registerBlockModel(DeepworldItems.WOODEN_CASING, 0, DeepworldBlocks.WOODEN_CASING);
        registerBlockModel(DeepworldItems.IRON_CASING, 0, DeepworldBlocks.IRON_CASING);

        // Register items

        logger.info("Done!");
    }

    private static void registerBlockModel(Item item, int metadata, Block block) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(block.getRegistryName(),"inventory"));
    }

    private static void registerItemModel(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }

}
