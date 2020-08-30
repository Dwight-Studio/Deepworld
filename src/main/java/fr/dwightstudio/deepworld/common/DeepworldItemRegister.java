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
        Item WOODEN_GEARBOX = new ItemWoodenGearbox();
        Item WOODEN_CRANK = new ItemWoodenCrank();
        Item WOODEN_GEAR = new ItemWoodenGear();

        registry.register(prepare(WOODEN_CASE_PANEL, "wooden_case_panel"));
        registry.register(prepare(SIMPLE_PRESSING_CHAMBER, "simple_pressing_chamber"));
        registry.register(prepare(WOODEN_GEARBOX, "wooden_gearbox"));
        registry.register(prepare(WOODEN_CRANK, "wooden_crank"));
        registry.register(prepare(WOODEN_GEAR, "wooden_gear"));
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {

        ItemBlock WOODEN_FRAME = new ItemBlock(DeepworldBlocks.WOODEN_FRAME);
        ItemBlock WOODEN_PRESS = new ItemBlock(DeepworldBlocks.WOODEN_PRESS);
        ItemBlock IRON_FRAME = new ItemBlock(DeepworldBlocks.IRON_FRAME);
        ItemBlock STEEL_FRAME = new ItemBlock(DeepworldBlocks.STEEL_FRAME);
        ItemBlock OBSIDIAN_INFUSED_STEEL_FRAME = new ItemBlock(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME);

        registry.register(prepareItemBlock(WOODEN_FRAME, "wooden_frame"));
        registry.register(prepareItemBlock(WOODEN_PRESS, "wooden_press"));
        registry.register(prepareItemBlock(IRON_FRAME, "iron_frame"));
        registry.register(prepareItemBlock(STEEL_FRAME, "steel_frame"));
        registry.register(prepareItemBlock(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
    }

    // Prepare items
    public static Item prepare(Item item, String name) {
        return item.setTranslationKey(name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }

    public static ItemBlock prepareItemBlock(ItemBlock item, String name) {
        return (ItemBlock) item.setTranslationKey(name).setRegistryName(item.getBlock().getRegistryName());
    }
}
