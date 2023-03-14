package fr.dwightstudio.deepworld.item.parts;

import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import fr.dwightstudio.deepworld.item.parts.component.WoodenFrameComponentItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class SimplePressingChamberItem extends WoodenFrameComponentItem {

    public SimplePressingChamberItem() {
        super(new FabricItemSettings());
    }

    @Override
    public DeepworldWoodenFrameComponent getComponent(){
        return DeepworldWoodenFrameComponent.SIMPLE_PRESSING_CHAMBER;
    }

}
