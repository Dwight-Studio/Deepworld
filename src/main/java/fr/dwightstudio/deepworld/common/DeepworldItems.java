package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.item.SimpleDeepworldBlockItem;
import fr.dwightstudio.deepworld.common.item.SimpleDeepworldItem;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldItems {

    // Items
    public static RegistryObject<SimpleDeepworldItem> WOODEN_GEAR;
    public static RegistryObject<SimpleDeepworldItem> IRON_GEAR;
    public static RegistryObject<SimpleDeepworldItem> STEEL_GEAR;
    public static RegistryObject<SimpleDeepworldItem> OBSIDIAN_INFUSED_STEEL_GEAR;

    public static RegistryObject<SimpleDeepworldItem> IRON_NAIL;
    public static RegistryObject<SimpleDeepworldItem> STEEl_NAIL;
    public static RegistryObject<SimpleDeepworldItem> OBSIDIAN_INFUSED_STEEL_NAIL;

    public static RegistryObject<SimpleDeepworldItem> IRON_PLATE;
    public static RegistryObject<SimpleDeepworldItem> STEEL_PLATE;
    public static RegistryObject<SimpleDeepworldItem> OBSIDIAN_INFUSED_STEEL_PLATE;

    public static RegistryObject<SimpleDeepworldItem> IRON_ROD;
    public static RegistryObject<SimpleDeepworldItem> STEEL_ROD;
    public static RegistryObject<SimpleDeepworldItem> OBSIDIAN_INFUSED_STEEL_ROD;

    public static RegistryObject<SimpleDeepworldItem> IRON_SCREW;
    public static RegistryObject<SimpleDeepworldItem> STEEL_SCREW;
    public static RegistryObject<SimpleDeepworldItem> OBSIDIAN_INFUSED_STEEL_SCREW;

    public static RegistryObject<SimpleDeepworldItem> IRON_SMALL_GEAR;

    public static RegistryObject<SimpleDeepworldItem> STEEL_INGOT;
    public static RegistryObject<SimpleDeepworldItem> OBSIDIAN_INFUSED_STEEL_INGOT;

    public static RegistryObject<SimpleDeepworldItem> IRON_PUMP;
    public static RegistryObject<SimpleDeepworldItem> IRON_TURBINE;
    public static RegistryObject<SimpleDeepworldItem> PRESSURE_REGULATOR;
    public static RegistryObject<SimpleDeepworldItem> VALVE_ASSEMBLY;

    // BlockItems
    public static RegistryObject<SimpleDeepworldBlockItem> STEEL_BLOCK;
    public static RegistryObject<SimpleDeepworldBlockItem> OBSIDIAN_INFUSED_STEEL_BLOCK;

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
    }
}
