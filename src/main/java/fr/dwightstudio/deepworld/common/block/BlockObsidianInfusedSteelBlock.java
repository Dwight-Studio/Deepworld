package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.List;

public class BlockObsidianInfusedSteelBlock extends BlockFrame {

    public BlockObsidianInfusedSteelBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(50, 1200)
                .harvestLevel(3)
                .harvestTool(ToolType.PICKAXE));
    }


    // Setting drops
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        drops.add(new ItemStack(DeepworldItems.OBSIDIAN_INFUSED_STEEL_BLOCK, 1));

        return drops;
    }
}
