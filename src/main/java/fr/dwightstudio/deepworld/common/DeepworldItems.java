package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.item.ItemSimplePressingChamber;
import fr.dwightstudio.deepworld.common.item.ItemWoodenCasePanel;
import fr.dwightstudio.deepworld.common.item.ItemWoodenCrank;
import fr.dwightstudio.deepworld.common.item.ItemWoodenGearbox;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Deepworld.MOD_ID)
public class DeepworldItems {

    // Declare items
    public static final Item WOODEN_CASE_PANEL = new ItemWoodenCasePanel();
    public static final Item SIMPLE_PRESSING_CHAMBER = new ItemSimplePressingChamber();
    public static final Item WOODEN_GEARBOX = new ItemWoodenGearbox();
    public static final Item WOODEN_CRANK = new ItemWoodenCrank();

    // Declare itemblocks
    public static final Item WOODEN_FRAME = new ItemBlock(DeepworldBlocks.WOODEN_FRAME);
    public static final Item WOODEN_PRESS = new ItemBlock(DeepworldBlocks.WOODEN_PRESS);
    public static final Item IRON_FRAME = new ItemBlock(DeepworldBlocks.IRON_FRAME);
    public static final Item STEEL_FRAME = new ItemBlock(DeepworldBlocks.STEEL_FRAME);
    public static final Item OBSIDIAN_INFUSED_STEEL_FRAME = new ItemBlock(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_FRAME);

    // Register items
    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(prepare(WOODEN_CASE_PANEL, "wooden_case_panel"));
        registry.register(prepare(SIMPLE_PRESSING_CHAMBER, "simple_pressing_chamber"));
        registry.register(prepare(WOODEN_GEARBOX, "wooden_gearbox"));
        registry.register(prepare(WOODEN_CRANK, "wooden_crank"));
    }

    // Register ItemBlock
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.register(DeepworldItems.prepare(WOODEN_FRAME, "wooden_frame"));
        registry.register(DeepworldItems.prepare(WOODEN_PRESS, "wooden_press"));
        registry.register(DeepworldItems.prepare(IRON_FRAME, "iron_frame"));
        registry.register(DeepworldItems.prepare(STEEL_FRAME, "steel_frame"));
        registry.register(DeepworldItems.prepare(OBSIDIAN_INFUSED_STEEL_FRAME, "obsidian_infused_steel_frame"));
    }

    // Prepare items
    public static Item prepare(Item item, String name) {
        return item.setTranslationKey(name).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, name));
    }
}
