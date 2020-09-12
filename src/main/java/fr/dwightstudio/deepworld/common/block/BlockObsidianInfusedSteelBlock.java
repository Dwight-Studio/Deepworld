package fr.dwightstudio.deepworld.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockObsidianInfusedSteelBlock extends BlockFrame {

    public BlockObsidianInfusedSteelBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(50, 1200)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE));
    }
}
