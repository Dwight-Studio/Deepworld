package fr.dwightstudio.deepworld.common.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class SteelFrameBlock extends FrameBlock {
    public SteelFrameBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL));
    }
}
