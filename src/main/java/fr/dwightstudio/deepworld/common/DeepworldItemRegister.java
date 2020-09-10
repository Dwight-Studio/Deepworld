package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class DeepworldItemRegister {

    // Items registration
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {

        Item WOODEN_CASE_PANEL = new ItemWoodenCasePanel();
        Item SIMPLE_PRESSING_CHAMBER = new ItemSimplePressingChamber();
        Item WOODEN_GEARBOX = new ItemWoodenGearbox();
        Item WOODEN_CRANK = new ItemWoodenCrank();
        Item WOODEN_GEAR = new ItemWoodenGear();
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

        event.getRegistry().register(prepare(WOODEN_CASE_PANEL, "wooden_case_panel"));
        event.getRegistry().register(prepare(SIMPLE_PRESSING_CHAMBER, "simple_pressing_chamber"));
        event.getRegistry().register(prepare(WOODEN_GEARBOX, "wooden_gearbox"));
        event.getRegistry().register(prepare(WOODEN_CRANK, "wooden_crank"));
        event.getRegistry().register(prepare(WOODEN_GEAR, "wooden_gear"));
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


        // Simple itemProperties for BlockItem
        Item.Properties simpleItemProperties = new Item.Properties()
                .group(Deepworld.itemGroup)
                .maxStackSize(64);

        BlockItem WOODEN_FRAME = new BlockItem(DeepworldBlocks.WOODEN_FRAME, simpleItemProperties);
        BlockItem WOODEN_PRESS = new BlockItem(DeepworldBlocks.WOODEN_PRESS, simpleItemProperties);
        BlockItem WOODEN_GEAR_SHAPER = new BlockItem(DeepworldBlocks.WOODEN_GEAR_SHAPER, simpleItemProperties);
        BlockItem IRON_FRAME = new BlockItem(DeepworldBlocks.IRON_FRAME, simpleItemProperties);
        BlockItem STEEL_FRAME = new BlockItem(DeepworldBlocks.STEEL_FRAME, simpleItemProperties);
        BlockItem OBSIDIAN_INFUSED_STEEL_FRAME = new BlockItem(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME, simpleItemProperties);

        event.getRegistry().register(prepareBlockItem(WOODEN_FRAME, "wooden_frame"));
        event.getRegistry().register(prepareBlockItem(WOODEN_PRESS, "wooden_press"));
        event.getRegistry().register(prepareBlockItem(WOODEN_GEAR_SHAPER, "wooden_gear_shaper"));
        event.getRegistry().register(prepareBlockItem(IRON_FRAME, "iron_frame"));
        event.getRegistry().register(prepareBlockItem(STEEL_FRAME, "steel_frame"));
        event.getRegistry().register(prepareBlockItem(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
    }

    // Prepare items
    public static Item prepare(Item item, String name) {
        return item.setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }

    public static BlockItem prepareBlockItem(BlockItem item, String name) {
        return (BlockItem) item.setRegistryName(item.getBlock().getRegistryName());
    }
}
