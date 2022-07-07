package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.block.component.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.item.component.WoodenFrameComponentItem;

public class SimplePressingChamberItem extends WoodenFrameComponentItem {
    public SimplePressingChamberItem() {
        super(new Properties().tab(Deepworld.MOD_TAB));
    }

    @Override
    public WoodenFrameComponent getComponent() {
        return WoodenFrameComponent.SIMPLE_PRESSING_CHAMBER;
    }
}
