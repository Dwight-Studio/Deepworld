package fr.dwightstudio.deepworld.common.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class ObsidianInfusedSteelBlockBlock extends FrameBlock {
    public ObsidianInfusedSteelBlockBlock() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.METAL));
    }
}
