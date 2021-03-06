package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

public class BlockObsidianInfusedSteelFrame extends BlockFrame {

    public BlockObsidianInfusedSteelFrame() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(25, 18)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE)
                .notSolid());
    }

    // Setting drops
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        drops.add(new ItemStack(DeepworldItems.OBSIDIAN_INFUSED_STEEL_FRAME, 1));

        return drops;
    }
}
