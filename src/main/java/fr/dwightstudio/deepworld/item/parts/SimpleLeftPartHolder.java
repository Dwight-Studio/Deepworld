package fr.dwightstudio.deepworld.item.parts;

import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import fr.dwightstudio.deepworld.item.parts.component.WoodenFrameComponentItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class SimpleLeftPartHolder extends WoodenFrameComponentItem {

    public SimpleLeftPartHolder() {
        super(new FabricItemSettings());
    }

    @Override
    public DeepworldWoodenFrameComponent getComponent(){
        return DeepworldWoodenFrameComponent.SIMPLE_LEFT_PART_HOLDER;
    }

}
