package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.recipe.MachineRecipe;
import fr.dwightstudio.deepworld.common.recipe.serializers.MachineRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeSerializers {

    public static RegistryObject<RecipeSerializer<MachineRecipe>> LATHING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> PRESSING;

    public DeepworldRecipeSerializers() {
        LATHING =  Deepworld.RECIPE_SERIALIZERS.register("lathing",  () -> new MachineRecipeSerializer(DeepworldRecipeTypes.LATHING.get()));
        PRESSING = Deepworld.RECIPE_SERIALIZERS.register("pressing", () -> new MachineRecipeSerializer(DeepworldRecipeTypes.PRESSING.get()));
    }
}
