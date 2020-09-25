package fr.dwightstudio.deepworld.common.recipe;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper.WoodenGearShaperRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper.WoodenGearShaperRecipeSerializer;
import fr.dwightstudio.deepworld.common.recipe.wooden_lathe.WoodenLatheRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_lathe.WoodenLatheRecipeSerializer;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldRecipeRegister {

    @SubscribeEvent
    public static void onRecipeSerializerRegister(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().registerAll(new WoodenPressRecipeSerializer<>(WoodenPressRecipe::new).setRegistryName(Deepworld.MOD_ID, "wooden_press_pressing"));
        event.getRegistry().registerAll(new WoodenGearShaperRecipeSerializer<>(WoodenGearShaperRecipe::new).setRegistryName(Deepworld.MOD_ID, "wooden_gear_shaper_shaping"));
        event.getRegistry().registerAll(new WoodenLatheRecipeSerializer<>(WoodenLatheRecipe::new).setRegistryName(Deepworld.MOD_ID, "wooden_lathe"));
    }

}
