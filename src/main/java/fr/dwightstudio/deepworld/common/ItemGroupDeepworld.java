package fr.dwightstudio.deepworld.common;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupDeepworld extends ItemGroup {
    public ItemGroupDeepworld() {
        super("tabDeepworld");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(DeepworldBlocks.WOODEN_FRAME);
    }
}
