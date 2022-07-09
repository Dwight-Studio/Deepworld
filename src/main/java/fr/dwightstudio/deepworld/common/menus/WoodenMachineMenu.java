package fr.dwightstudio.deepworld.common.menus;

import fr.dwightstudio.deepworld.common.blockentity.machines.wood.WoodenMachineBlockEntity;
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
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

public class WoodenMachineMenu extends RecipeBookMenu<Container> {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int[] SLOTS_FOR_UP = {0};
    public static final int[] SLOTS_FOR_DOWN = {1};
    public static final int[] SLOTS_FOR_SIDES = {};
    public static final int SLOT_COUNT = 2;
    public static final int INERTIA_DATA = 0;
    public static final int PROCESS_PROGRESS_DATA = 1;
    public static final int PROCESS_TIME_DATA = 2;
    public  static final int DATA_COUNT = 3;
    private static final int INV_START_SLOT = SLOT_COUNT;
    private static final int INV_END_SLOT = SLOT_COUNT + 27;
    private static final int HOTBAR_START_SLOT = INV_END_SLOT ;
    private static final int HOTBAR_END_SLOT = HOTBAR_START_SLOT + 9;
    private final Container container;
    private final ContainerData containerData;
    protected final Level level;
    private final RecipeType<MachineRecipe> recipeType;
    private final RecipeBookType recipeBookType;

    public WoodenMachineMenu(int containerID, Inventory inventory) {
        this(null, null, null, containerID, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(WoodenMachineMenu.DATA_COUNT));
    }

    public WoodenMachineMenu(MenuType<WoodenMachineMenu> menuType, RecipeBookType recipeBookType, RecipeType<MachineRecipe> recipeType, int containerID, Inventory inventory, Container container, ContainerData containerData) {
        super(menuType, containerID);
        this.recipeType = recipeType;
        this.recipeBookType = recipeBookType;
        this.container = container;
        this.containerData = containerData;
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
    public boolean recipeMatches(@NotNull Recipe<? super Container> recipe) {
        return recipe.matches(this.container, this.level);
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
        return slotID == INPUT_SLOT;
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
        return this.level.getRecipeManager().getRecipeFor((RecipeType<MachineRecipe>)this.recipeType, new SimpleContainer(itemStack), this.level).isPresent();

    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return this.container.stillValid(player);
    }

    public double getProgress() {
        return (double) this.containerData.get(PROCESS_PROGRESS_DATA) / (double)this.containerData.get(PROCESS_TIME_DATA);
    }

    public double getInertiaProgress() {
        return (double) this.containerData.get(INERTIA_DATA) / (double) WoodenMachineBlockEntity.MAX_INERTIA;
    }

    @Override
    public boolean clickMenuButton(@NotNull Player player, int buttonID) {
        this.containerData.set(INERTIA_DATA, Math.min(WoodenMachineBlockEntity.INERTIA_PER_CLICK + this.containerData.get(INERTIA_DATA), WoodenMachineBlockEntity.MAX_INERTIA));
        return true;
    }
}
