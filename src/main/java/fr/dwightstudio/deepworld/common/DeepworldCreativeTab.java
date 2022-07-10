package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.registries.DeepworldItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DeepworldCreativeTab extends CreativeModeTab {

    public DeepworldCreativeTab() {
        super("tabDeepworld");
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return DeepworldItems.STEEL_BLOCK.get().getDefaultInstance();
    }
}
