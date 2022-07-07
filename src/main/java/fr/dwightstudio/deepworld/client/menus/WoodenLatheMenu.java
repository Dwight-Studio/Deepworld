package fr.dwightstudio.deepworld.client.menus;

import fr.dwightstudio.deepworld.common.DeepworldMenus;
import fr.dwightstudio.deepworld.common.DeepworldRecipeBookTypes;
import fr.dwightstudio.deepworld.common.DeepworldRecipeTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WoodenLatheMenu extends RecipeBookMenu<Container> {

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int SLOT_COUNT = 2;
    private static final int DATA_COUNT = 3;
    private static final int INV_START_SLOT = SLOT_COUNT;
    private static final int INV_END_SLOT = SLOT_COUNT + 27;
    private static final int HOTBAR_START_SLOT = INV_END_SLOT ;
    private static final int HOTBAR_END_SLOT = HOTBAR_START_SLOT + 9;
    private Container container;
    private ContainerData containerData;
    protected final Level level;
    private final RecipeType<?> recipeType;
    private final RecipeBookType recipeBookType;

    public WoodenLatheMenu(int containerID, Inventory inventory) {
        super(DeepworldMenus.WOODEN_LATHE_MENU.get(), containerID);
        this.recipeType = DeepworldRecipeTypes.TURNING.get();
        this.recipeBookType = DeepworldRecipeBookTypes.LATHE;
        this.container = new SimpleContainer(SLOT_COUNT);
        this.containerData = new SimpleContainerData(DATA_COUNT);
        this.level = inventory.player.getLevel();
        this.addSlot(new Slot(this.container, INPUT_SLOT, 56, 17));
        this.addSlot(new Slot(this.container, OUTPUT_SLOT, 116, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

        this.addDataSlots(this.containerData);
    }

    @Override
    public void fillCraftSlotsStackedContents(@NotNull StackedContents stackedContents) {
        if (this.container instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible)this.container).fillStackedContents(stackedContents);
        }
    }

    @Override
    public void clearCraftingContent() {
        this.getSlot(INPUT_SLOT).set(ItemStack.EMPTY);
        this.getSlot(OUTPUT_SLOT).set(ItemStack.EMPTY);
    }

    @Override
    public boolean recipeMatches(@NotNull Recipe<? super Container> p_40118_) {
        return false;
    }

    @Override
    public int getResultSlotIndex() {
        return OUTPUT_SLOT;
    }

    @Override
    public int getGridWidth() {
        return 1;
    }

    @Override
    public int getGridHeight() {
        return 1;
    }

    @Override
    public int getSize() {
        return SLOT_COUNT;
    }

    @Override
    public @NotNull RecipeBookType getRecipeBookType() {
        return this.recipeBookType;
    }

    @Override
    public boolean shouldMoveToInventory(int slotID) {
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int slotID) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotID);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (slotID == 1) {
                if (!this.moveItemStackTo(itemstack1, INV_START_SLOT, HOTBAR_END_SLOT, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (slotID != 0) {
                if (this.isTurnable(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, INPUT_SLOT, OUTPUT_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, INV_START_SLOT, INV_END_SLOT, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public boolean isTurnable(ItemStack itemStack) {
        // TODO: TODO
        return true;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }
}
