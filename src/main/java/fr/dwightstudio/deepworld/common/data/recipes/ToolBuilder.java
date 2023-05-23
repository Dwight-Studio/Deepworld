package fr.dwightstudio.deepworld.common.data.recipes;

import fr.dwightstudio.deepworld.common.data.DeepworldRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ToolBuilder {
    Consumer<FinishedRecipe> exporter;
    TagKey<Item> material;

    public ToolBuilder(@NotNull Consumer<FinishedRecipe> exporter, TagKey<Item> material) {
        this.exporter = exporter;
        this.material = material;
    }

    public ToolBuilder pickaxe(ItemLike pickaxe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(pickaxe.asItem().toString()));
        return this;
    }

    public ToolBuilder axe(ItemLike axe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("##")
                .pattern("#|")
                .pattern(" |")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(axe.asItem().toString()));
        return this;
    }

    public ToolBuilder shovel(ItemLike shovel) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("#")
                .pattern("|")
                .pattern("|")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(shovel.asItem().toString()));
        return this;
    }

    public ToolBuilder hoe(ItemLike hoe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("##")
                .pattern(" |")
                .pattern(" |")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(hoe.asItem().toString()));
        return this;
    }

    public ToolBuilder sword(ItemLike sword) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("#")
                .pattern("#")
                .pattern("|")
                .define('#', material)
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_material", DeepworldRecipeProvider.has(material))
                .save(exporter, DeepworldRecipeProvider.getResourceLocation(sword.asItem().toString()));
        return this;
    }
}
