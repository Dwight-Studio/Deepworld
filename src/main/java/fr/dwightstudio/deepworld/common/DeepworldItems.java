package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.armor.ArmorMaterialsTier;
import fr.dwightstudio.deepworld.common.item.*;
import fr.dwightstudio.deepworld.common.tools.ToolsMaterialsTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldItems {

    // Items
    public static RegistryObject<Item> WOODEN_GEAR;
    public static RegistryObject<Item> IRON_GEAR;
    public static RegistryObject<Item> STEEL_GEAR;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_GEAR;

    public static RegistryObject<Item> IRON_NAIL;
    public static RegistryObject<Item> STEEl_NAIL;
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

    public static RegistryObject<Item> IRON_SMALL_GEAR;

    public static RegistryObject<Item> STEEL_INGOT;
    public static RegistryObject<Item> OBSIDIAN_INFUSED_STEEL_INGOT;

    public static RegistryObject<Item> IRON_PUMP;
    public static RegistryObject<Item> IRON_TURBINE;
    public static RegistryObject<Item> PRESSURE_REGULATOR;
    public static RegistryObject<Item> VALVE_ASSEMBLY;

    // BlockItems
    public static RegistryObject<BlockItem> STEEL_BLOCK;
    public static RegistryObject<BlockItem> OBSIDIAN_INFUSED_STEEL_BLOCK;

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

    public DeepworldItems() {
        // Items
        WOODEN_GEAR                       = Deepworld.ITEMS.register("wooden_gear"                      , SimpleDeepworldItem::new);
        IRON_GEAR                         = Deepworld.ITEMS.register("iron_gear"                        , SimpleDeepworldItem::new);
        STEEL_GEAR                        = Deepworld.ITEMS.register("steel_gear"                       , SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_GEAR       = Deepworld.ITEMS.register("obsidian_infused_steel_gear"      , SimpleDeepworldItem::new);

        IRON_NAIL                         = Deepworld.ITEMS.register("iron_nail"                        , SimpleDeepworldItem::new);
        STEEl_NAIL                        = Deepworld.ITEMS.register("steel_nail"                       , SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_NAIL       = Deepworld.ITEMS.register("obsidian_infused_steel_nail"      , SimpleDeepworldItem::new);

        IRON_PLATE                        = Deepworld.ITEMS.register("iron_plate"                       , SimpleDeepworldItem::new);
        STEEL_PLATE                       = Deepworld.ITEMS.register("steel_plate"                      , SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_PLATE      = Deepworld.ITEMS.register("obsidian_infused_steel_plate"     , SimpleDeepworldItem::new);

        IRON_ROD                          = Deepworld.ITEMS.register("iron_rod"                         , SimpleDeepworldItem::new);
        STEEL_ROD                         = Deepworld.ITEMS.register("steel_rod"                        , SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_ROD        = Deepworld.ITEMS.register("obsidian_infused_steel_rod"       , SimpleDeepworldItem::new);

        IRON_SCREW                        = Deepworld.ITEMS.register("iron_screw"                       , SimpleDeepworldItem::new);
        STEEL_SCREW                       = Deepworld.ITEMS.register("steel_screw"                      , SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_SCREW      = Deepworld.ITEMS.register("obsidian_infused_steel_screw"     , SimpleDeepworldItem::new);

        IRON_SMALL_GEAR                   = Deepworld.ITEMS.register("iron_small_gear"                  , SimpleDeepworldItem::new);

        STEEL_INGOT                       = Deepworld.ITEMS.register("steel_ingot"                      , SimpleDeepworldItem::new);
        OBSIDIAN_INFUSED_STEEL_INGOT      = Deepworld.ITEMS.register("obsidian_infused_steel_ingot"     , SimpleDeepworldItem::new);

        IRON_PUMP                         = Deepworld.ITEMS.register("iron_pump"                        , SimpleDeepworldItem::new);
        IRON_TURBINE                      = Deepworld.ITEMS.register("iron_turbine"                     , SimpleDeepworldItem::new);
        PRESSURE_REGULATOR                = Deepworld.ITEMS.register("pressure_regulator"               , SimpleDeepworldItem::new);
        VALVE_ASSEMBLY                    = Deepworld.ITEMS.register("valve_assembly"                   , SimpleDeepworldItem::new);

        // BlockItem
        STEEL_BLOCK                  = Deepworld.ITEMS.register("steel_block"                 , () -> new SimpleDeepworldBlockItem(DeepworldBlocks.STEEL_BLOCK.get()));
        OBSIDIAN_INFUSED_STEEL_BLOCK = Deepworld.ITEMS.register("obsidian_infused_steel_block", () -> new SimpleDeepworldBlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK.get()));

        // Tools
        STEEL_PICKAXE                  = Deepworld.ITEMS.register("steel_pickaxe"                 , () -> new PickaxeItem(ToolsMaterialsTier.STEEL                 , 3   , -2.8f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_SWORD                    = Deepworld.ITEMS.register("steel_sword"                   , () -> new SwordItem(ToolsMaterialsTier.STEEL                   , 5   , -2.4f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_AXE                      = Deepworld.ITEMS.register("steel_axe"                     , () -> new AxeItem(ToolsMaterialsTier.STEEL                     , 8   , -3.2f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_SHOVEL                   = Deepworld.ITEMS.register("steel_shovel"                  , () -> new ShovelItem(ToolsMaterialsTier.STEEL                  , 3.5f, -2.8f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_HOE                      = Deepworld.ITEMS.register("steel_hoe"                     , () -> new HoeItem(ToolsMaterialsTier.STEEL                     , 3   , -2.8f, new Item.Properties().tab(Deepworld.MOD_TAB)));

        OBSIDIAN_INFUSED_STEEL_PICKAXE = Deepworld.ITEMS.register("obsidian_infused_steel_pickaxe", () -> new PickaxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4   , -2.8f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_SWORD   = Deepworld.ITEMS.register("obsidian_infused_steel_sword"  , () -> new SwordItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL  , 9   , -2.2f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_AXE     = Deepworld.ITEMS.register("obsidian_infused_steel_axe"    , () -> new AxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL    , 12  , -3.4f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_SHOVEL  = Deepworld.ITEMS.register("obsidian_infused_steel_shovel" , () -> new ShovelItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL , 4.5f, -2.8f, new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_HOE     = Deepworld.ITEMS.register("obsidian_infused_steel_hoe"    , () -> new HoeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL    , 4   , -2.8f, new Item.Properties().tab(Deepworld.MOD_TAB)));

        // Armors
        STEEL_HELMET                      = Deepworld.ITEMS.register("steel_helmet"                     , () -> new ArmorItem(ArmorMaterialsTier.STEEL                 , EquipmentSlot.HEAD , new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_CHESTPLATE                  = Deepworld.ITEMS.register("steel_chestplate"                 , () -> new ArmorItem(ArmorMaterialsTier.STEEL                 , EquipmentSlot.CHEST, new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_LEGGINGS                    = Deepworld.ITEMS.register("steel_leggings"                   , () -> new ArmorItem(ArmorMaterialsTier.STEEL                 , EquipmentSlot.LEGS , new Item.Properties().tab(Deepworld.MOD_TAB)));
        STEEL_BOOTS                       = Deepworld.ITEMS.register("steel_boots"                      , () -> new ArmorItem(ArmorMaterialsTier.STEEL                 , EquipmentSlot.FEET , new Item.Properties().tab(Deepworld.MOD_TAB)));

        OBSIDIAN_INFUSED_STEEL_HELMET     = Deepworld.ITEMS.register("obsidian_infused_steel_helmet"    , () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.HEAD , new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_CHESTPLATE = Deepworld.ITEMS.register("obsidian_infused_steel_chestplate", () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.CHEST, new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_LEGGINGS   = Deepworld.ITEMS.register("obsidian_infused_steel_leggings"  , () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.LEGS , new Item.Properties().tab(Deepworld.MOD_TAB)));
        OBSIDIAN_INFUSED_STEEL_BOOTS      = Deepworld.ITEMS.register("obsidian_infused_steel_boots"     , () -> new ArmorItem(ArmorMaterialsTier.OBSIDIAN_INFUSED_STEEL, EquipmentSlot.FEET , new Item.Properties().tab(Deepworld.MOD_TAB)));

        // Machine components
        SIMPLE_PRESSING_CHAMBER = Deepworld.ITEMS.register("simple_pressing_chamber", SimplePressingChamberItem::new);
        WOODEN_GEARBOX = Deepworld.ITEMS.register("wooden_gearbox", WoodenGearboxItem::new);
        WOODEN_CRANK = Deepworld.ITEMS.register("wooden_crank", WoodenCrankItem::new);
        SIMPLE_CUTTER = Deepworld.ITEMS.register("simple_cutter", SimpleCutter::new);
        SIMPLE_LEFT_PART_HOLDER = Deepworld.ITEMS.register("simple_left_part_holder", SimpleLeftPartHolder::new);
        SIMPLE_RIGHT_PART_HOLDER = Deepworld.ITEMS.register("simple_right_part_holder", SimpleRightPartHolder::new);
    }
}
