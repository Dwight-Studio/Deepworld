package fr.dwightstudio.deepworld.client;

import fr.dwightstudio.deepworld.common.*;
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
@Mod.EventBusSubscriber(Side.CLIENT)
public class DeepworldClient extends Deepworld {

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {

        // Register itemBlocks
        registerBlockModel(DeepworldBlocks.WOODEN_FRAME, 0);
        registerBlockModel(DeepworldBlocks.WOODEN_PRESS, 0);
        registerBlockModel(DeepworldBlocks.IRON_FRAME, 0);
        registerBlockModel(DeepworldBlocks.STEEL_FRAME, 0);
        registerBlockModel(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME, 0);

        // Register items
        registerItemModel(DeepworldItems.WOODEN_CASE_PANEL, 0);
        registerItemModel(DeepworldItems.WOODEN_GEARBOX, 0);
        registerItemModel(DeepworldItems.WOODEN_CRANK, 0);
        registerItemModel(DeepworldItems.SIMPLE_PRESSING_CHAMBER, 0);
        registerItemModel(DeepworldItems.WOODEN_GEAR, 0);
    }

    private static void registerBlockModel(Block block, int metadata) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(block.getRegistryName(),"inventory"));
    }

    private static void registerItemModel(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }

}
