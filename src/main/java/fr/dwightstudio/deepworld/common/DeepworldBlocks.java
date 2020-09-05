package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.*;
import net.minecraft.block.Block;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class DeepworldBlocks {

    // Blocks initialization
    public static final Block WOODEN_FRAME;
    public static final Block WOODEN_PRESS;
    public static final Block WOODEN_ROLLING_MACHINE;
    public static final Block IRON_FRAME;
    public static final Block STEEL_FRAME;
    public static final Block OBSIDIAN_INFUSED_STEEL_FRAME;

    // Constants init
    static {
        WOODEN_PRESS = getRegisteredBlock("wooden_press");
        WOODEN_FRAME = getRegisteredBlock("wooden_frame");
        WOODEN_ROLLING_MACHINE = getRegisteredBlock("wooden_rolling_machine");
        IRON_FRAME = getRegisteredBlock("iron_frame");
        STEEL_FRAME = getRegisteredBlock("steel_frame");
        OBSIDIAN_INFUSED_STEEL_FRAME = getRegisteredBlock("obsidian_infused_steel_frame");
    }

    private static Block getRegisteredBlock(String name)
    {
        Block block = Block.REGISTRY.getObject(new ResourceLocation(Deepworld.MOD_ID, name));

        if (block == null)
        {
            throw new IllegalStateException("Invalid Block requested: " + name);
        }
        else
        {
            return block;
        }
    }
}
