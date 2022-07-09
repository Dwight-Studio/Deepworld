package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.blocks.*;
import fr.dwightstudio.deepworld.common.blocks.machines.wood.WoodenFrameBlock;
import fr.dwightstudio.deepworld.common.blocks.machines.wood.WoodenLatheBlock;
import fr.dwightstudio.deepworld.common.blocks.machines.wood.WoodenPressBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlocks {

    public static RegistryObject<Block> STEEL_BLOCK;
    public static RegistryObject<Block> OBSIDIAN_INFUSED_STEEL_BLOCK;

    // Frames
    public static RegistryObject<Block> WOODEN_FRAME;
    public static RegistryObject<Block> IRON_FRAME; // TODO: Add this
    public static RegistryObject<Block> STEEL_FRAME;
    public static RegistryObject<Block> OBSIDIAN_INFUSED_STEEL_FRAME;

    //Machines
    public static RegistryObject<Block> WOODEN_PRESS;
    public static RegistryObject<Block> WOODEN_GEAR_SHAPER;
    public static RegistryObject<Block> WOODEN_LATHE;


    public DeepworldBlocks() {
        STEEL_BLOCK = Deepworld.BLOCKS.register("steel_block", SteelBlockBlock::new);
        OBSIDIAN_INFUSED_STEEL_BLOCK = Deepworld.BLOCKS.register("obsidian_infused_steel_block", ObsidianInfusedSteelBlockBlock::new);

        // Frames
        WOODEN_FRAME = Deepworld.BLOCKS.register("wooden_frame", WoodenFrameBlock::new);
        //STEEL_FRAME = Deepworld.BLOCKS.register("steel_frame", SteelFrameBlock::new);
        //OBSIDIAN_INFUSED_STEEL_FRAME = Deepworld.BLOCKS.register("obsidian_infused_steel_frame", ObsidianInfusedSteelFrame::new);

        // Machines
        WOODEN_PRESS = Deepworld.BLOCKS.register("wooden_press", WoodenPressBlock::new);
        //WOODEN_GEAR_SHAPER = Deepworld.BLOCKS.register("wooden_gear_shaper", WoodenGearShaperBlock::new);
        WOODEN_LATHE = Deepworld.BLOCKS.register("wooden_lathe", WoodenLatheBlock::new);
    }
}
