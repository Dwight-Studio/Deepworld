package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import net.minecraft.world.item.Item;

public class ItemSimpleRightPartHolder extends ItemWoodenFrameComponent {

    public ItemSimpleRightPartHolder() {
        super(new Item.Properties().group(Deepworld.itemGroup));
    }

    @Override
    public WoodenFrameComponent getComponent() {
        return WoodenFrameComponent.SIMPLE_RIGHT_PART_HOLDER;
    }
}
