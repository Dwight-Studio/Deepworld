package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockObsidianInfusedSteelFrame extends Block {
    public BlockObsidianInfusedSteelFrame() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setCreativeTab(Deepworld.creativeTab);
        setHardness(25);
        setResistance(18);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return DeepworldItems.OBSIDIAN_INFUSED_STEEL_FRAME;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
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