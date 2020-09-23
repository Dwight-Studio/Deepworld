package fr.dwightstudio.deepworld.common.recipe.wooden_press;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenPressRecipeSerializer<T extends WoodenPressRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final WoodenPressRecipeSerializer.IFactory<T> factory;

    public WoodenPressRecipeSerializer(WoodenPressRecipeSerializer.IFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        String ingredient;
        ItemStack itemstack;

        if (!json.has("ingredientTag")) {
            throw new com.google.gson.JsonSyntaxException("Missing ingredient tag, expected to find a string");
        }

        if (json.get("ingredientTag").isJsonPrimitive()) {
            ingredient = json.get("ingredientTag").getAsString();

            ResourceLocation location = ResourceLocation.tryCreate(ingredient);
            if (location != null) {
                if (ItemTags.getCollection().getOrCreate(location).getEntries().isEmpty()) {
                    throw new IllegalStateException("Empty ingredient tag");
                }
            } else throw new IllegalStateException("Invalid ingredient tag resource location");
        } else {
            throw new com.google.gson.JsonSyntaxException("Missing ingredient tag, expected to find a string");
        }
        if (!json.has("result")) {
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        }

        if (json.get("result").isJsonObject()) {
            itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));

        } else {
            String result = JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(result);

            if (ForgeRegistries.ITEMS.containsKey(resourcelocation)) throw new IllegalStateException("Item " + result + " does not exist");
            itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(resourcelocation));
        }

        int ingredientCount = JSONUtils.getInt(json, "ingredientCount", 1);

        int processTime = JSONUtils.getInt(json, "processingTime", 200);

        return this.factory.create(recipeId, ingredient, ingredientCount, itemstack, processTime);
    }

    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String ingredient = buffer.readString();
        int ingredientCount = buffer.readVarInt();
        ItemStack itemstack = buffer.readItemStack();
        int processingTime = buffer.readVarInt();

        return this.factory.create(recipeId, ingredient, ingredientCount, itemstack, processingTime);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.ingredient);
        buffer.writeInt(recipe.ingredientCount);
        buffer.writeItemStack(recipe.result);
        buffer.writeVarInt(recipe.processingTime);
    }

    public interface IFactory<T extends WoodenPressRecipe> {
        T create(ResourceLocation resourceLocation, String ingredient, int ingredientCount, ItemStack result, int processingTime);
    }
}
