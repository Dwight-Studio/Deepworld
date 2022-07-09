package fr.dwightstudio.deepworld.common.recipe.serializers;

import com.google.gson.JsonObject;
import fr.dwightstudio.deepworld.common.DeepworldRecipeTypes;
import fr.dwightstudio.deepworld.common.recipe.LatheRecipe;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LatheRecipeSerializer implements RecipeSerializer<LatheRecipe> {

    @Override
    public @NotNull LatheRecipe fromJson(@NotNull ResourceLocation resourceLocation, @NotNull JsonObject jsonObject) {
        Ingredient inputIngredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "ingredients"));
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
        int processTime = GsonHelper.getAsInt(jsonObject, "processTime");
        String machineTierString = GsonHelper.getAsString(jsonObject, "machineTier");

        MachineTier machineTier = MachineTier.valueOf(machineTierString.toUpperCase());

        return new LatheRecipe(DeepworldRecipeTypes.LATHING.get(), resourceLocation, inputIngredient, result, processTime, machineTier);
    }

    @Override
    public @Nullable LatheRecipe fromNetwork(@NotNull ResourceLocation resourceLocation, @NotNull FriendlyByteBuf buf) {
        String type = buf.readUtf();
        int processTime = buf.readInt();
        Ingredient ingredient = Ingredient.fromNetwork(buf);
        MachineTier machineTier = MachineTier.valueOf(buf.readUtf().toUpperCase());
        ItemStack result = buf.readItem();

        return new LatheRecipe(DeepworldRecipeTypes.LATHING.get(), resourceLocation, ingredient, result, processTime, machineTier);
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull LatheRecipe latheRecipe) {
        buf.writeUtf(latheRecipe.getGroup());
        buf.writeInt(latheRecipe.getProcessTime());
        latheRecipe.getIngredient().toNetwork(buf);
        buf.writeUtf(latheRecipe.getMachineTier().name());
        buf.writeItem(latheRecipe.getResultItem());
    }
}
