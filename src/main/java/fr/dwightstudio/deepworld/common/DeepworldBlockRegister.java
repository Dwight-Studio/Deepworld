package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class DeepworldBlockRegister {

    // Blocks registration
    @SubscribeEvent
    public static void onBlocksRegistration(final RegisterEvent event) {

        Block WOODEN_FRAME = new BlockWoodenFrame();
        Block WOODEN_PRESS =  new BlockWoodenPress();
        Block WOODEN_GEAR_SHAPER = new BlockWoodenGearShaper();
        Block WOODEN_LATHE = new BlockWoodenLathe();
        Block STEEL_FRAME = new BlockSteelFrame();
        Block OBSIDIAN_INFUSED_STEEL_FRAME = new BlockObsidianInfusedSteelFrame();
        Block STEEL_BLOCK = new BlockSteelBlock();
        Block OBSIDIAN_INFUSED_STEEL_BLOCK = new BlockObsidianInfusedSteelBlock();
        Block PIPE = new BlockPipe();

        event.register(ForgeRegistries.Keys.BLOCKS, helper -> {
            helper.register(prepareBlock("wooden_frame"), WOODEN_FRAME);
            helper.register(prepareBlock("wooden_press"), WOODEN_PRESS);
            helper.register(prepareBlock("wooden_gear_shaper"), WOODEN_GEAR_SHAPER);
            helper.register(prepareBlock("wooden_lathe"), WOODEN_LATHE);
            helper.register(prepareBlock("steel_frame"), STEEL_FRAME);
            helper.register(prepareBlock("obsidian_infused_steel_frame"), OBSIDIAN_INFUSED_STEEL_FRAME);
            helper.register(prepareBlock("steel_block"), STEEL_BLOCK);
            helper.register(prepareBlock("obsidian_infused_steel_block"), OBSIDIAN_INFUSED_STEEL_BLOCK);
            helper.register(prepareBlock("pipe"), PIPE);
        });

        // Set render type
        ItemBlockRenderTypes.setRenderLayer(WOODEN_FRAME, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(STEEL_FRAME, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(OBSIDIAN_INFUSED_STEEL_FRAME, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WOODEN_GEAR_SHAPER, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WOODEN_LATHE, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WOODEN_PRESS, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(STEEL_BLOCK, RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(OBSIDIAN_INFUSED_STEEL_BLOCK, RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(PIPE, RenderType.solid());
    }

    // Prepare block
    public static ResourceLocation prepareBlock(String name) {
        return new ResourceLocation(Deepworld.MOD_ID, name);
    }
}
