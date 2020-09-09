package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.item.*;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldItemRegister {

    // Items registration
    public static void registerItems(IForgeRegistry<Item> registry) {

        Item WOODEN_CASE_PANEL = new ItemWoodenCasePanel();
        Item SIMPLE_PRESSING_CHAMBER = new ItemSimplePressingChamber();
        Item SIMPLE_CUTTER = new ItemSimpleCutter();
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

        registry.register(prepare(WOODEN_CASE_PANEL, "wooden_case_panel"));
        registry.register(prepare(SIMPLE_PRESSING_CHAMBER, "simple_pressing_chamber"));
        registry.register(prepare(SIMPLE_CUTTER, "simple_cutter"));
        registry.register(prepare(WOODEN_GEARBOX, "wooden_gearbox"));
        registry.register(prepare(WOODEN_CRANK, "wooden_crank"));
        registry.register(prepare(WOODEN_GEAR, "wooden_gear"));
        registry.register(prepare(IRON_PLATE, "iron_plate"));
        registry.register(prepare(STEEL_PLATE, "steel_plate"));
        registry.register(prepare(OBSIDIAN_INFUSED_STEEL_PLATE, "obsidian_infused_steel_plate"));
        registry.register(prepare(IRON_ROD, "iron_rod"));
        registry.register(prepare(STEEL_ROD, "steel_rod"));
        registry.register(prepare(OBSIDIAN_INFUSED_STEEL_ROD, "obsidian_infused_steel_rod"));
        registry.register(prepare(IRON_NAIL, "iron_nail"));
        registry.register(prepare(STEEL_NAIL, "steel_nail"));
        registry.register(prepare(OBSIDIAN_INFUSED_STEEL_NAIL, "obsidian_infused_steel_nail"));
        registry.register(prepare(IRON_SCREW, "iron_screw"));
        registry.register(prepare(STEEL_SCREW, "steel_screw"));
        registry.register(prepare(OBSIDIAN_INFUSED_STEEL_SCREW, "obsidian_infused_steel_screw"));
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {

        ItemBlock WOODEN_FRAME = new ItemBlock(DeepworldBlocks.WOODEN_FRAME);
        ItemBlock WOODEN_PRESS = new ItemBlock(DeepworldBlocks.WOODEN_PRESS);
        ItemBlock WOODEN_GEAR_SHAPER = new ItemBlock(DeepworldBlocks.WOODEN_GEAR_SHAPER);
        ItemBlock IRON_FRAME = new ItemBlock(DeepworldBlocks.IRON_FRAME);
        ItemBlock STEEL_FRAME = new ItemBlock(DeepworldBlocks.STEEL_FRAME);
        ItemBlock OBSIDIAN_INFUSED_STEEL_FRAME = new ItemBlock(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME);

        registry.register(prepareItemBlock(WOODEN_FRAME, "wooden_frame"));
        registry.register(prepareItemBlock(WOODEN_PRESS, "wooden_press"));
        registry.register(prepareItemBlock(WOODEN_GEAR_SHAPER, "wooden_gear_shaper"));
        registry.register(prepareItemBlock(IRON_FRAME, "iron_frame"));
        registry.register(prepareItemBlock(STEEL_FRAME, "steel_frame"));
        registry.register(prepareItemBlock(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
    }

    // Prepare items
    public static Item prepare(Item item, String name) {
        return item.setTranslationKey(Deepworld.MOD_ID + "_" + name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }

    public static ItemBlock prepareItemBlock(ItemBlock item, String name) {
        return (ItemBlock) item.setTranslationKey(Deepworld.MOD_ID + "_" + name).setRegistryName(item.getBlock().getRegistryName());
    }
}
