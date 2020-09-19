package fr.dwightstudio.deepworld.common.frame;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.block.BlockFrame;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.lwjgl.system.CallbackI;

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

    public static Block getResultFromTile(TileEntityWoodenFrame tile) {
        return getResult(tile.getPrimaryComponent(), tile.getSecondaryComponent(), tile.getTertiaryComponent());
    }

    public static Block getResult(int ci1, int ci2, int ci3) {
        WoodenFrameComponent c1 = getByID(ci1);
        WoodenFrameComponent c2 = getByID(ci2);
        WoodenFrameComponent c3 = getByID(ci3);

        return getResult(c1, c2, c3);
    }

    public static Block getResult(WoodenFrameComponent c1, WoodenFrameComponent c2, WoodenFrameComponent c3) {
        if (c1 == null || c2 == null || c3 == null) return null;
        Deepworld.logger.info("---");

        for (Block PrimaryMachineBlock : c1.getMachineBlocks()) {
            for (Block SecondaryMachineBlock : c2.getMachineBlocks()) {
                for (Block TertiaryMachineBlock : c3.getMachineBlocks()) {
                    Deepworld.logger.info(PrimaryMachineBlock.getRegistryName().getPath() + " " + SecondaryMachineBlock.getRegistryName().getPath() + " " + TertiaryMachineBlock.getRegistryName().getPath());
                    if (PrimaryMachineBlock == SecondaryMachineBlock && SecondaryMachineBlock == TertiaryMachineBlock) return PrimaryMachineBlock;
                }
            }
        }
        Deepworld.logger.info("NF");
        return null;
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
