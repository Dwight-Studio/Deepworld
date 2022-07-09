package fr.dwightstudio.deepworld.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class SteelBlockBlock extends Block {


    public SteelBlockBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .strength(5.0f, 6.0f));
    }
}
