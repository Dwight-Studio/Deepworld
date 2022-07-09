package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import fr.dwightstudio.deepworld.common.recipes.serializers.MachineRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldRecipeSerializers {

    public static RegistryObject<RecipeSerializer<MachineRecipe>> LATHING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> PRESSING;
    public static RegistryObject<RecipeSerializer<MachineRecipe>> GEAR_SHAPING;

    public DeepworldRecipeSerializers() {
        LATHING =  Deepworld.RECIPE_SERIALIZERS.register("lathing",  () -> new MachineRecipeSerializer(DeepworldRecipeTypes.LATHING.get()));
        PRESSING = Deepworld.RECIPE_SERIALIZERS.register("pressing", () -> new MachineRecipeSerializer(DeepworldRecipeTypes.PRESSING.get()));
        GEAR_SHAPING = Deepworld.RECIPE_SERIALIZERS.register("gear_shaping", () -> new MachineRecipeSerializer(DeepworldRecipeTypes.GEAR_SHAPING.get()));
    }
}
