package fr.dwightstudio.deepworld.common.frame;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public enum WoodenFrameComponent {

    // Components
    SIMPLE_PRESSING_CHAMBER(
            "simple_pressing_chamber",
            ComponentClass.PRIMARY,
            new String[] {"wooden_press"}
    ),
    WOODEN_GEARBOX(
            "wooden_gearbox",
            ComponentClass.SECONDARY,
            new String[] {"wooden_press", "wooden_gear_shaper"}
            ),
    WOODEN_CRANK(
            "wooden_crank",
            ComponentClass.TERTIARY,
            new String[] {"wooden_press", "wooden_gear_shaper"}
            ),
    SIMPLE_CUTTER(
            "simple_cutter",
            ComponentClass.PRIMARY,
            new String[] {"wooden_gear_shaper"}
    );

    // Var
    private final String item;
    private final ComponentClass componentClass;
    private final String[] machineBlocks;
    private final int ID;
    
    // Static neasted class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    WoodenFrameComponent(String item, ComponentClass componentClass, String[] machineBlocks) {
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
        return Item.REGISTRY.getObject(new ResourceLocation(Deepworld.MOD_ID, this.item));
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public Block[] getMachineBlocks() {
        List<Block> rtn = new ArrayList<Block>();

        for (String block : this.machineBlocks) {
            rtn.add(Block.REGISTRY.getObject(new ResourceLocation(Deepworld.MOD_ID, block)));
        }

        return (Block[]) rtn.toArray(new Block[0]);
    }

    public int getID() {
        return this.ID;
    }
}
