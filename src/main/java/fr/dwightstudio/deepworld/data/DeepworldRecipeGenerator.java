package fr.dwightstudio.deepworld.data;

import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.item.DeepworldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class DeepworldRecipeGenerator extends FabricRecipeProvider {

    public DeepworldRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, DeepworldItems.STEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, DeepworldBlocks.STEEL_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, DeepworldItems.OBSIDIAN_INFUSED_STEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, DeepworldBlocks.OBSIDIAN_INFUSED_STEEL_BLOCK);
    }
}
