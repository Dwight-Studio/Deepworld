package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import net.minecraft.world.item.Item;

public class ItemWoodenCrank extends ItemWoodenFrameComponent {

    public ItemWoodenCrank() {
        super(new Item.Properties().tab(Deepworld.itemGroup));
    }

    @Override
    public WoodenFrameComponent getComponent() {
        return WoodenFrameComponent.WOODEN_CRANK;
    }
}
