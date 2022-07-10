package fr.dwightstudio.deepworld.common.recipes;

import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeSerializers;
import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class GearShaperRecipe extends MachineRecipe {

    public GearShaperRecipe(RecipeType<?> recipeType, ResourceLocation resourceLocation, Ingredient ingredient, int ingredientCount, ItemStack resultItem, int processTime, MachineTier machineTier) {
        super(recipeType, resourceLocation, ingredient, ingredientCount, resultItem, processTime, machineTier);
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return DeepworldRecipeSerializers.GEAR_SHAPING.get();
    }
}
