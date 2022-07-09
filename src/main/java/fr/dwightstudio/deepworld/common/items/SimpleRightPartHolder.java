package fr.dwightstudio.deepworld.common.items;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.items.component.WoodenFrameComponentItem;

public class SimpleRightPartHolder extends WoodenFrameComponentItem {
    public SimpleRightPartHolder() {
        super(new Properties().tab(Deepworld.MOD_TAB));
    }

    @Override
    public WoodenFrameComponent getComponent() {
        return WoodenFrameComponent.SIMPLE_RIGHT_PART_HOLDER;
    }
}
