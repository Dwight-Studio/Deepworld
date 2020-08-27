package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.BlockIronCasing;
import fr.dwightstudio.deepworld.common.block.BlockWoodenCasing;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldBlocks {

    // Declare blocks
    public static final Block WOODEN_CASING = new BlockWoodenCasing();
    public static final Block IRON_CASING = new BlockIronCasing();

    // Register blocks
    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(prepareBlock(WOODEN_CASING, "wooden_casing"));
        registry.register(prepareBlock(IRON_CASING, "iron_casing"));
    }

    // Prepare block
    public static Block prepareBlock(Block block, String name) {
        return block.setTranslationKey(name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
