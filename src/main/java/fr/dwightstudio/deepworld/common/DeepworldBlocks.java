package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.ObsidianInfusedSteelBlockBlock;
import fr.dwightstudio.deepworld.common.block.SteelBlockBlock;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlocks {

    public static RegistryObject<SteelBlockBlock> STEEL_BLOCK;
    public static RegistryObject<ObsidianInfusedSteelBlockBlock> OBSIDIAN_INFUSED_STEEL_BLOCK;

    public DeepworldBlocks() {
        STEEL_BLOCK = Deepworld.BLOCKS.register("steel_block", SteelBlockBlock::new);
        OBSIDIAN_INFUSED_STEEL_BLOCK = Deepworld.BLOCKS.register("obsidian_infused_steel_block", ObsidianInfusedSteelBlockBlock::new);
    }
}
