package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldBlockRegister {

    // Blocks registration
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        Block WOODEN_FRAME = new BlockWoodenFrame();
        Block WOODEN_PRESS =  new BlockWoodenPress();
        Block WOODEN_GEAR_SHAPER = new BlockWoodenGearShaper();
        Block IRON_FRAME = new BlockIronFrame();
        Block STEEL_FRAME = new BlockSteelFrame();
        Block OBSIDIAN_INFUSED_STEEL_FRAME = new BlockObsidianInfusedSteelFrame();

        registry.register(prepareBlock(WOODEN_FRAME, "wooden_frame"));
        registry.register(prepareBlock(WOODEN_PRESS, "wooden_press"));
        registry.register(prepareBlock(WOODEN_GEAR_SHAPER, "wooden_gear_shaper"));
        registry.register(prepareBlock(IRON_FRAME, "iron_frame"));
        registry.register(prepareBlock(STEEL_FRAME, "steel_frame"));
        registry.register(prepareBlock(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
    }

    // Prepare block
    public static Block prepareBlock(Block block, String name) {
        return block.setTranslationKey(Deepworld.MOD_ID + "_" + name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
