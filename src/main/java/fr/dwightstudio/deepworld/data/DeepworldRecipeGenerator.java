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
        createBlock(DeepworldBlocks.STEEL_BLOCK, DeepworldItems.STEEL_INGOT, DeepworldTagGenerator.DeepworldItemTagGenerator.C_STEEL_INGOTS, exporter);
    }

    public void createBlock(Block block, Item item, TagKey<Item> itemTagKey, Consumer<RecipeJsonProvider> exporter){
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
}
