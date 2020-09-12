package fr.dwightstudio.deepworld.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockIronFrame extends BlockFrame {
    public BlockIronFrame() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(4, 3)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .notSolid());
    }
}
