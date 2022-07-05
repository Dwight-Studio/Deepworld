package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.armor.ArmorMaterialsTier;
import fr.dwightstudio.deepworld.common.item.*;
import fr.dwightstudio.deepworld.common.tools.ToolsMaterialsTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class DeepworldItemRegister {

    // Items registration
    @SubscribeEvent
    public static void registerItems(final RegisterEvent event) {

        Item WOODEN_CASE_PANEL = new ItemWoodenCasePanel();
        Item SIMPLE_PRESSING_CHAMBER = new ItemSimplePressingChamber();
        Item SIMPLE_CUTTER = new ItemSimpleCutter();
        Item SIMPLE_LEFT_PART_HOLDER = new ItemSimpleLeftPartHolder();
        Item SIMPLE_RIGHT_PART_HOLDER = new ItemSimpleRightPartHolder();
        Item WOODEN_GEARBOX = new ItemWoodenGearbox();
        Item WOODEN_CRANK = new ItemWoodenCrank();
        Item IRON_PLATE = new ItemIronPlate();
        Item STEEL_PLATE = new ItemSteelPlate();
        Item OBSIDIAN_INFUSED_STEEL_PLATE = new ItemObsidianInfusedSteelPlate();
        Item IRON_ROD = new ItemIronRod();
        Item STEEL_ROD = new ItemSteelRod();
        Item OBSIDIAN_INFUSED_STEEL_ROD = new ItemObsidianInfusedSteelRod();
        Item IRON_NAIL = new ItemIronNail();
        Item STEEL_NAIL = new ItemSteelNail();
        Item OBSIDIAN_INFUSED_STEEL_NAIL = new ItemObsidianInfusedSteelNail();
        Item IRON_SCREW = new ItemIronScrew();
        Item STEEL_SCREW = new ItemSteelScrew();
        Item OBSIDIAN_INFUSED_STEEL_SCREW = new ItemObsidianInfusedSteelScrew();
        Item WOODEN_GEAR = new ItemWoodenGear();
        Item IRON_GEAR = new ItemIronGear();
        Item STEEL_GEAR = new ItemSteelGear();
        Item OBSIDIAN_INFUSED_STEEL_GEAR = new ItemObsidianInfusedSteelGear();
        Item STEEL_INGOT = new ItemSteelIngot();
        Item OBSIDIAN_INFUSED_STEEL_INGOT = new ItemObsidianInfusedSteelIngot();
        Item IRON_PUMP = new ItemIronPump();
        Item IRON_SMALL_GEAR = new ItemIronSmallGear();
        Item VALVE_ASSEMBLY = new ItemValveAssembly();
        Item IRON_TURBINE = new ItemIronTurbine();
        Item PRESSURE_REGULATOR = new ItemPressureRegulator();

        //Tools
        Item STEEL_PICKAXE = new PickaxeItem(ToolsMaterialsTier.STEEL, 3, -2.8F, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_SWORD = new SwordItem(ToolsMaterialsTier.STEEL, 5, -2.4F, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_AXE = new AxeItem(ToolsMaterialsTier.STEEL, 8, -3.2F, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_SHOVEL = new ShovelItem(ToolsMaterialsTier.STEEL, 3.5F, -2.8F, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_HOE = new HoeItem(ToolsMaterialsTier.STEEL, 3, -2.8F, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_PICKAXE = new PickaxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4, -2.8F, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_SWORD = new SwordItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 9, -2.2F, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_AXE = new AxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 12, -3.4F, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_SHOVEL = new ShovelItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4.5F, -2.8F, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_HOE = new HoeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4, -2.8F, new Item.Properties().tab(Deepworld.itemGroup));

        //Armors
        Item STEEL_HELMET = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.HEAD, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_CHESTPLATE = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.CHEST, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_LEGGINGS = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.LEGS, new Item.Properties().tab(Deepworld.itemGroup));
        Item STEEL_BOOTS = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.FEET, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_HELMET = new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.HEAD, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_CHESTPLATE = new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.CHEST, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_LEGGINGS = new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.LEGS, new Item.Properties().tab(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_BOOTS = new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.FEET, new Item.Properties().tab(Deepworld.itemGroup));

        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            helper.register(prepare("wooden_case_panel"), WOODEN_CASE_PANEL);
            helper.register(prepare("simple_pressing_chamber"), SIMPLE_PRESSING_CHAMBER);
            helper.register(prepare("simple_cutter"), SIMPLE_CUTTER);
            helper.register(prepare("simple_left_part_holder"), SIMPLE_LEFT_PART_HOLDER);
            helper.register(prepare("simple_right_part_holder"), SIMPLE_RIGHT_PART_HOLDER);
            helper.register(prepare("wooden_gearbox"), WOODEN_GEARBOX);
            helper.register(prepare("wooden_crank"), WOODEN_CRANK);
            helper.register(prepare("iron_plate"), IRON_PLATE);
            helper.register(prepare("steel_plate"), STEEL_PLATE);
            helper.register(prepare("obsidian_infused_steel_plate"), OBSIDIAN_INFUSED_STEEL_PLATE);
            helper.register(prepare("iron_rod"), IRON_ROD);
            helper.register(prepare("steel_rod"), STEEL_ROD);
            helper.register(prepare("obsidian_infused_steel_rod"), OBSIDIAN_INFUSED_STEEL_ROD);
            helper.register(prepare("iron_nail"), IRON_NAIL);
            helper.register(prepare("steel_nail"), STEEL_NAIL);
            helper.register(prepare("obsidian_infused_steel_nail"), OBSIDIAN_INFUSED_STEEL_NAIL);
            helper.register(prepare("iron_screw"), IRON_SCREW);
            helper.register(prepare("steel_screw"), STEEL_SCREW);
            helper.register(prepare("obsidian_infused_steel_screw"), OBSIDIAN_INFUSED_STEEL_SCREW);
            helper.register(prepare("wooden_gear"), WOODEN_GEAR);
            helper.register(prepare("iron_gear"), IRON_GEAR);
            helper.register(prepare("steel_gear"), STEEL_GEAR);
            helper.register(prepare("obsidian_infused_steel_gear"), OBSIDIAN_INFUSED_STEEL_GEAR);
            helper.register(prepare("steel_ingot"), STEEL_INGOT);
            helper.register(prepare("obsidian_infused_steel_ingot"), OBSIDIAN_INFUSED_STEEL_INGOT);
            helper.register(prepare("iron_pump"), IRON_PUMP);
            helper.register(prepare("iron_small_gear"), IRON_SMALL_GEAR);
            helper.register(prepare("valve_assembly"), VALVE_ASSEMBLY);
            helper.register(prepare("iron_turbine"), IRON_TURBINE);
            helper.register(prepare("pressure_regulator"), PRESSURE_REGULATOR);

            //Tools
            helper.register(prepare("steel_pickaxe"), STEEL_PICKAXE);
            helper.register(prepare("steel_sword"), STEEL_SWORD);
            helper.register(prepare("steel_axe"), STEEL_AXE);
            helper.register(prepare("steel_shovel"), STEEL_SHOVEL);
            helper.register(prepare("steel_hoe"), STEEL_HOE);
            helper.register(prepare("obsidian_infused_steel_pickaxe"), OBSIDIAN_INFUSED_STEEL_PICKAXE);
            helper.register(prepare("obsidian_infused_steel_sword"), OBSIDIAN_INFUSED_STEEL_SWORD);
            helper.register(prepare("obsidian_infused_steel_axe"), OBSIDIAN_INFUSED_STEEL_AXE);
            helper.register(prepare("obsidian_infused_steel_shovel"), OBSIDIAN_INFUSED_STEEL_SHOVEL);
            helper.register(prepare("obsidian_infused_steel_hoe"), OBSIDIAN_INFUSED_STEEL_HOE);

            //Armors
            helper.register(prepare("steel_helmet"), STEEL_HELMET);
            helper.register(prepare("steel_chestplate"), STEEL_CHESTPLATE);
            helper.register(prepare("steel_leggings"), STEEL_LEGGINGS);
            helper.register(prepare("steel_boots"), STEEL_BOOTS);
            helper.register(prepare("obsidian_infused_steel_helmet"), OBSIDIAN_INFUSED_STEEL_HELMET);
            helper.register(prepare("obsidian_infused_steel_chestplate"), OBSIDIAN_INFUSED_STEEL_CHESTPLATE);
            helper.register(prepare("obsidian_infused_steel_leggings"), OBSIDIAN_INFUSED_STEEL_LEGGINGS);
            helper.register(prepare("obsidian_infused_steel_boots"), OBSIDIAN_INFUSED_STEEL_BOOTS);
        });

        // Simple itemProperties for BlockItem
        Item.Properties simpleItemProperties = new Item.Properties()
                .tab(Deepworld.itemGroup)
                .stacksTo(64);

        BlockItem WOODEN_FRAME = new BlockItem(DeepworldBlocks.WOODEN_FRAME, simpleItemProperties);
        BlockItem WOODEN_PRESS = new BlockItem(DeepworldBlocks.WOODEN_PRESS, simpleItemProperties);
        BlockItem WOODEN_GEAR_SHAPER = new BlockItem(DeepworldBlocks.WOODEN_GEAR_SHAPER, simpleItemProperties);
        BlockItem WOODEN_LATHE = new BlockItem(DeepworldBlocks.WOODEN_LATHE, simpleItemProperties);
        BlockItem STEEL_FRAME = new BlockItem(DeepworldBlocks.STEEL_FRAME, simpleItemProperties);
        BlockItem OBSIDIAN_INFUSED_STEEL_FRAME = new BlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME, simpleItemProperties);
        BlockItem STEEL_BLOCK = new BlockItem(DeepworldBlocks.STEEL_BLOCK, simpleItemProperties);
        BlockItem OBSIDIAN_INFUSED_STEEL_BLOCK = new BlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK, simpleItemProperties);
        BlockItem PIPE = new BlockItem(DeepworldBlocks.PIPE, simpleItemProperties);

        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            helper.register(prepare("wooden_frame"), WOODEN_FRAME);
            helper.register(prepare("wooden_press"), WOODEN_PRESS);
            helper.register(prepare("wooden_gear_shaper"), WOODEN_GEAR_SHAPER);
            helper.register(prepare("wooden_lathe"), WOODEN_LATHE);
            helper.register(prepare("steel_frame"), STEEL_FRAME);
            helper.register(prepare("obsidian_infused_steel_frame"), OBSIDIAN_INFUSED_STEEL_FRAME);
            helper.register(prepare("steel_block"), STEEL_BLOCK);
            helper.register(prepare("obsidian_infused_steel_block"), OBSIDIAN_INFUSED_STEEL_BLOCK);
            helper.register(prepare("pipe"), PIPE);
        });
    }

    // Prepare items
    public static ResourceLocation prepare(String name) {
        return new ResourceLocation(Deepworld.MOD_ID, name);
    }
}
