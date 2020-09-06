package fr.dwightstudio.deepworld.common;

import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class DeepworldItems {

    // Items
    public static final Item WOODEN_CASE_PANEL;
    public static final Item SIMPLE_PRESSING_CHAMBER;
    public static final Item SIMPLE_CUTTER;
    public static final Item WOODEN_GEARBOX;
    public static final Item WOODEN_CRANK;
    public static final Item IRON_PLATE;

    // Itemblocks
    public static final Item WOODEN_FRAME;
    public static final Item WOODEN_PRESS;
    public static final Item WOODEN_GEAR_SHAPER;
    public static final Item IRON_FRAME;
    public static final Item STEEL_FRAME;
    public static final Item OBSIDIAN_INFUSED_STEEL_FRAME;
    public static final Item WOODEN_GEAR;

    // Constants init
    static {
        // Items
        WOODEN_CASE_PANEL = getRegisteredItem("wooden_case_panel");
        SIMPLE_PRESSING_CHAMBER = getRegisteredItem("simple_pressing_chamber");
        SIMPLE_CUTTER = getRegisteredItem("simple_cutter");
        WOODEN_GEARBOX = getRegisteredItem("wooden_gearbox");
        WOODEN_CRANK = getRegisteredItem("wooden_crank");
        WOODEN_GEAR = getRegisteredItem("wooden_gear");
        IRON_PLATE = getRegisteredItem("iron_plate");

        // Itemblocks
        WOODEN_FRAME = getRegisteredItem("wooden_frame");
        WOODEN_PRESS = getRegisteredItem("wooden_press");
        WOODEN_GEAR_SHAPER = getRegisteredItem("wooden_gear_shaper");
        IRON_FRAME = getRegisteredItem("iron_frame");
        STEEL_FRAME = getRegisteredItem("steel_frame");
        OBSIDIAN_INFUSED_STEEL_FRAME = getRegisteredItem("obsidian_infused_steel_frame");
    }

    private static Item getRegisteredItem(String name)
    {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(Deepworld.MOD_ID, name));

        if (item == null)
        {
            throw new IllegalStateException("Invalid Item requested: " + name);
        }
        else
        {
            return item;
        }
    }

}
