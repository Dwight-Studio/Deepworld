/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.armors.ArmorMaterialsTier;
import fr.dwightstudio.deepworld.common.items.*;
import fr.dwightstudio.deepworld.common.tools.ToolsMaterialsTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.function.Supplier;

public class DeepworldItems {

    public static final HashSet<Supplier<ItemStack>> modTabContent = new HashSet<>();

    // Items
    public static RegistryObject<Item> WOODEN_GEAR;
    public static RegistryObject<Item> IRON_GEAR;
    public static RegistryObject<Item> STEEL_GEAR;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_GEAR;

    public static RegistryObject<Item> IRON_NAIL;
    public static RegistryObject<Item> STEEL_NAIL;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_NAIL;

    public static RegistryObject<Item> IRON_PLATE;
    public static RegistryObject<Item> STEEL_PLATE;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_PLATE;

    public static RegistryObject<Item> IRON_ROD;
    public static RegistryObject<Item> STEEL_ROD;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_ROD;

    public static RegistryObject<Item> IRON_SCREW;
    public static RegistryObject<Item> STEEL_SCREW;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_SCREW;

    public static RegistryObject<Item> STEEL_INGOT;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_INGOT;

    public static RegistryObject<Item> IRON_SMALL_GEAR;
    public static RegistryObject<Item> IRON_PUMP;
    public static RegistryObject<Item> IRON_TURBINE;
    public static RegistryObject<Item> PRESSURE_REGULATOR;
    public static RegistryObject<Item> VALVE_ASSEMBLY;

    // BlockItems
    public static RegistryObject<BlockItem> STEEL_BLOCK;
    public static RegistryObject<BlockItem> OBSIDIAN_INFUSED_STEEL_BLOCK;

    public static RegistryObject<BlockItem> WOODEN_FRAME;

    public static RegistryObject<BlockItem> WOODEN_LATHE;
    public static RegistryObject<BlockItem> WOODEN_PRESS;
    public static RegistryObject<BlockItem> WOODEN_GEAR_SHAPER;

    public static RegistryObject<BlockItem> IRON_TANK;
    public static RegistryObject<BlockItem> PIPE;

    // Tools
    public static RegistryObject<PickaxeItem> STEEL_PICKAXE;
    public static RegistryObject<SwordItem> STEEL_SWORD;
    public static RegistryObject<AxeItem> STEEL_AXE;
    public static RegistryObject<ShovelItem> STEEL_SHOVEL;
    public static RegistryObject<HoeItem> STEEL_HOE;

    public static RegistryObject<PickaxeItem> OBSIDIAN_INFUSED_STEEL_PICKAXE;
    public static RegistryObject<SwordItem> OBSIDIAN_INFUSED_STEEL_SWORD;
    public static RegistryObject<AxeItem> OBSIDIAN_INFUSED_STEEL_AXE;
    public static RegistryObject<ShovelItem> OBSIDIAN_INFUSED_STEEL_SHOVEL;
    public static RegistryObject<HoeItem> OBSIDIAN_INFUSED_STEEL_HOE;

    // Armors
    public static RegistryObject<ArmorItem> STEEL_HELMET;
    public static RegistryObject<ArmorItem> STEEL_CHESTPLATE;
    public static RegistryObject<ArmorItem> STEEL_LEGGINGS;
    public static RegistryObject<ArmorItem> STEEL_BOOTS;

    public static RegistryObject<ArmorItem> OBSIDIAN_INFUSED_STEEL_HELMET;
    public static RegistryObject<ArmorItem> OBSIDIAN_INFUSED_STEEL_CHESTPLATE;
    public static RegistryObject<ArmorItem> OBSIDIAN_INFUSED_STEEL_LEGGINGS;
    public static RegistryObject<ArmorItem> OBSIDIAN_INFUSED_STEEL_BOOTS;

    // Machine Components
    public static RegistryObject<Item> SIMPLE_PRESSING_CHAMBER;
    public static RegistryObject<Item> WOODEN_GEARBOX;
    public static RegistryObject<Item> WOODEN_CRANK;
    public static RegistryObject<Item> SIMPLE_CUTTER;
    public static RegistryObject<Item> SIMPLE_LEFT_PART_HOLDER;
    public static RegistryObject<Item> SIMPLE_RIGHT_PART_HOLDER;
    public static RegistryObject<Item> WOODEN_CASE_PANEL;

    private DeepworldItems() {
    }

    public static void register() {
        // Items
        WOODEN_GEAR = registerItem("wooden_gear", SimpleDeepworldItem::new);
        IRON_GEAR = registerItem("iron_gear", SimpleDeepworldItem::new);
        STEEL_GEAR = registerItem("steel_gear", SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_GEAR = registerItem("obsidian_infused_steel_gear", SimpleDeepworldItem::new);

        IRON_NAIL = registerItem("iron_nail", SimpleDeepworldItem::new);
        STEEL_NAIL = registerItem("steel_nail", SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_NAIL = registerItem("obsidian_infused_steel_nail", SimpleDeepworldItem::new);

        IRON_PLATE = registerItem("iron_plate", SimpleDeepworldItem::new);
        STEEL_PLATE = registerItem("steel_plate", SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_PLATE = registerItem("obsidian_infused_steel_plate", SimpleDeepworldItem::new);

        IRON_ROD = registerItem("iron_rod", SimpleDeepworldItem::new);
        STEEL_ROD = registerItem("steel_rod", SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_ROD = registerItem("obsidian_infused_steel_rod", SimpleDeepworldItem::new);

        IRON_SCREW = registerItem("iron_screw", SimpleDeepworldItem::new);
        STEEL_SCREW = registerItem("steel_screw", SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_SCREW = registerItem("obsidian_infused_steel_screw", SimpleDeepworldItem::new);

        STEEL_INGOT = registerItem("steel_ingot", SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_INGOT = registerItem("obsidian_infused_steel_ingot", SimpleDeepworldItem::new);

        IRON_SMALL_GEAR = registerItem("iron_small_gear", SimpleDeepworldItem::new);
        IRON_PUMP = registerItem("iron_pump", SimpleDeepworldItem::new);
        IRON_TURBINE = registerItem("iron_turbine", SimpleDeepworldItem::new);
        PRESSURE_REGULATOR = registerItem("pressure_regulator", SimpleDeepworldItem::new);
        VALVE_ASSEMBLY = registerItem("valve_assembly", SimpleDeepworldItem::new);

        // BlockItem
        STEEL_BLOCK = registerItem("steel_block", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.STEEL_BLOCK.get()));
        OBSIDIAN_INFUSED_STEEL_BLOCK = registerItem("obsidian_infused_steel_block", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.get()));

        WOODEN_FRAME = registerItem("wooden_frame", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.WOODEN_FRAME.get()));
        WOODEN_LATHE = registerItem("wooden_lathe", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.WOODEN_LATHE.get()));
        WOODEN_PRESS = registerItem("wooden_press", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.WOODEN_PRESS.get()));
        WOODEN_GEAR_SHAPER = registerItem("wooden_gear_shaper", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.WOODEN_GEAR_SHAPER.get()));

        IRON_TANK = registerItem("iron_tank", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.IRON_TANK.get()));
        PIPE = registerItem("pipe", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.PIPE.get()));

        // Tools
        STEEL_PICKAXE = registerItem("steel_pickaxe", () -> new PickaxeItem(ToolsMaterialsTier.STEEL, 3, -2.8f, new Item.Properties()));
        STEEL_SWORD = registerItem("steel_sword", () -> new SwordItem(ToolsMaterialsTier.STEEL, 5, -2.4f, new Item.Properties()));
        STEEL_AXE = registerItem("steel_axe", () -> new AxeItem(ToolsMaterialsTier.STEEL, 8, -3.2f, new Item.Properties()));
        STEEL_SHOVEL = registerItem("steel_shovel", () -> new ShovelItem(ToolsMaterialsTier.STEEL, 3.5f, -2.8f, new Item.Properties()));
        STEEL_HOE = registerItem("steel_hoe", () -> new HoeItem(ToolsMaterialsTier.STEEL, 3, -2.8f, new Item.Properties()));

        OBSIDIAN_INFUSED_STEEL_PICKAXE = registerItem("obsidian_infused_steel_pickaxe", () -> new PickaxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4, -2.8f, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_SWORD = registerItem("obsidian_infused_steel_sword", () -> new SwordItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 9, -2.2f, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_AXE = registerItem("obsidian_infused_steel_axe", () -> new AxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 12, -3.4f, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_SHOVEL = registerItem("obsidian_infused_steel_shovel", () -> new ShovelItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4.5f, -2.8f, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_HOE = registerItem("obsidian_infused_steel_hoe", () -> new HoeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4, -2.8f, new Item.Properties()));

        // Armors
        STEEL_HELMET = registerItem("steel_helmet", () -> new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.HEAD, new Item.Properties()));
        STEEL_CHESTPLATE = registerItem("steel_chestplate", () -> new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.CHEST, new Item.Properties()));
        STEEL_LEGGINGS = registerItem("steel_leggings", () -> new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.LEGS, new Item.Properties()));
        STEEL_BOOTS = registerItem("steel_boots", () -> new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlot.FEET, new Item.Properties()));

        OBSIDIAN_INFUSED_STEEL_HELMET = registerItem("obsidian_infused_steel_helmet", () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.HEAD, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_CHESTPLATE = registerItem("obsidian_infused_steel_chestplate", () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.CHEST, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_LEGGINGS = registerItem("obsidian_infused_steel_leggings", () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.LEGS, new Item.Properties()));
        OBSIDIAN_INFUSED_STEEL_BOOTS = registerItem("obsidian_infused_steel_boots", () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.FEET, new Item.Properties()));

        // Machine components
        SIMPLE_PRESSING_CHAMBER = registerItem("simple_pressing_chamber", SimplePressingChamberItem::new);
        WOODEN_GEARBOX = registerItem("wooden_gearbox", WoodenGearboxItem::new);
        WOODEN_CRANK = registerItem("wooden_crank", WoodenCrankItem::new);
        SIMPLE_CUTTER = registerItem("simple_cutter", SimpleCutterItem::new);
        SIMPLE_LEFT_PART_HOLDER = registerItem("simple_left_part_holder", SimpleLeftPartHolderItem::new);
        SIMPLE_RIGHT_PART_HOLDER = registerItem("simple_right_part_holder", SimpleRightPartHolderItem::new);
        WOODEN_CASE_PANEL = registerItem("wooden_case_panel", WoodenCasePanelItem::new);
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        RegistryObject<T> item = Deepworld.ITEMS.register(name, itemSupplier);
        modTabContent.add(() -> item.get().getDefaultInstance());
        return item;
    }
}
