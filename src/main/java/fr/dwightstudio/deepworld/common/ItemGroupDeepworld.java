package fr.dwightstudio.deepworld.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupDeepworld extends CreativeModeTab {
    public ItemGroupDeepworld() {
        super("tabDeepworld");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(DeepworldBlocks.WOODEN_FRAME);
    }
}
