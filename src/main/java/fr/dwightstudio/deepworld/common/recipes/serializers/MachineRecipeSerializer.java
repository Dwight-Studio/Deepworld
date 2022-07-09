package fr.dwightstudio.deepworld.common.recipes.serializers;

import com.google.gson.JsonObject;
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MachineRecipeSerializer implements RecipeSerializer<MachineRecipe> {

    private final RecipeType<? extends MachineRecipe> recipeTypes;

    public MachineRecipeSerializer(RecipeType<MachineRecipe> recipeTypes) {
        this.recipeTypes = recipeTypes;
    }

    @Override
    public @NotNull MachineRecipe fromJson(@NotNull ResourceLocation resourceLocation, @NotNull JsonObject jsonObject) {
        Ingredient inputIngredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "ingredients"));
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
        int processTime = GsonHelper.getAsInt(jsonObject, "processTime");
        String machineTierString = GsonHelper.getAsString(jsonObject, "machineTier");

        int ingredientCount = 1;
        if (GsonHelper.isNumberValue(jsonObject, "ingredientsCount")) {
            ingredientCount = GsonHelper.getAsInt(jsonObject, "ingredientsCount");
        }

        MachineTier machineTier = MachineTier.valueOf(machineTierString.toUpperCase());

        return new MachineRecipe(recipeTypes, resourceLocation, inputIngredient, ingredientCount, result, processTime, machineTier);
    }

    @Override
    public @Nullable MachineRecipe fromNetwork(@NotNull ResourceLocation resourceLocation, @NotNull FriendlyByteBuf buf) {
        String type = buf.readUtf();
        int processTime = buf.readInt();
        Ingredient ingredient = Ingredient.fromNetwork(buf);
        int ingredientCount = buf.readInt();
        MachineTier machineTier = MachineTier.valueOf(buf.readUtf().toUpperCase());
        ItemStack result = buf.readItem();

        return new MachineRecipe(recipeTypes, resourceLocation, ingredient, ingredientCount, result, processTime, machineTier);
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull MachineRecipe machineRecipe) {
        buf.writeUtf(machineRecipe.getGroup());
        buf.writeInt(machineRecipe.getProcessTime());
        machineRecipe.getIngredient().toNetwork(buf);
        buf.writeInt(machineRecipe.getIngredientCount());
        buf.writeUtf(machineRecipe.getMachineTier().name());
        buf.writeItem(machineRecipe.getResultItem());
    }
}
