package fr.dwightstudio.deepworld.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabDeepworld extends CreativeTabs {
    public CreativeTabDeepworld() {
        super("tabDeepworld");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(DeepworldItems.WOODEN_CASING);
    }
}
