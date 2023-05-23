package fr.dwightstudio.deepworld.common.data.recipes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class MachineRecipeBuilder implements RecipeBuilder {

    private RecipeSerializer<MachineRecipe> serializer;
    @Nullable
    private String group;
    private final RecipeCategory category;
    private final Item result;
    private int count = 1;
    private Ingredient ingredient;
    private int ingredientsCount = 1;
    private int processingTime = 500;
    private MachineTier machineTier = MachineTier.WOOD;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    private MachineRecipeBuilder(RecipeCategory category, Item result, int count) {
        this.category = category;
        this.result = result;
        this.count = count;
    }

    public static @NotNull MachineRecipeBuilder process(RecipeCategory category, Item result) {
        return new MachineRecipeBuilder(category, result, 1);
    }

    public static @NotNull MachineRecipeBuilder process(RecipeCategory category, Item result, int count) {
        return new MachineRecipeBuilder(category, result, count);
    }

    public @NotNull MachineRecipeBuilder machine(RecipeSerializer<MachineRecipe> type, MachineTier tier) {
        this.serializer = type;
        this.machineTier = tier;
        return this;
    }

    public @NotNull MachineRecipeBuilder ingredient(ItemLike itemLike, int count) {
        this.ingredient = Ingredient.of(itemLike);
        this.ingredientsCount = count;
        return this;
    }

    public @NotNull MachineRecipeBuilder ingredient(ItemLike itemLike) {
        return ingredient(itemLike, 1);
    }

    public @NotNull MachineRecipeBuilder ingredient(TagKey<Item> itemTagKey, int count) {
        this.ingredient = Ingredient.of(itemTagKey);
        this.ingredientsCount = count;
        return this;
    }

    public @NotNull MachineRecipeBuilder ingredient(TagKey<Item> itemTagKey) {
        return ingredient(itemTagKey, 1);
    }

    public @NotNull MachineRecipeBuilder processingTime(int processingTime) {
        this.processingTime = processingTime;
        return this;
    }

    public @NotNull MachineRecipeBuilder unlockedBy(@NotNull String name, @NotNull CriterionTriggerInstance criterionTriggerInstance) {
        this.advancement.addCriterion(name, criterionTriggerInstance);
        return this;
    }

    @Override
    public @NotNull MachineRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return result;
    }

    private void ensureValid(ResourceLocation p_126266_) {
        if (this.serializer == null) {
            throw new IllegalStateException("Recipe " + p_126266_ + " lack serializer");
        }
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_126266_);
        }
    }

    public void save(Consumer<FinishedRecipe> exporter, @NotNull ResourceLocation resourceLocation) {
        resourceLocation = resourceLocation.withPrefix(this.serializer.toString() + "/");
        this.ensureValid(resourceLocation);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceLocation)).rewards(AdvancementRewards.Builder.recipe(resourceLocation)).requirements(RequirementsStrategy.OR);
        exporter.accept(new MachineRecipeBuilder.Result(resourceLocation, this.serializer, this.group == null ? "" : this.group, this.ingredient, this.ingredientsCount, this.processingTime, this.machineTier, this.result, this.count, this.advancement, resourceLocation.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    static class Result implements FinishedRecipe {
        private final ResourceLocation id;

        private final RecipeSerializer<MachineRecipe> serializer;
        private final String group;
        private final Ingredient ingredient;

        private final int ingredientsCount;
        private final int processingTime;
        private final Item result;

        private final int count;

        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        private final MachineTier machineTier;

        public Result(ResourceLocation id, RecipeSerializer<MachineRecipe> serializer, String group, Ingredient ingredient, int ingredientsCount, int processingTime, MachineTier machineTier, Item result, int count, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.serializer = serializer;
            this.group = group;
            this.ingredient = ingredient;
            this.ingredientsCount = ingredientsCount;
            this.processingTime = processingTime;
            this.machineTier = machineTier;
            this.result = result;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public void serializeRecipeData(@NotNull JsonObject jsonObject) {
            if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }

            jsonObject.add("ingredient", this.ingredient.toJson());
            jsonObject.addProperty("ingredientsCount", this.ingredientsCount);
            jsonObject.addProperty("processTime", this.processingTime);
            jsonObject.addProperty("machineTier", this.machineTier.toString().toLowerCase());

            JsonObject itemJson = new JsonObject();
            itemJson.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1) {
                itemJson.addProperty("count", this.count);
            }

            jsonObject.add("result", itemJson);
        }

        public @NotNull RecipeSerializer<MachineRecipe> getType() {
            return this.serializer;
        }

        public @NotNull ResourceLocation getId() {
            return this.id;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
