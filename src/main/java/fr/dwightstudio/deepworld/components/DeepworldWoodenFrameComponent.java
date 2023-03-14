package fr.dwightstudio.deepworld.components;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum DeepworldWoodenFrameComponent {
    SIMPLE_PRESSING_CHAMBER(
            DeepworldItems.SIMPLE_PRESSING_CHAMBER,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{()-> DeepworldBlocks.WOODEN_PRESS}
    ),
    WOODEN_GEARBOX(
            DeepworldItems.WOODEN_GEARBOX,
            ComponentClass.SECONDARY,
            new ComponentMachine[]{()-> DeepworldBlocks.WOODEN_PRESS, ()->DeepworldBlocks.WOODEN_GEAR_SHAPER}
    ),
    WOODEN_CRANK(DeepworldItems.WOODEN_CRANK,
            ComponentClass.TERTIARY,
            new ComponentMachine[]{()->DeepworldBlocks.WOODEN_PRESS, ()->DeepworldBlocks.WOODEN_GEAR_SHAPER}
    ),
    SIMPLE_CUTTER(DeepworldItems.SIMPLE_CUTTER,
            ComponentClass.PRIMARY,
            new ComponentMachine[]{()->DeepworldBlocks.WOODEN_GEAR_SHAPER}
    ),
    SIMPLE_LEFT_PART_HOLDER(DeepworldItems.SIMPLE_LEFT_PART_HOLDER,
            ComponentClass.SECONDARY,
            new ComponentMachine[]{()->DeepworldBlocks.WOODEN_LATHE}
    ),
    SIMPLE_RIGHT_PART_HOLDER(DeepworldItems.SIMPLE_RIGHT_PART_HOLDER,
            ComponentClass.TERTIARY,
            new ComponentMachine[]{()->DeepworldBlocks.WOODEN_LATHE}
    );

    // Var
    private final Item item; // <- Should be ComponentItem
    private final ComponentClass componentClass;
    private final ComponentMachine[] machineBlocks;
    private final int ID;

    // Static nested class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    DeepworldWoodenFrameComponent(Item item, ComponentClass componentClass, ComponentMachine[] machineBlocks){
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

    public static DeepworldWoodenFrameComponent getByID(int ID, ComponentClass componentClass) {
        for (DeepworldWoodenFrameComponent component : DeepworldWoodenFrameComponent.values()) {
            if (component.getComponentClass() == componentClass && component.getID() == ID) {
                return component;
            }
        }
        return null;
    }

    public static DeepworldWoodenFrameComponent[] getByMachine(Block sMachineBlock) {
        DeepworldWoodenFrameComponent[] rtn = {null, null, null};
        for (DeepworldWoodenFrameComponent component : DeepworldWoodenFrameComponent.values()) {
            for (Block machineBlock : component.getMachineBlocks()) {
                if (machineBlock == sMachineBlock) {
                    rtn[component.getComponentClass().getIndex()] = component;
                    break;
                }
            }
        }
        return rtn;
    }

    public static Block getResultFromTile(WoodenFrameBlockEntity tile) {
        return getResult(tile.getPrimaryComponent(), tile.getSecondaryComponent(), tile.getTertiaryComponent());
    }

    public static Block getResult(int ci1, int ci2, int ci3) {
        DeepworldWoodenFrameComponent c1 = getByID(ci1, ComponentClass.PRIMARY);
        DeepworldWoodenFrameComponent c2 = getByID(ci2, ComponentClass.SECONDARY);
        DeepworldWoodenFrameComponent c3 = getByID(ci3, ComponentClass.TERTIARY);

        return getResult(c1, c2, c3);
    }

    public static Block getResult(DeepworldWoodenFrameComponent c1, DeepworldWoodenFrameComponent c2, DeepworldWoodenFrameComponent c3) {
        if (c1 == null || c2 == null || c3 == null) return null;

        for (Block PrimaryMachineBlock : c1.getMachineBlocks()) {
            for (Block SecondaryMachineBlock : c2.getMachineBlocks()) {
                for (Block TertiaryMachineBlock : c3.getMachineBlocks()) {
                    if (PrimaryMachineBlock == SecondaryMachineBlock && SecondaryMachineBlock == TertiaryMachineBlock)
                        return PrimaryMachineBlock;
                }
            }
        }
        return null;
    }

    public Item getItem() {
        return item.asItem();
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public Block[] getMachineBlocks() {
        Block[] rtn = new Block[machineBlocks.length];
        int i = 0;
        for (ComponentMachine machineBlock : machineBlocks) {
            rtn[i] = machineBlock.get();
            i++;
        }
        return rtn;
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
