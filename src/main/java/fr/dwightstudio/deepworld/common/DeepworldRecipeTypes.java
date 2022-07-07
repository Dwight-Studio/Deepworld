package fr.dwightstudio.deepworld.common;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeTypes {

    public static RegistryObject<RecipeType<?>> TURNING;

    public DeepworldRecipeTypes() {
        TURNING = Deepworld.RECIPE_TYPES.register("turning", () -> createRecipeType("turning"));
    }

    public static RecipeType<?> createRecipeType(String name) {
        return new RecipeType<>() {
            public String toString() {
                return name;
            }
        };
    }
}
