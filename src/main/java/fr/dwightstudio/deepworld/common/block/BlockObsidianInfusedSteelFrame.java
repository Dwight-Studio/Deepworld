package fr.dwightstudio.deepworld.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockObsidianInfusedSteelFrame extends BlockFrame {

    public BlockObsidianInfusedSteelFrame() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(25, 18)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE)
                .notSolid());
    }
}
