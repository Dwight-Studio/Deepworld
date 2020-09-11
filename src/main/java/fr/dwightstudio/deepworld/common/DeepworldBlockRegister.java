package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class DeepworldBlockRegister {

    // Blocks registration
    @SubscribeEvent
    public static void onBlocksRegistration(final RegistryEvent.Register<Block> event) {

        Block WOODEN_FRAME = new BlockWoodenFrame();
        Block WOODEN_PRESS =  new BlockWoodenPress();
        Block WOODEN_GEAR_SHAPER = new BlockWoodenGearShaper();
        Block IRON_FRAME = new BlockIronFrame();
        Block STEEL_FRAME = new BlockSteelFrame();
        Block OBSIDIAN_INFUSED_STEEL_FRAME = new BlockObsidianInfusedSteelFrame();
        Block STEEL_BLOCK = new BlockSteelBlock();

        // Set render type
        RenderTypeLookup.setRenderLayer(WOODEN_FRAME, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(IRON_FRAME, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(STEEL_FRAME, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OBSIDIAN_INFUSED_STEEL_FRAME, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WOODEN_GEAR_SHAPER, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WOODEN_PRESS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(STEEL_BLOCK, RenderType.getSolid());

        event.getRegistry().register(prepareBlock(WOODEN_FRAME, "wooden_frame"));
        event.getRegistry().register(prepareBlock(WOODEN_PRESS, "wooden_press"));
        event.getRegistry().register(prepareBlock(WOODEN_GEAR_SHAPER, "wooden_gear_shaper"));
        event.getRegistry().register(prepareBlock(IRON_FRAME, "iron_frame"));
        event.getRegistry().register(prepareBlock(STEEL_FRAME, "steel_frame"));
        event.getRegistry().register(prepareBlock(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
        event.getRegistry().register(prepareBlock(STEEL_BLOCK, "steel_block"));
    }

    // Prepare block
    public static Block prepareBlock(Block block, String name) {
        return block.setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
