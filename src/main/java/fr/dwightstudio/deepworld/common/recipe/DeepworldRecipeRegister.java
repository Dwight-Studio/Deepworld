package fr.dwightstudio.deepworld.common.recipe;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipe;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldRecipeRegister {

    @SubscribeEvent
    public static void onRecipeSerializerRegister(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().registerAll(new WoodenPressRecipeSerializer<>(WoodenPressRecipe::new).setRegistryName(Deepworld.MOD_ID, "wooden_press_pressing"));
    }

}
