package fr.dwightstudio.deepworld.common.recipe;

import fr.dwightstudio.deepworld.common.utils.MachineTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MachineRecipe implements Recipe<Container> {

    private final RecipeType<?> recipeType;
    private final ResourceLocation resourceLocation;
    private final Ingredient ingredient;
    private final ItemStack resultItem;
    private final int processTime;
    private final MachineTier machineTier;

    public MachineRecipe(RecipeType<?> recipeType, ResourceLocation resourceLocation, Ingredient ingredient, ItemStack resultItem, int processTime, MachineTier machineTier) {
        this.recipeType = recipeType;
        this.resourceLocation = resourceLocation;
        this.ingredient = ingredient;
        this.resultItem = resultItem;
        this.processTime = processTime;
        this.machineTier = machineTier;
    }

    @Override
    public boolean matches(@NotNull Container container, @NotNull Level level) {
        return this.ingredient.test(container.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull Container container) {
        return this.resultItem.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return this.resultItem;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.resourceLocation;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return this.recipeType;
    }

    public int getProcessTime() {
        return this.processTime;
    }

    public MachineTier getMachineTier() {
        return this.machineTier;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }
}
