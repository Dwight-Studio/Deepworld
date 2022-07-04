package fr.dwightstudio.deepworld.common.recipe;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper.WoodenGearShaperRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper.WoodenGearShaperRecipeSerializer;
import fr.dwightstudio.deepworld.common.recipe.wooden_lathe.WoodenLatheRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_lathe.WoodenLatheRecipeSerializer;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class DeepworldRecipeRegister {

    @SubscribeEvent
    public static void onRecipeSerializerRegister(final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, helper -> {

            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_press_pressing"), new WoodenPressRecipeSerializer<>(WoodenPressRecipe::new));
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_gear_shaper_shaping"), new WoodenGearShaperRecipeSerializer<>(WoodenGearShaperRecipe::new));
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_lathe_lathing"), new WoodenLatheRecipeSerializer<>(WoodenLatheRecipe::new));
        });

    }

}
