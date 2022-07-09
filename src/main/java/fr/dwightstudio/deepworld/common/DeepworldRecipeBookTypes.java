package fr.dwightstudio.deepworld.common;

import net.minecraft.world.inventory.RecipeBookType;

public class DeepworldRecipeBookTypes {
    public static RecipeBookType LATHE;
    public static RecipeBookType PRESS;
    public static RecipeBookType GEAR_SHAPER;

    public DeepworldRecipeBookTypes() {
        LATHE = RecipeBookType.create("LATHE");
        PRESS = RecipeBookType.create("PRESS");
        GEAR_SHAPER = RecipeBookType.create("GEAR_SHAPER");
    }
}
