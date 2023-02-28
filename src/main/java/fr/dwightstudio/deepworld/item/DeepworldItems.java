package fr.dwightstudio.deepworld.item;

import fr.dwightstudio.deepworld.Deepworld;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DeepworldItems {

    // Items
    public static final Item WOODEN_GEAR = registerItem("wooden_gear", new Item(new FabricItemSettings()));
    public static final Item IRON_GEAR = registerItem("iron_gear", new Item(new FabricItemSettings()));
    public static final Item STEEL_GEAR = registerItem("steel_gear", new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_GEAR = registerItem("obsidian_infused_steel_gear", new Item(new FabricItemSettings()));

    public static final Item IRON_NAIL = registerItem("iron_nail", new Item(new FabricItemSettings()));
    public static final Item STEEL_NAIL = registerItem("steel_nail", new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_NAIL = registerItem("obsidian_infused_steel_nail", new Item(new FabricItemSettings()));

    public static final Item IRON_PLATE = registerItem("iron_plate", new Item(new FabricItemSettings()));
    public static final Item STEEL_PLATE = registerItem("steel_plate", new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_PLATE = registerItem("obsidian_infused_steel_plate", new Item(new FabricItemSettings()));

    public static final Item IRON_ROD = registerItem("iron_rod", new Item(new FabricItemSettings()));
    public static final Item STEEL_ROD = registerItem("steel_rod", new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_ROD = registerItem("obsidian_infused_steel_rod", new Item(new FabricItemSettings()));

    public static final Item IRON_SCREW = registerItem("iron_screw", new Item(new FabricItemSettings()));
    public static final Item STEEL_SCREW = registerItem("steel_screw", new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_SCREW = registerItem("obsidian_infused_steel_screw", new Item(new FabricItemSettings()));

    public static final Item IRON_SMALL_GEAR = registerItem("iron_small_gear", new Item(new FabricItemSettings()));

    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_INGOT = registerItem("obsidian_infused_steel_ingot", new Item(new FabricItemSettings()));

    public static final Item IRON_PUMP = registerItem("iron_pump", new Item(new FabricItemSettings()));
    public static final Item IRON_TURBINE = registerItem("iron_turbine", new Item(new FabricItemSettings()));
    public static final Item PRESSURE_REGULATOR = registerItem("pressure_regulator", new Item(new FabricItemSettings()));
    public static final Item VALVE_ASSEMBLY = registerItem("valve_assembly", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Deepworld.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup(){
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, WOODEN_GEAR);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_GEAR);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_GEAR);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_GEAR);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_NAIL);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_NAIL);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_NAIL);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_PLATE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_PLATE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_PLATE);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_ROD);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_ROD);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_ROD);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_SCREW);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_SCREW);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_SCREW);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_SMALL_GEAR);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_INGOT);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_INGOT);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_PUMP);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, IRON_TURBINE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, PRESSURE_REGULATOR);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, VALVE_ASSEMBLY);
    }

    private static void addToItemGroup(ItemGroup group, Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems(){
        Deepworld.LOGGER.info("Registering Mod Items for " + Deepworld.MOD_ID);

        addItemsToItemGroup();
    }

}
