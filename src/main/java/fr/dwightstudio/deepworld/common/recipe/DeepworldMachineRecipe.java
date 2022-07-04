package fr.dwightstudio.deepworld.common.recipe;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public abstract class DeepworldMachineRecipe implements Recipe<Inventory> {

    private final ResourceLocation id;

    protected DeepworldMachineRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public final ResourceLocation getId() {
        return id;
    }

    @Override
    public final NonNullList<ItemStack> getRemainingItems(Inventory inv) {
        return NonNullList.create();
    }

    @Override
    public final String getGroup() {
        return Deepworld.MOD_ID;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    public final ItemStack getCraftingResult(Inventory inv) {
        return getResult();
    }

    @Override
    public final NonNullList<Ingredient> getIngredients() {
        return NonNullList.create();
    }

    @Override
    public final boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public final RecipeSerializer<?> getSerializer() {
        return null;
    }

    // Get output (for display purposes only)
    @Override
    public final ItemStack getRecipeOutput() {
        return getResult();
    }

    @Override
    public final ItemStack getIcon() {
        return getResult();
    }

    /**
     * @param inv Inputs inventory
     * @param worldIn World instance
     * @return same inventory with decreased itemStacks
     */
    abstract public Inventory applyCraft(Inventory inv, Level worldIn);

    /**
     * @return result of the recipe
     */
    abstract public ItemStack getResult();

    /**
     * @param inv Inputs inventory
     */
    abstract public boolean isValidInput(Inventory inv, Level worldIn);
}
