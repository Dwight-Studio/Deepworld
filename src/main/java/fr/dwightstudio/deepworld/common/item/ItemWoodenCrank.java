package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;

public class ItemWoodenCrank extends ItemWoodenFrameComponent {

    public ItemWoodenCrank() {
        super(new Item.Properties().group(Deepworld.itemGroup));
    }

    @Override
    public WoodenFrameComponent getComponent() {
        return WoodenFrameComponent.WOODEN_CRANK;
    }
}
