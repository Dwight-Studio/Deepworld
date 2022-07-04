package fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodenGearShaperRecipeSerializer <T extends WoodenGearShaperRecipe> extends net.minecraftforge.registries.RegistryBuilder<RecipeSerializer<?>> implements RecipeSerializer<T>{

    private final WoodenGearShaperRecipeSerializer.IFactory<T> factory;

    public WoodenGearShaperRecipeSerializer(WoodenGearShaperRecipeSerializer.IFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public @NotNull T fromJson(@NotNull ResourceLocation recipeId, JsonObject json) {
        String ingredient;
        ItemStack itemstack;

        if (!json.has("ingredientTag")) {
            throw new com.google.gson.JsonSyntaxException("Missing ingredient tag, expected to find a string");
        }

        if (json.get("ingredientTag").isJsonPrimitive()) {
            ingredient = json.get("ingredientTag").getAsString();
        } else {
            throw new com.google.gson.JsonSyntaxException("Missing ingredient tag, expected to find a string");
        }
        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }

        if (json.get("result").isJsonObject()) {
            itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

        } else {
            String result = GsonHelper.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(result);

            if (ForgeRegistries.ITEMS.containsKey(resourcelocation)) throw new IllegalStateException("Item " + result + " does not exist");
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }

        int ingredientCount = GsonHelper.getAsInt(json, "ingredientCount", 1);

        int processTime = GsonHelper.getAsInt(json, "processingTime", 200);

        return this.factory.create(recipeId, ingredient, ingredientCount, itemstack, processTime);
    }

    @Override
    public @Nullable T fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
        String ingredient = buffer.readUtf();
        int ingredientCount = buffer.readVarInt();
        ItemStack itemstack = buffer.readItem();
        int processingTime = buffer.readVarInt();

        return this.factory.create(recipeId, ingredient, ingredientCount, itemstack, processingTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.ingredient);
        buffer.writeInt(recipe.ingredientCount);
        buffer.writeItem(recipe.result);
        buffer.writeVarInt(recipe.processingTime);
    }

    public interface IFactory<T extends WoodenGearShaperRecipe> {
        T create(ResourceLocation resourceLocation, String ingredient, int ingredientCount, ItemStack result, int processingTime);
    }
}
