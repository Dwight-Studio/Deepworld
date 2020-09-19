package fr.dwightstudio.deepworld.common.recipe.wooden_press;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class WoodenPressRecipe implements IRecipe<IInventory> {

    // Infos
    public static final IRecipeType<WoodenPressRecipe> pressing = IRecipeType.register("wooden_press_pressing");

    private final IRecipeType<?> type;;
    private final ResourceLocation id;
    final String group;
    final Ingredient ingredient;
    final ItemStack result;
    final int processingTime;

    public WoodenPressRecipe(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack result, int processingTime) {
        type = pressing;
        id = resourceLocation;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.processingTime = processingTime;
    }

    // Check if input is valid
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    // Get output
    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
    }

    // N/A
    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    // Get output (for display purposes only)
    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.ingredient);
        return nonNullList;
    }

    // Prevent from appearing in the recipe book
    @Override
    public boolean isDynamic() {
        return true;
    }


    @Override
    public ItemStack getIcon() {
        return new ItemStack(DeepworldItems.WOODEN_PRESS);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return type;
    }

    public int getProcessingTime() {
        return processingTime;
    }
}
