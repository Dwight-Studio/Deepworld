package fr.dwightstudio.deepworld.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockSteelFrame extends BlockFrame {

    public BlockSteelFrame() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(10, 9)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .noDrops());
    }
}
