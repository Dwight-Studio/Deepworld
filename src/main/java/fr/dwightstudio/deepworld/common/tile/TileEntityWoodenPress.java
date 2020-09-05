package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;

public class TileEntityWoodenPress implements IInventory, ITickable {

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

    @Override
    public int getSizeInventory() {
        return itemStacks.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
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
