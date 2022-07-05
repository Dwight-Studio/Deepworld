package fr.dwightstudio.deepworld.common.recipe.wooden_press;

import fr.dwightstudio.deepworld.common.recipe.DeepworldMachineRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class WoodenPressRecipe extends DeepworldMachineRecipe {

    // Infos
    public static final RecipeType<WoodenPressRecipe> PRESSING = RecipeType.register("wooden_press_pressing");

    private final RecipeType<?> type;
    final TagKey<Item> input;
    final ItemStack result;
    final String ingredient;
    final int ingredientCount;
    final int processingTime;

    public WoodenPressRecipe(ResourceLocation resourceLocation, String ingredient, int ingredientCount, ItemStack result, int processingTime) {
        super(resourceLocation);
        type = PRESSING;
        this.input = ItemTags.create(new ResourceLocation(ingredient));
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
        this.result = result;
        this.processingTime = processingTime;
    }

    // Check if input is valid
    @Override
    public boolean matches(Inventory inv, Level level) {
        for (Item item : Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(this.input)) {
            if (inv.getItem(0).getItem() == item) return true;
        }
        return false;
    }

    @Override
    public ItemStack assemble(Inventory p_44001_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }


    @Override
    public @NotNull RecipeType<?> getType() {
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
    public Inventory applyCraft(Inventory inv, Level worldIn) {
        if (matches(inv, worldIn)) {
            for (int i = 0; i<inv.getContainerSize(); i++) {
                inv.removeItem(i, ingredientCount);
            }
        }
        return inv;
    }

    @Override
    public ItemStack getResult() {
        return result.copy();
    }

    public @NotNull ItemStack getResultItem() {
        return result.copy();
    }

    /**
     * @param inv Inputs inventory
     */
    @Override
    public boolean isValidInput(Inventory inv, Level worldIn) {
        return matches(inv, worldIn) && inv.getItem(0).getCount() >= ingredientCount;
    }
}
