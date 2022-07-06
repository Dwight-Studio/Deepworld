package fr.dwightstudio.deepworld.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class ObsidianInfusedSteelBlockBlock extends Block {


    public ObsidianInfusedSteelBlockBlock() {
        super(Properties.of(Material.METAL)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .strength(5.0f, 6.0f));
    }
}
