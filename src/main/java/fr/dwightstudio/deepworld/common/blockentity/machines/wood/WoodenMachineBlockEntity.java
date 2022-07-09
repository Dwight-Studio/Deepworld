package fr.dwightstudio.deepworld.common.blockentity.machines.wood;

import fr.dwightstudio.deepworld.common.menus.WoodenMachineMenu;
import fr.dwightstudio.deepworld.common.recipes.MachineRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static fr.dwightstudio.deepworld.common.menus.WoodenMachineMenu.*;

public class WoodenMachineBlockEntity extends BaseContainerBlockEntity implements MenuProvider, WorldlyContainer, RecipeHolder, StackedContentsCompatible, Container {

    public  static final int MAX_INERTIA = 200;
    public static final int INERTIA_PER_CLICK = 10;

    int inertia;
    int processProgress;
    int processTimeTotal;

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int dataID) {
            return switch (dataID) {
                case INERTIA_DATA -> WoodenMachineBlockEntity.this.inertia;
                case PROCESS_PROGRESS_DATA -> WoodenMachineBlockEntity.this.processProgress;
                case PROCESS_TIME_DATA -> WoodenMachineBlockEntity.this.processTimeTotal;
                default -> 0;
            };
        }

        public void set(int dataID, int value) {
            switch (dataID) {
                case INERTIA_DATA -> WoodenMachineBlockEntity.this.inertia = value;
                case PROCESS_PROGRESS_DATA -> WoodenMachineBlockEntity.this.processProgress = value;
                case PROCESS_TIME_DATA -> WoodenMachineBlockEntity.this.processTimeTotal = value;
            }

        }

        public int getCount() {
            return WoodenMachineMenu.DATA_COUNT;
        }
    };
    
    RecipeManager.CachedCheck<Container, MachineRecipe> quickCheck;
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private final MenuType<WoodenMachineMenu> menuType;
    private final RecipeBookType recipeBookType;
    private final RecipeType<MachineRecipe> recipeType;

    public WoodenMachineBlockEntity(BlockEntityType<?> blockEntityType, MenuType<WoodenMachineMenu> menuType, RecipeBookType recipeBookType, BlockPos blockPos, BlockState blockState, RecipeType<MachineRecipe> recipeType) {
        super(blockEntityType, blockPos, blockState);
        this.quickCheck = RecipeManager.createCheck(recipeType);
        this.menuType = menuType;
        this.recipeBookType = recipeBookType;
        this.recipeType = recipeType;
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compoundTag, this.items);
        inertia = compoundTag.getInt("Inertia");
        processProgress = compoundTag.getInt("ProcessProgress");
        processTimeTotal = compoundTag.getInt("ProcessTimeTotal");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putInt("Inertia", inertia);
        compoundTag.putInt("ProcessProgress", processProgress);
        compoundTag.putInt("ProcessTimeTotal", processTimeTotal);
        ContainerHelper.saveAllItems(compoundTag, this.items);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.literal("Missing default name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerID, @NotNull Inventory inventory, @NotNull Player player) {
        return new WoodenMachineMenu(this.menuType, this.recipeBookType, this.recipeType, containerID, inventory, this, this.dataAccess);
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerID, @NotNull Inventory inventory) {
        return new WoodenMachineMenu(this.menuType, this.recipeBookType, this.recipeType, containerID, inventory, this, this.dataAccess);
    }

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        if (direction == Direction.UP) {
            return SLOTS_FOR_UP;
        } else if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItem(int slotID, @NotNull ItemStack itemStack) {
        return slotID != OUTPUT_SLOT;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slotID, @NotNull ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(slotID, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slotID, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.items) {
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack getItem(int slotID) {
        return this.items.get(slotID);
    }

    @Override
    public @NotNull ItemStack removeItem(int slotID, int number) {
        return ContainerHelper.removeItem(this.items, slotID, number);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slotID) {
        return ContainerHelper.takeItem(this.items, slotID);
    }



    @Override
    public void setItem(int slotID, ItemStack newItemStack) {
        ItemStack currentItemStack = this.items.get(slotID);
        boolean flag = !newItemStack.isEmpty() && newItemStack.sameItem(currentItemStack) && ItemStack.tagMatches(newItemStack, currentItemStack);
        this.items.set(slotID, newItemStack);
        if (newItemStack.getCount() > this.getMaxStackSize()) {
            newItemStack.setCount(this.getMaxStackSize());
        }

        if (slotID == 0 && !flag) {
            this.processTimeTotal = getTotalProcessTime(this.level, this);
            this.processProgress = 0;
            this.setChanged();
        }
    }

    private int getTotalProcessTime(Level level, BlockEntity blockEntity) {
        WoodenMachineBlockEntity woodenMachineBlockEntity = (WoodenMachineBlockEntity) blockEntity;
        return woodenMachineBlockEntity.quickCheck.getRecipeFor(woodenMachineBlockEntity, level).map(MachineRecipe::getProcessTime).orElse(1);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> p_40134_) {

    }

    @Nullable
    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void fillStackedContents(@NotNull StackedContents stackedContents) {
        for (ItemStack itemStack : this.items) {
            stackedContents.accountStack(itemStack);
        }
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        WoodenMachineBlockEntity woodenMachineBlockEntity = (WoodenMachineBlockEntity) blockEntity;
        MachineRecipe recipe;
        ItemStack inputItem = woodenMachineBlockEntity.items.get(INPUT_SLOT);
        ItemStack outputItem = woodenMachineBlockEntity.items.get(OUTPUT_SLOT);

        // The inertia must be greater than 0 for the process to start
        if (woodenMachineBlockEntity.inertia > 0) {
            woodenMachineBlockEntity.inertia--;

            // The input item slot must contain something
            if (!inputItem.isEmpty()) {
                recipe = woodenMachineBlockEntity.quickCheck.getRecipeFor(woodenMachineBlockEntity, level).orElse(null);

                // The input item must be a recipe ingredient and the output slot content must contain the result item or be empty
                // else make sure the process progress is set to 0
                if (canProcess(recipe, woodenMachineBlockEntity)) {
                    woodenMachineBlockEntity.processTimeTotal = recipe.getProcessTime();
                    woodenMachineBlockEntity.processProgress++;

                    // The process is finished
                    if (woodenMachineBlockEntity.processProgress >= woodenMachineBlockEntity.processTimeTotal) {
                        woodenMachineBlockEntity.processProgress = 0;

                        // If the output is empty, set the result item else add 1 item to output slot
                        if (outputItem.isEmpty()) {
                            woodenMachineBlockEntity.items.set(OUTPUT_SLOT, recipe.assemble(woodenMachineBlockEntity));
                        } else {
                            outputItem.grow(recipe.assemble(woodenMachineBlockEntity).getCount());
                        }

                        // Empty or decrement the input itemstack
                        if (inputItem.getCount() > recipe.getIngredient().getItems()[0].getCount()) {
                            inputItem.shrink(recipe.getIngredient().getItems()[0].getCount());
                        } else {
                            woodenMachineBlockEntity.items.set(INPUT_SLOT, ItemStack.EMPTY);
                        }
                    }
                } else {
                    woodenMachineBlockEntity.processProgress = 0;
                }
            }
        }
        // The inertia mustn't be < to 0, if so set it to 0
        if (woodenMachineBlockEntity.inertia < 0) { woodenMachineBlockEntity.inertia = 0; }
    }

    private static boolean canProcess(MachineRecipe recipe, WoodenMachineBlockEntity blockEntity) {
        if (recipe == null) return false;
        if (blockEntity.items.get(INPUT_SLOT).getCount() < recipe.getIngredient().getItems()[0].getCount()) return false;
        if (!blockEntity.items.get(OUTPUT_SLOT).sameItem(recipe.assemble(blockEntity)) && !blockEntity.items.get(OUTPUT_SLOT).isEmpty()) return false;
        if (blockEntity.items.get(OUTPUT_SLOT).getCount() + recipe.assemble(blockEntity).getCount() >= recipe.assemble(blockEntity).getMaxStackSize()) return false;

        return true;
    }


}
