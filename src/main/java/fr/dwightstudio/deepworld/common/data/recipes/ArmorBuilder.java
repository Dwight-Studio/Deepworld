package fr.dwightstudio.deepworld.common.data.recipes;

import fr.dwightstudio.deepworld.common.data.DeepworldRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ArmorBuilder {

    Consumer<FinishedRecipe> exporter;
    ItemLike material;

    public ArmorBuilder(@NotNull Consumer<FinishedRecipe> exporter, ItemLike material) {
        this.exporter = exporter;
        this.material = material;
    }

    public ArmorBuilder helmet(ItemLike helmet) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("###")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(helmet.asItem().toString()));
        return this;
    }

    public ArmorBuilder chestplate(ItemLike chestplate) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', material)
                .unlockedBy("creteria", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(chestplate.asItem().toString()));
        return this;
    }

    public ArmorBuilder leggings(ItemLike leggings) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(leggings.asItem().toString()));
        return this;
    }

    public ArmorBuilder boots(ItemLike boots) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("# #")
                .pattern("# #")
                .define('#', material)
                .unlockedBy("creteria", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(boots.asItem().toString()));
        return this;
    }
}
