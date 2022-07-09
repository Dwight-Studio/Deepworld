package fr.dwightstudio.deepworld.common.blocks.frames;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SteelFrameBlock extends FrameBlock {
    public SteelFrameBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL));
    }
}
