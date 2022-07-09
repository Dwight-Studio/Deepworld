package fr.dwightstudio.deepworld.common.blocks.frames;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class ObsidianInfusedSteelFrame extends Block {
    public ObsidianInfusedSteelFrame() {
        super(Properties.copy(Blocks.OBSIDIAN)
                .sound(SoundType.METAL));
    }
}
