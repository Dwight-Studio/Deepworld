package fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper;

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

public class WoodenGearShaperRecipe implements IRecipe<IInventory> {
    // Infos
    public static final IRecipeType<WoodenGearShaperRecipe> SHAPING = IRecipeType.register("wooden_gear_shaper_shaping");

    private final IRecipeType<?> type;
    private final ResourceLocation id;
    final String group;
    final Ingredient ingredient;
    final ItemStack result;
    final int processingTime;

    public WoodenGearShaperRecipe(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack result, int processingTime) {
        type = SHAPING;
        id = resourceLocation;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.processingTime = processingTime;
    }

    // Check if input is valid
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    // Get output
    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return result;
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
        return new ItemStack(DeepworldItems.WOODEN_GEAR_SHAPER);
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
