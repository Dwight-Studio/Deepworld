package fr.dwightstudio.deepworld.common.blockentity;

import fr.dwightstudio.deepworld.client.menus.WoodenLatheMenu;
import fr.dwightstudio.deepworld.common.DeepworldBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodenLatheBlockEntity extends BlockEntity implements MenuProvider, WorldlyContainer, RecipeHolder, StackedContentsCompatible {

    private static final int SLOT_INPUT = 0;
    private static final int SLOT_OUTPUT = 1;
    private static final int[] SLOTS_FOR_UP = {0};
    private static final int[] SLOTS_FOR_DOWN = {1};
    private static final int[] SLOTS_FOR_SIDES = {};

    int inertia;
    int processProgress;
    int processTimeTotal;
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public WoodenLatheBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_LATHE.get(), blockPos, blockState);
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
        return new WoodenLatheMenu(containerID, inventory);
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
        return slotID != SLOT_OUTPUT;
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
}
