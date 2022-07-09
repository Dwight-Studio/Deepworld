package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.recipe.LatheRecipe;
import fr.dwightstudio.deepworld.common.recipe.serializers.LatheRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeSerializers {

    public static RegistryObject<RecipeSerializer<LatheRecipe>> LATHING;

    public DeepworldRecipeSerializers() {
        LATHING = Deepworld.RECIPE_SERIALIZERS.register("lathing", LatheRecipeSerializer::new);
    }
}
