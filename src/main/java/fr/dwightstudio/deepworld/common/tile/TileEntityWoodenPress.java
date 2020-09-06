package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

public class TileEntityWoodenPress extends TileEntity implements IInventory, ITickable {

    // Block custom name
    private String customName;

    // The number of burn ticks remaining on the current piece of fuel
    private int burnTimeRemaining;

    // The initial fuel value of the currently burning fuel (in ticks of burn duration)
    private int burnTimeInitialValue;

    // The number of ticks the current item has been cooking
    private short cookTime;

    // The number of slots
    private final int TOTAL_SLOTS_COUNT = 2;

    // The number of ticks required to cook an item
    private static final short COOK_TIME_FOR_COMPLETION = 200;  // vanilla value is 200 = 10 seconds

    // Create the itemStacks variable that will store store the itemStacks
    private ItemStack[] itemStacks;

    // Initialize itemStacks
    public TileEntityWoodenPress() {
        itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];
    }

    // Gets the number of slots in the inventory
    @Override
    public int getSizeInventory() {
        return itemStacks.length;
    }

    // Check if inventory is empty
    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : itemStacks) {
            if (!itemstack.isEmpty()) {  // isEmpty()
                return false;
            }
        }

        return true;
    }

    // Gets the stack in the given slot
    @Override
    public ItemStack getStackInSlot(int index) {
        return itemStacks[index];
    }

    // Removes some of the units from itemstack in the given slot, and returns as a separate itemstack
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemInSlot = getStackInSlot(index);
        if (itemInSlot.isEmpty()) return ItemStack.EMPTY;

        ItemStack itemReturned;
        if (itemInSlot.getCount() <= count) {
            itemReturned = removeStackFromSlot(index);
        } else {
            itemReturned = itemInSlot.splitStack(count);
            if (itemInSlot.getCount() == 0) {
                setInventorySlotContents(index, ItemStack.EMPTY);
            }
        }

        markDirty();
        return itemReturned;
    }

    // Remove and return ItemStack in given slot
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = getStackInSlot(index);
        if (!itemStack.isEmpty()) setInventorySlotContents(index, ItemStack.EMPTY);
        return itemStack;
    }

    // Replace ItemStack in given slot
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.itemStacks[index] = stack;
        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) stack.setCount(getInventoryStackLimit());
        markDirty();
    }

    // Gets max stack size
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        switch (index) {
            case 0:
                break;
            case 1:
                break;
        }
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    // Clear slots
    @Override
    public void clear() {
        Arrays.fill(itemStacks, ItemStack.EMPTY);
    }

    // Default custom name
    @Override
    public String getName() {
        return "container." + Deepworld.MOD_ID + "_wooden_press.name";
    }

    // Check if container has custom name
    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    // Update method (ran every tick)
    @Override
    public void update() {

    }
}
