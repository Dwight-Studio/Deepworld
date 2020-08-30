package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldBlocks {

    // Blocks initialization
    public static final Block WOODEN_FRAME = new BlockWoodenFrame();
    public static final Block WOODEN_PRESS =  new BlockWoodenPress();
    public static final Block IRON_FRAME = new BlockIronFrame();
    public static final Block STEEL_FRAME = new BlockSteelFrame();
    public static final Block OBSIDIAN_INFUSED_STEEL_FRAME = new BlockObsidianInfusedSteelFrame();

    // Blocks registration
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(prepareBlock(WOODEN_FRAME, "wooden_frame"));
        registry.register(prepareBlock(WOODEN_PRESS, "wooden_press"));
        registry.register(prepareBlock(IRON_FRAME, "iron_frame"));
        registry.register(prepareBlock(STEEL_FRAME, "steel_frame"));
        registry.register(prepareBlock(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
    }

    // Prepare block
    public static Block prepareBlock(Block block, String name) {
        return block.setTranslationKey(name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
