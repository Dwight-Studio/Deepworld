package fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper;

import fr.dwightstudio.deepworld.common.recipe.DeepworldMachineRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class WoodenGearShaperRecipe extends DeepworldMachineRecipe {

    // Infos
    public static final IRecipeType<WoodenGearShaperRecipe> SHAPING = IRecipeType.register("wooden_gear_shaper_shaping");

    private final IRecipeType<?> type;
    final Item[] input;
    final ItemStack result;
    final String ingredient;
    final int ingredientCount;
    final int processingTime;

    public WoodenGearShaperRecipe(ResourceLocation resourceLocation, String ingredient, int ingredientCount, ItemStack result, int processingTime) {
        super(resourceLocation);
        type = SHAPING;
        this.input = ItemTags.getCollection().getOrCreate(new ResourceLocation(ingredient)).getAllElements().toArray(new Item[0]);
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
        this.result = result;
        this.processingTime = processingTime;
    }

    // Check if input is valid
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for (Item item : input) {
            if (inv.getStackInSlot(0).getItem() == item) return true;
        }
        return false;
    }

    @Override
    public IRecipeType<?> getType() {
        return type;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    /**
     * @param inv Inputs inventory
     * @return same inventory with decreased itemStacks
     */
    @Override
    @Nonnull
    public IInventory applyCraft(IInventory inv, World worldIn) {
        if (matches(inv, worldIn)) {
            for (int i = 0; i<inv.getSizeInventory(); i++) {
                inv.decrStackSize(i, ingredientCount);
            }
        }
        return inv;
    }

    public ItemStack getResult() {
        return result.copy();
    }

    /**
     * @param inv Inputs inventory
     */
    @Override
    public boolean isValidInput(IInventory inv, World worldIn) {
        return matches(inv, worldIn) && inv.getStackInSlot(0).getCount() >= ingredientCount;
    }
}
