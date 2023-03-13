package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.minecraft.item.Item;

public enum Tool {
    axe(new String[]{"## ", "#/ ", " / "}, new Item[]{DeepworldItems.STEEL_AXE, DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE}, "axe"),
    hoe(new String[]{"## ", " / ", " / "}, new Item[]{DeepworldItems.STEEL_HOE, DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE}, "hoe"),
    pickaxe(new String[]{"###", " / ", " / "}, new Item[]{DeepworldItems.STEEL_PICKAXE, DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE}, "pickaxe"),
    shovel(new String[]{" # ", " / ", " / "}, new Item[]{DeepworldItems.STEEL_SHOVEL, DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL}, "shovel"),
    sword(new String[]{" # ", " # ", " / "}, new Item[]{DeepworldItems.STEEL_SWORD, DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD}, "sword");

    private final String[] pattern;
    private final Item[] toolParts;
    private final String name;

    Tool(String[] pattern, Item[] toolParts, String name) {
        this.pattern = pattern;
        this.toolParts = toolParts;
        this.name = name;
    }

    public String getPattern(int lineNumber){
        return this.pattern[lineNumber];
    }

    public Item getToolPart(Item item){
        switch (item.getTranslationKey().split("\\.")[2]) {
            case "steel_ingot" -> {
                return this.toolParts[0];
            }
            case "obsidian_infused_steel_ingot" -> {
                return this.toolParts[1];
            }
            default -> {
                return null;
            }
        }
    }

    public String getName(){
        return this.name;
    }

}
