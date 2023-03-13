package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class DeepworldRecipeGenerator extends FabricRecipeProvider {

    public DeepworldRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, DeepworldBlocks.STEEL_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', DeepworldTagGenerator.DeepworldItemTagGenerator.C_STEEL_INGOTS)
                .criterion(FabricRecipeProvider.hasItem(DeepworldItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(DeepworldItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(DeepworldBlocks.STEEL_BLOCK), "blocks/steel_block"));
    }
}
