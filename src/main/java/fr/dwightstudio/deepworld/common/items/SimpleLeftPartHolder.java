package fr.dwightstudio.deepworld.common.items;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.items.component.WoodenFrameComponentItem;

public class SimpleLeftPartHolder extends WoodenFrameComponentItem {
    public SimpleLeftPartHolder() {
        super(new Properties().tab(Deepworld.MOD_TAB));
    }

    @Override
    public WoodenFrameComponent getComponent() {
        return WoodenFrameComponent.SIMPLE_LEFT_PART_HOLDER;
    }
}
