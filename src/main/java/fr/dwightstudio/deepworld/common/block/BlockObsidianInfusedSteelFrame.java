package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldBlockRegister;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockObsidianInfusedSteelFrame extends BlockFrame {
    public BlockObsidianInfusedSteelFrame() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(25);
        setResistance(18);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME);
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return 3;
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "pickaxe";
    }
}
