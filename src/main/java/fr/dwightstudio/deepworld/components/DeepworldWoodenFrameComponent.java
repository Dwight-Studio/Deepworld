package fr.dwightstudio.deepworld.components;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum DeepworldWoodenFrameComponent {
    SIMPLE_PRESSING_CHAMBER(ComponentClass.PRIMARY),
    WOODEN_GEARBOX(ComponentClass.SECONDARY),
    WOODEN_CRANK(ComponentClass.TERTIARY),
    SIMPLE_CUTTER(ComponentClass.PRIMARY),
    SIMPLE_LEFT_PART_HOLDER(ComponentClass.SECONDARY),
    SIMPLE_RIGHT_PART_HOLDER(ComponentClass.TERTIARY);

    // Var
    private final ComponentClass componentClass;
    private final int ID;

    // Static nested class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    DeepworldWoodenFrameComponent(ComponentClass componentClass){
        this.componentClass = componentClass;
        ID = createID(this.componentClass);
    }

    private int createID(ComponentClass componentClass) {
        LastData.lastID[componentClass.getIndex()] = LastData.lastID[componentClass.getIndex()] + 1;
        return LastData.lastID[componentClass.getIndex()];
    }

    public static int getLastID(ComponentClass componentClass) {
        return LastData.lastID[componentClass.getIndex()];
    }

    public static DeepworldWoodenFrameComponent getByID(int ID, ComponentClass componentClass) {
        for (DeepworldWoodenFrameComponent component : DeepworldWoodenFrameComponent.values()) {
            if (component.getComponentClass() == componentClass && component.getID() == ID) {
                return component;
            }
        }
        return null;
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public int getID() {
        return this.ID;
    }

    private interface ComponentItem {
        Item get();
    }

    private interface ComponentMachine {
        Block get();
    }

}
