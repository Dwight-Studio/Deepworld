package fr.dwightstudio.deepworld.common.tile;

import net.minecraft.item.ItemStack;
import org.antlr.v4.runtime.misc.NotNull;

public interface ITileEntityWoodenMachine {

    boolean isThereRecipeForInput(ItemStack sourceItemStack);

    @NotNull
    boolean isItemValidForInputSlot(ItemStack sourceItemStack);

    @NotNull
    boolean isItemValidForOutputSlot(ItemStack sourceItemStack);

    @NotNull
    float getVolume();
}
