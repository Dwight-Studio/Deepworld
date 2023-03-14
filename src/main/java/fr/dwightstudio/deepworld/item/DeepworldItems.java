package fr.dwightstudio.deepworld.item;

import fr.dwightstudio.deepworld.Deepworld;
import fr.dwightstudio.deepworld.item.parts.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

    // Tools
    public static final Item STEEL_PICKAXE = registerItem("steel_pickaxe", new PickaxeItem(DeepworldToolMaterial.STEEL, 3, -2.8f, new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_SWORD = registerItem("steel_sword", new SwordItem(DeepworldToolMaterial.STEEL, 5, -2.4f, new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_AXE = registerItem("steel_axe", new AxeItem(DeepworldToolMaterial.STEEL, 8, -3.2f, new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_SHOVEL = registerItem("steel_shovel", new ShovelItem(DeepworldToolMaterial.STEEL, 3.5f, -2.8f, new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_HOE = registerItem("steel_hoe", new HoeItem(DeepworldToolMaterial.STEEL, 3, -2.8f, new FabricItemSettings().maxCount(1)));

    public static final Item OBSIDIAN_INFUSED_STEEL_PICKAXE = registerItem("obsidian_infused_steel_pickaxe", new PickaxeItem(DeepworldToolMaterial.OBSIDIAN_INFUSED_STEEL, 4, -2.8f, new FabricItemSettings().maxCount(1)));
    public static final Item OBSIDIAN_INFUSED_STEEL_SWORD = registerItem("obsidian_infused_steel_sword", new SwordItem(DeepworldToolMaterial.OBSIDIAN_INFUSED_STEEL, 9, -2.2f, new FabricItemSettings().maxCount(1)));
    public static final Item OBSIDIAN_INFUSED_STEEL_AXE = registerItem("obsidian_infused_steel_axe", new AxeItem(DeepworldToolMaterial.OBSIDIAN_INFUSED_STEEL, 12, -3.4f, new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_SHOVEL = registerItem("obsidian_infused_steel_shovel", new ShovelItem(DeepworldToolMaterial.OBSIDIAN_INFUSED_STEEL, 4.5f, -2.8f, new FabricItemSettings().maxCount(1)));
    public static final Item OBSIDIAN_INFUSED_STEEL_HOE = registerItem("obsidian_infused_steel_hoe", new HoeItem(DeepworldToolMaterial.OBSIDIAN_INFUSED_STEEL, 4, -2.8f, new FabricItemSettings().maxCount(1)));

    // Armors
    public static final Item STEEL_HELMET = registerItem("steel_helmet", new ArmorItem(DeepworldArmorMaterials.STEEL, EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item STEEL_CHESTPLATE = registerItem("steel_chestplate", new ArmorItem(DeepworldArmorMaterials.STEEL, EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item STEEL_LEGGINGS = registerItem("steel_leggings", new ArmorItem(DeepworldArmorMaterials.STEEL, EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item STEEL_BOOTS = registerItem("steel_boots", new ArmorItem(DeepworldArmorMaterials.STEEL, EquipmentSlot.FEET, new FabricItemSettings()));

    public static final Item OBSIDIAN_INFUSED_STEEL_HELMET = registerItem("obsidian_infused_steel_helmet", new ArmorItem(DeepworldArmorMaterials.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_CHESTPLATE = registerItem("obsidian_infused_steel_chestplate", new ArmorItem(DeepworldArmorMaterials.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_LEGGINGS = registerItem("obsidian_infused_steel_leggings", new ArmorItem(DeepworldArmorMaterials.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item OBSIDIAN_INFUSED_STEEL_BOOTS = registerItem("obsidian_infused_steel_boots", new ArmorItem(DeepworldArmorMaterials.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.FEET, new FabricItemSettings()));

    // Machine components
    public static final Item SIMPLE_PRESSING_CHAMBER = registerItem("simple_pressing_chamber", new SimplePressingChamberItem());
    public static final Item WOODEN_GEARBOX = registerItem("wooden_gearbox", new WoodenGearboxItem());
    public static final Item WOODEN_CRANK = registerItem("wooden_crank", new WoodenCrankItem());
    public static final Item SIMPLE_CUTTER = registerItem("simple_cutter", new SimpleCutter());
    public static final Item SIMPLE_LEFT_PART_HOLDER = registerItem("simple_left_part_holder", new SimpleLeftPartHolder());
    public static final Item SIMPLE_RIGHT_PART_HOLDER = registerItem("simple_right_part_holder", new SimpleRightPartHolder());
    public static final Item WOODEN_CASE_PANEL = registerItem("wooden_case_panel", new WoodenCasePanelItem());

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

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_PICKAXE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_SWORD);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_AXE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_SHOVEL);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_HOE);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_PICKAXE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_SWORD);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_AXE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_SHOVEL);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_HOE);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_HELMET);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_CHESTPLATE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_LEGGINGS);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, STEEL_BOOTS);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_HELMET);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_CHESTPLATE);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_LEGGINGS);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, OBSIDIAN_INFUSED_STEEL_BOOTS);

        addToItemGroup(DeepworldItemGroup.DEEPWORLD, SIMPLE_PRESSING_CHAMBER);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, WOODEN_GEARBOX);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, WOODEN_CRANK);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, SIMPLE_CUTTER);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, SIMPLE_LEFT_PART_HOLDER);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, SIMPLE_RIGHT_PART_HOLDER);
        addToItemGroup(DeepworldItemGroup.DEEPWORLD, WOODEN_CASE_PANEL);
    }

    private static void addToItemGroup(ItemGroup group, Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems(){
        Deepworld.LOGGER.info("Registering Mod Items for " + Deepworld.MOD_ID);

        addItemsToItemGroup();
    }

}
