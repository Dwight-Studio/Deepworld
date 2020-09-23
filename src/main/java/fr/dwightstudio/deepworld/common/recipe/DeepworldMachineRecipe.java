package fr.dwightstudio.deepworld.common.recipe;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class DeepworldMachineRecipe implements IRecipe<IInventory> {

    private final ResourceLocation id;

    protected DeepworldMachineRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public final ResourceLocation getId() {
        return id;
    }

    @Override
    public final NonNullList<ItemStack> getRemainingItems(IInventory inv) {
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
    public final ItemStack getCraftingResult(IInventory inv) {
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
    public final IRecipeSerializer<?> getSerializer() {
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
    abstract public IInventory applyCraft(IInventory inv, World worldIn);

    /**
     * @return result of the recipe
     */
    abstract public ItemStack getResult();

    /**
     * @param inv Inputs inventory
     */
    abstract public boolean isValidInput(IInventory inv, World worldIn);
}
