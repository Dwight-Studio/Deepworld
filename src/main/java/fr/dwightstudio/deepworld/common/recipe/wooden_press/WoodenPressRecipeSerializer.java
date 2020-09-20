package fr.dwightstudio.deepworld.common.recipe.wooden_press;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodenPressRecipeSerializer<T extends WoodenPressRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private final WoodenPressRecipeSerializer.IFactory<T> factory;

    public WoodenPressRecipeSerializer(WoodenPressRecipeSerializer.IFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {

        String group = JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(jsonelement);
        ItemStack itemstack;

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

        int processTime = JSONUtils.getInt(json, "processingTime", 200);

        return this.factory.create(recipeId, group, ingredient, itemstack, processTime);
    }

    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        ItemStack itemstack = buffer.readItemStack();
        int i = buffer.readVarInt();

        return this.factory.create(recipeId, s, ingredient, itemstack, i);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.group);
        recipe.ingredient.write(buffer);
        buffer.writeItemStack(recipe.result);
        buffer.writeVarInt(recipe.processingTime);
    }

    public interface IFactory<T extends WoodenPressRecipe> {
        T create(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack result, int processingTime);
    }
}
