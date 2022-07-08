package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.recipe.LatheRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeTypes {

    public static RegistryObject<RecipeType<LatheRecipe>> TURNING;

    public DeepworldRecipeTypes() {
        TURNING = Deepworld.RECIPE_TYPES.register("turning", () -> createRecipeType("turning"));
    }

    public static <T extends Recipe<? extends Container>> RecipeType<T> createRecipeType(String name) {
        return new RecipeType<T>() {
            public String toString() {
                return name;
            }
        };
    }
}
