package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.armor.ArmorMaterialsTier;
import fr.dwightstudio.deepworld.common.item.*;
import fr.dwightstudio.deepworld.common.tools.ToolsMaterialsTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldItemRegister {

    // Items registration
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {

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

        //Tools
        Item STEEL_PICKAXE = new PickaxeItem(ToolsMaterialsTier.STEEL, 3, -2.8F, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_SWORD = new SwordItem(ToolsMaterialsTier.STEEL, 5, -2.4F, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_AXE = new AxeItem(ToolsMaterialsTier.STEEL, 8, -3.2F, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_SHOVEL = new ShovelItem(ToolsMaterialsTier.STEEL, 3.5F, -2.8F, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_HOE = new HoeItem(ToolsMaterialsTier.STEEL, -2.8F, new Item.Properties().group(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_PICKAXE = new PickaxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4, -2.8F, new Item.Properties().group(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_SWORD = new SwordItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 9, -2.2F, new Item.Properties().group(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_AXE = new AxeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 12, -3.4F, new Item.Properties().group(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_SHOVEL = new ShovelItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, 4.5F, -2.8F, new Item.Properties().group(Deepworld.itemGroup));
        Item OBSIDIAN_INFUSED_STEEL_HOE = new HoeItem(ToolsMaterialsTier.OBSIDIAN_INFUSED_STEEL, -2.8F, new Item.Properties().group(Deepworld.itemGroup));

        //Armors
        Item STEEL_HELMET = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlotType.HEAD, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_CHESTPLATE = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlotType.CHEST, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_LEGGINGS = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlotType.LEGS, new Item.Properties().group(Deepworld.itemGroup));
        Item STEEL_BOOTS = new ArmorItem(ArmorMaterialsTier.STEEL, EquipmentSlotType.FEET, new Item.Properties().group(Deepworld.itemGroup));

        event.getRegistry().register(prepare(WOODEN_CASE_PANEL, "wooden_case_panel"));
        event.getRegistry().register(prepare(SIMPLE_PRESSING_CHAMBER, "simple_pressing_chamber"));
        event.getRegistry().register(prepare(SIMPLE_CUTTER, "simple_cutter"));
        event.getRegistry().register(prepare(SIMPLE_LEFT_PART_HOLDER, "simple_left_part_holder"));
        event.getRegistry().register(prepare(SIMPLE_RIGHT_PART_HOLDER, "simple_right_part_holder"));
        event.getRegistry().register(prepare(WOODEN_GEARBOX, "wooden_gearbox"));
        event.getRegistry().register(prepare(WOODEN_CRANK, "wooden_crank"));
        event.getRegistry().register(prepare(IRON_PLATE, "iron_plate"));
        event.getRegistry().register(prepare(STEEL_PLATE, "steel_plate"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_PLATE, "obsidian_infused_steel_plate"));
        event.getRegistry().register(prepare(IRON_ROD, "iron_rod"));
        event.getRegistry().register(prepare(STEEL_ROD, "steel_rod"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_ROD, "obsidian_infused_steel_rod"));
        event.getRegistry().register(prepare(IRON_NAIL, "iron_nail"));
        event.getRegistry().register(prepare(STEEL_NAIL, "steel_nail"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_NAIL, "obsidian_infused_steel_nail"));
        event.getRegistry().register(prepare(IRON_SCREW, "iron_screw"));
        event.getRegistry().register(prepare(STEEL_SCREW, "steel_screw"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_SCREW, "obsidian_infused_steel_screw"));
        event.getRegistry().register(prepare(WOODEN_GEAR, "wooden_gear"));
        event.getRegistry().register(prepare(IRON_GEAR, "iron_gear"));
        event.getRegistry().register(prepare(STEEL_GEAR, "steel_gear"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_GEAR, "obsidian_infused_steel_gear"));
        event.getRegistry().register(prepare(STEEL_INGOT, "steel_ingot"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_INGOT, "obsidian_infused_steel_ingot"));
        event.getRegistry().register(prepare(IRON_PUMP, "iron_pump"));

        //Tools
        event.getRegistry().register(prepare(STEEL_PICKAXE, "steel_pickaxe"));
        event.getRegistry().register(prepare(STEEL_SWORD, "steel_sword"));
        event.getRegistry().register(prepare(STEEL_AXE, "steel_axe"));
        event.getRegistry().register(prepare(STEEL_SHOVEL, "steel_shovel"));
        event.getRegistry().register(prepare(STEEL_HOE, "steel_hoe"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_PICKAXE, "obsidian_infused_steel_pickaxe"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_SWORD, "obsidian_infused_steel_sword"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_AXE, "obsidian_infused_steel_axe"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_SHOVEL, "obsidian_infused_steel_shovel"));
        event.getRegistry().register(prepare(OBSIDIAN_INFUSED_STEEL_HOE, "obsidian_infused_steel_hoe"));

        //Armors
        event.getRegistry().register(prepare(STEEL_HELMET, "steel_helmet"));
        event.getRegistry().register(prepare(STEEL_CHESTPLATE, "steel_chestplate"));
        event.getRegistry().register(prepare(STEEL_LEGGINGS, "steel_leggings"));
        event.getRegistry().register(prepare(STEEL_BOOTS, "steel_boots"));

        // Simple itemProperties for BlockItem
        Item.Properties simpleItemProperties = new Item.Properties()
                .group(Deepworld.itemGroup)
                .maxStackSize(64);

        BlockItem WOODEN_FRAME = new BlockItem(DeepworldBlocks.WOODEN_FRAME, simpleItemProperties);
        BlockItem WOODEN_PRESS = new BlockItem(DeepworldBlocks.WOODEN_PRESS, simpleItemProperties);
        BlockItem WOODEN_GEAR_SHAPER = new BlockItem(DeepworldBlocks.WOODEN_GEAR_SHAPER, simpleItemProperties);
        BlockItem WOODEN_LATHE = new BlockItem(DeepworldBlocks.WOODEN_LATHE, simpleItemProperties);
        BlockItem IRON_FRAME = new BlockItem(DeepworldBlocks.IRON_FRAME, simpleItemProperties);
        BlockItem STEEL_FRAME = new BlockItem(DeepworldBlocks.STEEL_FRAME, simpleItemProperties);
        BlockItem OBSIDIAN_INFUSED_STEEL_FRAME = new BlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME, simpleItemProperties);
        BlockItem STEEL_BLOCK = new BlockItem(DeepworldBlocks.STEEL_BLOCK, simpleItemProperties);
        BlockItem OBSIDIAN_INFUSED_STEEL_BLOCK = new BlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK, simpleItemProperties);

        event.getRegistry().register(prepareBlockItem(WOODEN_FRAME, "wooden_frame"));
        event.getRegistry().register(prepareBlockItem(WOODEN_PRESS, "wooden_press"));
        event.getRegistry().register(prepareBlockItem(WOODEN_GEAR_SHAPER, "wooden_gear_shaper"));
        event.getRegistry().register(prepareBlockItem(WOODEN_LATHE, "wooden_lathe"));
        event.getRegistry().register(prepareBlockItem(IRON_FRAME, "iron_frame"));
        event.getRegistry().register(prepareBlockItem(STEEL_FRAME, "steel_frame"));
        event.getRegistry().register(prepareBlockItem(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
        event.getRegistry().register(prepareBlockItem(STEEL_BLOCK, "steel_block"));
        event.getRegistry().register(prepareBlockItem(OBSIDIAN_INFUSED_STEEL_BLOCK, "obsidian_infused_steel_block"));
    }

    // Prepare items
    public static Item prepare(Item item, String name) {
        return item.setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }

    public static BlockItem prepareBlockItem(BlockItem item, String name) {
        return (BlockItem) item.setRegistryName(item.getBlock().getRegistryName());
    }
}
