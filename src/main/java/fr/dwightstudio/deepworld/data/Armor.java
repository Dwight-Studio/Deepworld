package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.minecraft.item.Item;

public enum Armor {
    helmet(new String[]{"###", "# #", "   "}, new Item[]{DeepworldItems.STEEL_HELMET, DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET}),
    chestplate(new String[]{"# #", "###", "###"}, new Item[]{DeepworldItems.STEEL_CHESTPLATE, DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE}),
    leggings(new String[]{"###", "# #", "# #"}, new Item[]{DeepworldItems.STEEL_LEGGINGS, DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS}),
    boots(new String[]{"   ", "# #", "# #"}, new Item[]{DeepworldItems.STEEL_BOOTS, DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS});

    private final String[] pattern;
    private final Item[] armorParts;

    Armor(String[] pattern, Item[] armorParts) {
        this.pattern = pattern;
        this.armorParts = armorParts;
    }

    public String getPattern(int lineNumber){
        return this.pattern[lineNumber];
    }

    public Item getArmorPart(Item item){
        switch (item.getTranslationKey().split("\\.")[2]) {
            case "steel_ingot" -> {
                return this.armorParts[0];
            }
            case "obsidian_infused_steel_ingot" -> {
                return this.armorParts[1];
            }
            default -> {
                return null;
            }
        }
    }
}
