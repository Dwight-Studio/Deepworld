package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SimpleDeepworldBlockItem extends BlockItem {

    public SimpleDeepworldBlockItem(Block block) {
        super(block, new Item.Properties().tab(Deepworld.MOD_TAB));
    }
}
