package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldBlockRegister;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockIronFrame extends BlockFrame {
    public BlockIronFrame() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(4);
        setResistance(3);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(DeepworldBlocks.IRON_FRAME);
    }

    /*
     * Setting custom harvest level & tool
     * -> Harvest level
     *      0 - wood
     *      1 - stone
     *      2 - iron
     *      3 - diamond
     *      >3 - custom (mod implemented tools)
     *
     *  -> Harvest tool
     *      pickaxe
     *      axe
     *      shovel
     *      sword
     *      hoe
     */

    @Override
    public int getHarvestLevel(IBlockState state) {
        return 1;
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "pickaxe";
    }
}
