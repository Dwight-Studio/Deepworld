package fr.dwightstudio.deepworld.common.blockentity;

import fr.dwightstudio.deepworld.common.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.DeepworldRecipeTypes;
import fr.dwightstudio.deepworld.common.menus.WoodenLatheMenu;
import fr.dwightstudio.deepworld.common.recipe.LatheRecipe;
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
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static fr.dwightstudio.deepworld.common.menus.WoodenLatheMenu.*;

public class WoodenLatheBlockEntity extends BlockEntity implements MenuProvider, WorldlyContainer, RecipeHolder, StackedContentsCompatible, Container {

    public  static final int MAX_INERTIA = 200;
    public static final int INERTIA_PER_CLICK = 10;

    int inertia;
    int processProgress;
    int processTimeTotal;

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int dataID) {
            switch (dataID) {
                case INERTIA_DATA:
                    return WoodenLatheBlockEntity.this.inertia;
                case PROCESS_PROGRESS_DATA:
                    return WoodenLatheBlockEntity.this.processProgress;
                case PROCESS_TIME_DATA:
                    return WoodenLatheBlockEntity.this.processTimeTotal;
                default:
                    return 0;
            }
        }

        public void set(int dataID, int value) {
            switch (dataID) {
                case INERTIA_DATA:
                    WoodenLatheBlockEntity.this.inertia = value;
                    break;
                case PROCESS_PROGRESS_DATA:
                    WoodenLatheBlockEntity.this.processProgress = value;
                    break;
                case PROCESS_TIME_DATA:
                    WoodenLatheBlockEntity.this.processTimeTotal = value;
                    break;
            }

        }

        public int getCount() {
            return WoodenLatheMenu.DATA_COUNT;
        }
    };
    
    RecipeManager.CachedCheck<Container, LatheRecipe> quickCheck;
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public WoodenLatheBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_LATHE.get(), blockPos, blockState);
        this.quickCheck = RecipeManager.createCheck(DeepworldRecipeTypes.TURNING.get());
    }



    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        this.items = NonNullList.withSize(2, ItemStack.EMPTY);
        inertia = compoundTag.getInt("Inertia");
        processProgress = compoundTag.getInt("ProcessTime");
        processTimeTotal = compoundTag.getInt("ProcessTimeTotal");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putInt("Inertia", inertia);
        compoundTag.putInt("ProcessTime", processProgress);
        compoundTag.putInt("ProcessTimeTotal", processTimeTotal);
        ContainerHelper.saveAllItems(compoundTag, this.items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("container.deepworld.wooden_press");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerID, Inventory inventory, Player player) {
        return new WoodenLatheMenu(containerID, inventory, this.dataAccess);
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
        return this.items.get(0);
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

    private int getTotalProcessTime(Level level, BlockEntity blockEntity) {return 0;}

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
    public void fillStackedContents(StackedContents stackedContents) {
        for (ItemStack itemStack : this.items) {
            stackedContents.accountStack(itemStack);
        }
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        WoodenLatheBlockEntity woodenLatheBlockEntity = (WoodenLatheBlockEntity) blockEntity;
        LatheRecipe recipe;
        ItemStack inputItem = woodenLatheBlockEntity.items.get(INPUT_SLOT);
        ItemStack outputItem = woodenLatheBlockEntity.items.get(OUTPUT_SLOT);

        if (woodenLatheBlockEntity.inertia > 0) {
            woodenLatheBlockEntity.inertia--;
            if (!inputItem.isEmpty()) {
                recipe = woodenLatheBlockEntity.quickCheck.getRecipeFor(woodenLatheBlockEntity, level).orElse(null);
                if (canProcess(recipe, woodenLatheBlockEntity)) {
                    woodenLatheBlockEntity.processTimeTotal = recipe.getProcessTime();
                    woodenLatheBlockEntity.processProgress++;
                    if (woodenLatheBlockEntity.processProgress >= woodenLatheBlockEntity.processTimeTotal) {
                        woodenLatheBlockEntity.processProgress = 0;
                        if (outputItem.isEmpty()) {
                            woodenLatheBlockEntity.items.set(1, recipe.assemble(woodenLatheBlockEntity));
                        } else {
                            outputItem.grow(recipe.assemble(woodenLatheBlockEntity).getCount());
                        }
                    }
                } else {
                    woodenLatheBlockEntity.processProgress = 0;
                }
            }
        }
        if (woodenLatheBlockEntity.inertia < 0) { woodenLatheBlockEntity.inertia = 0; }
    }

    private static boolean canProcess(LatheRecipe recipe, WoodenLatheBlockEntity blockEntity) {
        if (recipe == null) return false;
        if (!blockEntity.items.get(OUTPUT_SLOT).sameItem(recipe.assemble(blockEntity)) && !blockEntity.items.get(OUTPUT_SLOT).isEmpty()) return false;
        if (blockEntity.items.get(OUTPUT_SLOT).getCount() + recipe.assemble(blockEntity).getCount() >= recipe.assemble(blockEntity).getMaxStackSize()) return false;

        return true;
    }
}
