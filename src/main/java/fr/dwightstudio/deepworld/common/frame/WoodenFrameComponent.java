package fr.dwightstudio.deepworld.common.frame;

import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum WoodenFrameComponent {

    // Components
    SIMPLE_PRESSING_CHAMBER(
            DeepworldItems.SIMPLE_PRESSING_CHAMBER,
            ComponentClass.PRIMARY,
            new Block[] {DeepworldBlocks.WOODEN_PRESS}
    ),
    WOODEN_GEARBOX(
            DeepworldItems.WOODEN_GEARBOX,
            ComponentClass.SECONDARY,
            new Block[] {DeepworldBlocks.WOODEN_PRESS, DeepworldBlocks.WOODEN_GEAR_SHAPER}
            ),
    WOODEN_CRANK(
            DeepworldItems.WOODEN_CRANK,
            ComponentClass.TERTIARY,
            new Block[] {DeepworldBlocks.WOODEN_PRESS, DeepworldBlocks.WOODEN_GEAR_SHAPER}
            ),
    SIMPLE_CUTTER(
            DeepworldItems.SIMPLE_CUTTER,
            ComponentClass.PRIMARY,
            new Block[] {DeepworldBlocks.WOODEN_GEAR_SHAPER}
    );

    // Var
    private final Item item;
    private final ComponentClass componentClass;
    private final Block[] machineBlocks;
    private final int ID;
    
    // Static neasted class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    WoodenFrameComponent(Item item, ComponentClass componentClass, Block[] machineBlocks) {
        this.item = item;
        this.componentClass = componentClass;
        this.machineBlocks = machineBlocks;
        ID = createID(this.componentClass);
    }

    private int createID(ComponentClass componentClass) {
        LastData.lastID[componentClass.getIndex()] = LastData.lastID[componentClass.getIndex()] + 1;
        return LastData.lastID[componentClass.getIndex()];
    }

    public static int getLastID(ComponentClass componentClass) {
        return LastData.lastID[componentClass.getIndex()];
    }

    public static WoodenFrameComponent getByID(int ID) {
        for (WoodenFrameComponent component : WoodenFrameComponent.values()) {
            if (component.getID() == ID) {
                return component;
            }
        }

        return null;
    }

    public static WoodenFrameComponent[] getByMachine(Block sMachineBlock) {
        WoodenFrameComponent[] rtn = {null, null, null};

        for (WoodenFrameComponent component : WoodenFrameComponent.values()) {
            for (Block machineBlock : component.getMachineBlocks()) {
                if (machineBlock == sMachineBlock) {
                    rtn[component.getComponentClass().getIndex()] = component;
                    break;
                }
            }
        }

        return rtn;
    }

    public Item getItem() {
        return item;
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public Block[] getMachineBlocks() {
        return machineBlocks;
    }

    public int getID() {
        return this.ID;
    }
}
