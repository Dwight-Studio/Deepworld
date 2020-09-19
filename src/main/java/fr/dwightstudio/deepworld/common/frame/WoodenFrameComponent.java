package fr.dwightstudio.deepworld.common.frame;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public enum WoodenFrameComponent {

    // Components
    SIMPLE_PRESSING_CHAMBER(
            DeepworldItems.SIMPLE_PRESSING_CHAMBER,
            ComponentClass.PRIMARY,
            new ResourceLocation[] {new ResourceLocation(Deepworld.MOD_ID, "wooden_press")}
    ),
    WOODEN_GEARBOX(
            DeepworldItems.WOODEN_GEARBOX,
            ComponentClass.SECONDARY,
            new ResourceLocation[] {new ResourceLocation(Deepworld.MOD_ID, "wooden_press"), new ResourceLocation(Deepworld.MOD_ID, "wooden_gear_shaper")}
            ),
    WOODEN_CRANK(
            DeepworldItems.WOODEN_CRANK,
            ComponentClass.TERTIARY,
            new ResourceLocation[] {new ResourceLocation(Deepworld.MOD_ID, "wooden_press"), new ResourceLocation(Deepworld.MOD_ID, "wooden_gear_shaper")}
            ),
    SIMPLE_CUTTER(
            DeepworldItems.SIMPLE_CUTTER,
            ComponentClass.PRIMARY,
            new ResourceLocation[] {new ResourceLocation(Deepworld.MOD_ID, "wooden_gear_shaper")}
    );

    // Var
    private final Item item;
    private final ComponentClass componentClass;
    private final ResourceLocation[] machineBlocks;
    private final int ID;
    
    // Static nested class to store LastData.lastID
    private static class LastData {
        private static int[] lastID = {0, 0, 0};
    }

    WoodenFrameComponent(Item item, ComponentClass componentClass, ResourceLocation[] machineBlocks) {
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

    public static WoodenFrameComponent getByID(int ID, ComponentClass componentClass) {
        for (WoodenFrameComponent component : WoodenFrameComponent.values()) {
            if (component.getComponentClass() == componentClass && component.getID() == ID) {
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
        WoodenFrameComponent c1 = getByID(ci1, ComponentClass.PRIMARY);
        WoodenFrameComponent c2 = getByID(ci2, ComponentClass.SECONDARY);
        WoodenFrameComponent c3 = getByID(ci3, ComponentClass.TERTIARY);

        return getResult(c1, c2, c3);
    }

    public static Block getResult(WoodenFrameComponent c1, WoodenFrameComponent c2, WoodenFrameComponent c3) {
        if (c1 == null || c2 == null || c3 == null) return null;

        Deepworld.logger.info(c1.getMachineBlocks().length * c2.getMachineBlocks().length * c3.getMachineBlocks().length);

        for (Block PrimaryMachineBlock : c1.getMachineBlocks()) {
            for (Block SecondaryMachineBlock : c2.getMachineBlocks()) {
                for (Block TertiaryMachineBlock : c3.getMachineBlocks()) {
                    Deepworld.logger.info(PrimaryMachineBlock.getRegistryName().toString() + " " + SecondaryMachineBlock.getRegistryName().toString() + " " + TertiaryMachineBlock.getRegistryName().toString());
                    if (PrimaryMachineBlock == SecondaryMachineBlock && SecondaryMachineBlock == TertiaryMachineBlock) return PrimaryMachineBlock;
                }
            }
        }
        return null;
    }

    public Item getItem() {
        return item;
    }

    public ComponentClass getComponentClass() {
        return this.componentClass;
    }

    public Block[] getMachineBlocks() {
        Block[] rtn = new Block[machineBlocks.length];

        int i = 0;
        for (ResourceLocation machineBlock : machineBlocks) {
            rtn[i] = ForgeRegistries.BLOCKS.getValue(machineBlock);
            i++;
        }

        return rtn;
    }

    public int getID() {
        return this.ID;
    }
}
