package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class DeepworldRecipeGenerator extends FabricRecipeProvider {

    public DeepworldRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        createBlockAndIngots(DeepworldBlocks.STEEL_BLOCK, DeepworldItems.STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_STEEL_INGOTS, exporter);
        createBlockAndIngots(DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK, DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_OBSIDIAN_INFUSED_STEEL_INGOTS, exporter);

        createArmor(DeepworldItems.STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_STEEL_INGOTS, exporter);
        createArmor(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_OBSIDIAN_INFUSED_STEEL_INGOTS, exporter);

        createTools(DeepworldItems.STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_STEEL_INGOTS, exporter);
        createTools(DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_OBSIDIAN_INFUSED_STEEL_INGOTS, exporter);
    }

    public void createBlockAndIngots(Block block, Item item, TagKey<Item> itemTagKey, Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', itemTagKey)
                .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(block), "blocks/" + block.getTranslationKey().split("\\.")[2]));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, item, 9)
                .input(block)
                .criterion(FabricRecipeProvider.hasItem(block), FabricRecipeProvider.conditionsFromItem(block))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(item), "ingots/" + item.getTranslationKey().split("\\.")[2] + "_from_block"));
    }

    public void createArmor(Item item, TagKey<Item> itemTagKey, Consumer<RecipeJsonProvider> exporter){
        for(Armor part:Armor.values()){
            Item armorPart = part.getArmorPart(item);
            assert armorPart != null;
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armorPart)
                    .pattern(part.getPattern(0))
                    .pattern(part.getPattern(1))
                    .pattern(part.getPattern(2))
                    .input('#', itemTagKey)
                    .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item))
                    .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(armorPart), "armor/" + armorPart.getTranslationKey().split("\\.")[2]));
        }
    }

    public void createTools(Item item, TagKey<Item> itemTagKey, Consumer<RecipeJsonProvider> exporter){
        for(Tool part:Tool.values()){
            Item toolPart = part.getToolPart(item);
            assert toolPart != null;
            ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, toolPart)
                    .pattern(part.getPattern(0))
                    .pattern(part.getPattern(1))
                    .pattern(part.getPattern(2))
                    .input('#', itemTagKey)
                    //.input('/', Ingredient.fromTag(DeepworldTagGenerator.DeepworldItemTagGenerator.C_WOOD_STICKS))
                    .input('/', DeepworldTagGenerator.DeepworldItemTagGenerator.C_WOODEN_RODS)
                    .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item))
                    .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(toolPart), part.getName() + "/" + toolPart.getTranslationKey().split("\\.")[2]));
        }
    }
}
