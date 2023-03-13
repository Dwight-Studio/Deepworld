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
        createBlock(RecipeCategory.MISC, DeepworldBlocks.STEEL_BLOCK, DeepworldItems.STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_STEEL_INGOTS, "blocks/steel_block", "ingots/steel_ingot_from_block", exporter);
    }

    public void createBlock(RecipeCategory recipeCategory, Block block, Item item, TagKey<Item> itemTagKey, String pathToBlock, String pathToItem, Consumer<RecipeJsonProvider> exporter){
        ShapedRecipeJsonBuilder.create(recipeCategory, block)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', itemTagKey)
                .criterion(FabricRecipeProvider.hasItem(item), FabricRecipeProvider.conditionsFromItem(item))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(block), pathToBlock));
        ShapelessRecipeJsonBuilder.create(recipeCategory, item, 9)
                .input(block)
                .criterion(FabricRecipeProvider.hasItem(block), FabricRecipeProvider.conditionsFromItem(block))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(item), pathToItem));
    }
}
