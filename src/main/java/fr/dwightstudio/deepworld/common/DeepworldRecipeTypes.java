package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeTypes {

    public static RegistryObject<RecipeType<MachineRecipe>> LATHING;
    public static RegistryObject<RecipeType<MachineRecipe>> PRESSING;
    public static RegistryObject<RecipeType<MachineRecipe>> GEAR_SHAPING;

    public DeepworldRecipeTypes() {
        LATHING = Deepworld.RECIPE_TYPES.register("lathing", () -> createRecipeType("lathing"));
        PRESSING = Deepworld.RECIPE_TYPES.register("pressing", () -> createRecipeType("pressing"));
        GEAR_SHAPING = Deepworld.RECIPE_TYPES.register("gear_shaping", () -> createRecipeType("gear_shaping"));
    }

    public static <T extends Recipe<? extends Container>> RecipeType<T> createRecipeType(String name) {
        return new RecipeType<T>() {
            public String toString() {
                return name;
            }
        };
    }
}
