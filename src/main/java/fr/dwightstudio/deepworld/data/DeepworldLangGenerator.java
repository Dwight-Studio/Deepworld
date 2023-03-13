package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItemGroup;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class DeepworldLangGenerator {

    public static class DeepworldLangGeneratorEN extends FabricLanguageProvider {

        public DeepworldLangGeneratorEN(FabricDataOutput dataGenerator){
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

    public static class DeepworldLangGeneratorFR extends FabricLanguageProvider {

        public DeepworldLangGeneratorFR(FabricDataOutput dataOutput) {
            super(dataOutput, "fr_fr");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(DeepworldItemGroup.DEEPWORLD, "The Deep World");
            translationBuilder.add(DeepworldBlocks.STEEL_BLOCK, "Bloc d'acie");
            translationBuilder.add(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK, "Bloc d'acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.IRON_GEAR, "Engrenage en fer");
            translationBuilder.add(DeepworldItems.IRON_NAIL, "Clou en fer");
            translationBuilder.add(DeepworldItems.IRON_PLATE, "Plaque en fer");
            translationBuilder.add(DeepworldItems.IRON_PUMP, "Pompe en fer");
            translationBuilder.add(DeepworldItems.IRON_ROD, "Tige en fer");
            translationBuilder.add(DeepworldItems.IRON_SCREW, "Vis en fer");
            translationBuilder.add(DeepworldItems.IRON_SMALL_GEAR, "Petit engrenage en fer");
            translationBuilder.add(DeepworldItems.IRON_TURBINE, "Turbine en fer");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_GEAR, "Engrenage en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, "Lingot d'acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_NAIL, "Clou en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PLATE, "Plaque en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_ROD, "Tige en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SCREW, "Vis en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.PRESSURE_REGULATOR, "Régulateur de pression");
            translationBuilder.add(DeepworldItems.STEEL_GEAR, "Engrenage en acier");
            translationBuilder.add(DeepworldItems.STEEL_INGOT, "Lingot d'acier");
            translationBuilder.add(DeepworldItems.STEEL_NAIL, "Clou en acier");
            translationBuilder.add(DeepworldItems.STEEL_PLATE, "Plaque en acier");
            translationBuilder.add(DeepworldItems.STEEL_ROD, "Tige en acier");
            translationBuilder.add(DeepworldItems.STEEL_SCREW, "Vis en acier");
            translationBuilder.add(DeepworldItems.VALVE_ASSEMBLY, "Assemblage de vanne");
            translationBuilder.add(DeepworldItems.WOODEN_GEAR, "Engrenage en bois");
            translationBuilder.add(DeepworldItems.STEEL_PICKAXE, "Pioche en acier");
            translationBuilder.add(DeepworldItems.STEEL_SWORD, "Épée en acier");
            translationBuilder.add(DeepworldItems.STEEL_AXE, "Hache en acier");
            translationBuilder.add(DeepworldItems.STEEL_SHOVEL, "Pelle en acier");
            translationBuilder.add(DeepworldItems.STEEL_HOE, "Houe en acier");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_PICKAXE, "Pioche en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SWORD, "Épée en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_AXE, "Hache en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_SHOVEL, "Pelle en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HOE, "Houe en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.STEEL_HELMET, "Casque en acier");
            translationBuilder.add(DeepworldItems.STEEL_CHESTPLATE, "Plastron en acier");
            translationBuilder.add(DeepworldItems.STEEL_LEGGINGS, "Jambières en acier");
            translationBuilder.add(DeepworldItems.STEEL_BOOTS, "Bottes en acier");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_HELMET, "Casque en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_CHESTPLATE, "Plastron en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_LEGGINGS, "Jambières en acier imprégné d'obsidienne");
            translationBuilder.add(DeepworldItems.OBSIDIAN_INFUSED_STEEL_BOOTS, "Bottes en acier imprégné d'obsidienne");
        }
    }

}
