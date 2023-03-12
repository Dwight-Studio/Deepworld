package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItemGroup;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class DeepworldLangGenerator extends FabricLanguageProvider {

    public DeepworldLangGenerator(FabricDataOutput dataGenerator){
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(DeepworldItemGroup.DEEPWORLD, "The Deep World");
        translationBuilder.add(DeepworldBlocks.STEEL_BLOCK, "Steel Block");
        translationBuilder.add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK, "Obsidian-Infused Steel Block");
        translationBuilder.add(DeepworldItems.IRON_GEAR, "Iron Gear");
        translationBuilder.add(DeepworldItems.IRON_NAIL, "Iron Nail");
        translationBuilder.add(DeepworldItems.IRON_PLATE, "Iron Plate");
        translationBuilder.add(DeepworldItems.IRON_PUMP, "Iron Pump");
        translationBuilder.add(DeepworldItems.IRON_ROD, "Iron Rod");
        translationBuilder.add(DeepworldItems.IRON_SCREW, "Iron Screw");
        translationBuilder.add(DeepworldItems.IRON_SMALL_GEAR, "Small Iron Gear");
        translationBuilder.add(DeepworldItems.IRON_TURBINE, "Iron Turbine");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_GEAR, "Obsidian-Infused Steel Gear");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, "Obsidian-Infused Steel Ingot");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_NAIL, "Obsidian-Infused Steel Nail");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PLATE, "Obsidian-Infused Steel Plate");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_ROD, "Obsidian-Infused Steel Rod");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SCREW, "Obsidian-Infused Steel Screw");
        translationBuilder.add(DeepworldItems.PRESSURE_REGULATOR, "Pressure Regulator");
        translationBuilder.add(DeepworldItems.STEEL_GEAR, "Steel Gear");
        translationBuilder.add(DeepworldItems.STEEL_INGOT, "Steel Ingot");
        translationBuilder.add(DeepworldItems.STEEL_NAIL, "Steel Nail");
        translationBuilder.add(DeepworldItems.STEEL_PLATE, "Steel Plate");
        translationBuilder.add(DeepworldItems.STEEL_ROD, "Steel Rod");
        translationBuilder.add(DeepworldItems.STEEL_SCREW, "Steel Screw");
        translationBuilder.add(DeepworldItems.VALVE_ASSEMBLY, "Valve Assembly");
        translationBuilder.add(DeepworldItems.WOODEN_GEAR, "Wooden Gear");
        translationBuilder.add(DeepworldItems.STEEL_PICKAXE, "Steel Pickaxe");
        translationBuilder.add(DeepworldItems.STEEL_SWORD, "Steel Sword");
        translationBuilder.add(DeepworldItems.STEEL_AXE, "Steel Axe");
        translationBuilder.add(DeepworldItems.STEEL_SHOVEL, "Steel Shovel");
        translationBuilder.add(DeepworldItems.STEEL_HOE, "Steel Hoe");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE, "Obsidian-Infused Steel Pickaxe");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD, "Obsidian-Infused Steel Sword");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE, "Obsidian-Infused Steel Axe");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL, "Obsidian-Infused Steel Shovel");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE, "Obsidian-Infused Steel Hoe");
        translationBuilder.add(DeepworldItems.STEEL_HELMET, "Steel Helmet");
        translationBuilder.add(DeepworldItems.STEEL_CHESTPLATE, "Steel Chestplate");
        translationBuilder.add(DeepworldItems.STEEL_LEGGINGS, "Steel Leggings");
        translationBuilder.add(DeepworldItems.STEEL_BOOTS, "Steel Boots");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET, "Obsidian-Infused Steel Helmet");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE, "Obsidian-Infused Steel Chestplate");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS, "Obsidian-Infused Steel Leggings");
        translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS, "Obsidian-Infused Steel Boots");
    }
}
