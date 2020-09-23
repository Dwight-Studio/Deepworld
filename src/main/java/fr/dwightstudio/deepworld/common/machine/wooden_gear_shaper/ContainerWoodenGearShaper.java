package fr.dwightstudio.deepworld.common.machine.wooden_gear_shaper;

import fr.dwightstudio.deepworld.common.DeepworldContainers;
import fr.dwightstudio.deepworld.common.machine.wooden_press.ContainerWoodenPress;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ContainerWoodenGearShaper extends Container {
    public static ContainerWoodenGearShaper createContainerServerSide(int windowID, PlayerInventory playerInventory,
                                                                 WoodenGearShaperZoneContents inputZoneContents,
                                                                 WoodenGearShaperZoneContents outputZoneContents,
                                                                 WoodenGearShaperStateData woodenGearShaperStateData) {
        return new ContainerWoodenGearShaper(windowID, playerInventory, inputZoneContents, outputZoneContents, woodenGearShaperStateData);
    }

    public static ContainerWoodenGearShaper createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {
        //  don't need extraData for this example; if you want you can use it to provide extra information from the server, that you can use
        //  when creating the client container
        //  eg String detailedDescription = extraData.readString(128);
        WoodenGearShaperZoneContents inputZoneContents = WoodenGearShaperZoneContents.createForClientSideContainer(INPUT_SLOTS_COUNT);
        WoodenGearShaperZoneContents outputZoneContents = WoodenGearShaperZoneContents.createForClientSideContainer(OUTPUT_SLOTS_COUNT);
        WoodenGearShaperStateData woodenGearShaperStateData = new WoodenGearShaperStateData();

        // on the client side there is no parent TileEntity to communicate with, so we:
        // 1) use dummy inventories and furnace state data (tracked ints)
        // 2) use "do nothing" lambda functions for canPlayerAccessInventory and markDirty
        return new ContainerWoodenGearShaper(windowID, playerInventory, inputZoneContents, outputZoneContents, woodenGearShaperStateData);
    }

    // must assign a slot index to each of the slots used by the GUI.
    // Each time we add a Slot to the container using addSlotToContainer(), it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  37 = input slot (woodenGearShaperStateData 0)
    //  38 = output slot (woodenGearShaperStateData 1)

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;

    public static final int INPUT_SLOTS_COUNT = TileEntityWoodenGearShaper.INPUT_SLOTS_COUNT;
    public static final int OUTPUT_SLOTS_COUNT = TileEntityWoodenGearShaper.OUTPUT_SLOTS_COUNT;
    public static final int WOODEN_GEAR_SHAPER_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    // slot index is the unique index for all slots in this container
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int HOTBAR_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT;
    private static final int FIRST_INPUT_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;

    // slot number is the slot number within each component;
    // i.e. invPlayer slots 0 - 35 (hotbar 0 - 8 then main inventory 9 to 35)
    // and furnace: inputZone slots 0, outputZone slots 0

    public ContainerWoodenGearShaper(int windowID, PlayerInventory invPlayer,
                                WoodenGearShaperZoneContents inputZoneContents,
                                WoodenGearShaperZoneContents outputZoneContents,
                                WoodenGearShaperStateData woodenGearShaperStateData) {
        super(DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER, windowID);
        if (DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER == null)
            throw new IllegalStateException("Must initialise containerTypeContainerWoodenGearShaper before constructing a ContainerWoodenGearShaper!");
        this.inputZoneContents = inputZoneContents;
        this.outputZoneContents = outputZoneContents;
        this.woodenGearShaperStateData = woodenGearShaperStateData;
        this.world = invPlayer.player.world;

        trackIntArray(woodenGearShaperStateData);    // tell vanilla to keep the woodenGearShaperStateData synchronised between client and server Containers

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 142;
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            int slotNumber = x;
            addSlot(new Slot(invPlayer, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
        }

        final int PLAYER_INVENTORY_XPOS = 8;
        final int PLAYER_INVENTORY_YPOS = 84;
        // Add the rest of the players inventory to the gui
        for (int y = 0; y < PLAYER_INVENTORY_ROW_COUNT; y++) {
            for (int x = 0; x < PLAYER_INVENTORY_COLUMN_COUNT; x++) {
                int slotNumber = HOTBAR_SLOT_COUNT + y * PLAYER_INVENTORY_COLUMN_COUNT + x;
                int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
                int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
                addSlot(new Slot(invPlayer, slotNumber,  xpos, ypos));
            }
        }

        final int INPUT_SLOT_XPOS = 56;
        final int INPUT_SLOT_YPOS = 17;
        // Add the tile input slots
        for (int y = 0; y < INPUT_SLOTS_COUNT; y++) {
            int slotNumber = y;
            addSlot(new ContainerWoodenGearShaper.SlotProcessableInput(inputZoneContents, slotNumber, INPUT_SLOT_XPOS, INPUT_SLOT_YPOS));
        }

        final int OUTPUT_SLOT_XPOS = 116;
        final int OUTPUT_SLOT_YPOS = 35;
        // Add the tile output slots
        for (int y = 0; y < OUTPUT_SLOTS_COUNT; y++) {
            int slotNumber = y;
            addSlot(new ContainerWoodenGearShaper.SlotOutput(outputZoneContents, slotNumber, OUTPUT_SLOT_XPOS, OUTPUT_SLOT_YPOS));
        }
    }

    // Checks each tick to make sure the player is still able to access the inventory and if not closes the gui
    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return inputZoneContents.isUsableByPlayer(player)  && outputZoneContents.isUsableByPlayer(player);
    }

    // This is where you specify what happens when a player shift clicks a slot in the gui
    //  (when you shift click a slot in the TileEntity Inventory, it moves it to the first available position in the hotbar and/or
    //    player inventory.  When you you shift-click a hotbar or player inventory item, it moves it to the first available
    //    position in the TileEntity inventory
    // At the very least you must override this and return ItemStack.EMPTY or the game will crash when the player shift clicks a slot.
    // returns ItemStack.EMPTY if the source slot is empty, or if none of the source slot item could be moved.
    //   otherwise, returns a copy of the source stack
    //  Code copied & refactored from vanilla furnace AbstractFurnaceContainer
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int sourceSlotIndex)
    {
        Slot sourceSlot = inventorySlots.get(sourceSlotIndex);
        if (sourceSlot == null || !sourceSlot.getHasStack()) return ItemStack.EMPTY;
        ItemStack sourceItemStack = sourceSlot.getStack();
        ItemStack sourceStackBeforeMerge = sourceItemStack.copy();
        boolean successfulTransfer = false;

        SlotZone sourceZone = SlotZone.getZoneFromIndex(sourceSlotIndex);

        switch (sourceZone) {
            case OUTPUT_ZONE: // taking out of the output zone - try the hotbar first, then main inventory.  fill from the end.
                successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.PLAYER_HOTBAR, sourceItemStack, true);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, true);
                }
                if (successfulTransfer) {  // removing from output means we have just crafted an item -> need to inform
                    sourceSlot.onSlotChange(sourceItemStack, sourceStackBeforeMerge);
                }
                break;

            case INPUT_ZONE: // taking out of input zone - try player main inv first, then hotbar.  fill from the start
                successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, false);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.PLAYER_HOTBAR, sourceItemStack, false);
                }
                break;

            case PLAYER_HOTBAR:
            case PLAYER_MAIN_INVENTORY: // taking out of inventory - find the appropriate furnace zone
                if (!TileEntityWoodenGearShaper.getProcessingResultForItem(world, sourceItemStack).isEmpty()) { // processable -> add to input
                    successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.INPUT_ZONE, sourceItemStack, false);
                }
                if (!successfulTransfer) {  // didn't fit into furnace; try player main inventory or hotbar
                    if (sourceZone == ContainerWoodenGearShaper.SlotZone.PLAYER_HOTBAR) { // main inventory
                        successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, false);
                    } else {
                        successfulTransfer = mergeInto(ContainerWoodenGearShaper.SlotZone.PLAYER_HOTBAR, sourceItemStack, false);
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("unexpected sourceZone:" + sourceZone);
        }
        if (!successfulTransfer) return ItemStack.EMPTY;

        // If source stack is empty (the entire stack was moved) set slot contents to empty
        if (sourceItemStack.isEmpty()) {
            sourceSlot.putStack(ItemStack.EMPTY);
        } else {
            sourceSlot.onSlotChanged();
        }

        // if source stack is still the same as before the merge, the transfer failed somehow?  not expected.
        if (sourceItemStack.getCount() == sourceStackBeforeMerge.getCount()) {
            return ItemStack.EMPTY;
        }
        sourceSlot.onTake(player, sourceItemStack);
        return sourceStackBeforeMerge;
    }

    /**
     * Try to merge from the given source ItemStack into the given SlotZone.
     * @param destinationZone the zone to merge into
     * @param sourceItemStack the itemstack to merge from
     * @param fillFromEnd if true: try to merge from the end of the zone instead of from the start
     * @return true if a successful transfer occurred
     */
    private boolean mergeInto(ContainerWoodenGearShaper.SlotZone destinationZone, ItemStack sourceItemStack, boolean fillFromEnd) {
        return mergeItemStack(sourceItemStack, destinationZone.firstIndex, destinationZone.lastIndexPlus1, fillFromEnd);
    }

    // -------- methods used by the ContainerScreen to render parts of the display

    /**
     * Returns the amount of inertia remaining on the currently processing item.
     * @return fraction remaining, between 0.0 - 1.0
     */
    public double getInertiaFraction() {
        if (woodenGearShaperStateData.inertiaTimeInitialValue <= 0 ) return 0;
        double fraction = woodenGearShaperStateData.inertiaTimeRemaining / (double)woodenGearShaperStateData.inertiaTimeInitialValue;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    /**
     * Returns the amount of process time completed on the currently processing item.
     * @return fraction remaining, between 0 - 1
     */
    public double fractionOfProcessTimeComplete() {
        if (woodenGearShaperStateData.processTimeForCompletion == 0) return 0;
        double fraction = woodenGearShaperStateData.processTimeElapsed / (double)woodenGearShaperStateData.processTimeForCompletion;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    @Override
    public boolean enchantItem(PlayerEntity playerIn, int id) {
        woodenGearShaperStateData.inertiaTimeRemaining += 10;
        woodenGearShaperStateData.inertiaTimeRemaining = Math.min(woodenGearShaperStateData.inertiaTimeRemaining, woodenGearShaperStateData.inertiaTimeInitialValue);
        woodenGearShaperStateData.inertiaTimeInitialValue = 100;
        return true;
    }

    // --------- Customise the different slots (in particular - what items they will accept)

    // SlotProcessableInput is a slot for input item
    public static class SlotProcessableInput extends Slot {
        public SlotProcessableInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntityWoodenGearShaper.isItemValidForInputSlot(stack);
        }
    }

    // SlotOutput is a slot that will not accept any item
    public static class SlotOutput extends Slot {
        public SlotOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given item into this slot
        @Override
        public boolean isItemValid(ItemStack stack) {
            return TileEntityWoodenGearShaper.isItemValidForOutputSlot(stack);
        }
    }

    private WoodenGearShaperZoneContents inputZoneContents;
    private WoodenGearShaperZoneContents outputZoneContents;
    private WoodenGearShaperStateData woodenGearShaperStateData;

    private World world; //needed for some helper methods

    /**
     * Helper enum to make the code more readable
     */
    private enum SlotZone {
        INPUT_ZONE(FIRST_INPUT_SLOT_INDEX, INPUT_SLOTS_COUNT),
        OUTPUT_ZONE(FIRST_OUTPUT_SLOT_INDEX, OUTPUT_SLOTS_COUNT),
        PLAYER_MAIN_INVENTORY(PLAYER_INVENTORY_FIRST_SLOT_INDEX, PLAYER_INVENTORY_SLOT_COUNT),
        PLAYER_HOTBAR(HOTBAR_FIRST_SLOT_INDEX, HOTBAR_SLOT_COUNT);

        SlotZone(int firstIndex, int numberOfSlots) {
            this.firstIndex = firstIndex;
            this.slotCount = numberOfSlots;
            this.lastIndexPlus1 = firstIndex + numberOfSlots;
        }

        public final int firstIndex;
        public final int slotCount;
        public final int lastIndexPlus1;

        public static ContainerWoodenGearShaper.SlotZone getZoneFromIndex(int slotIndex) {
            for (ContainerWoodenGearShaper.SlotZone slotZone : ContainerWoodenGearShaper.SlotZone.values()) {
                if (slotIndex >= slotZone.firstIndex && slotIndex < slotZone.lastIndexPlus1) return slotZone;
            }
            throw new IndexOutOfBoundsException("Unexpected slotIndex");
        }
    }
}
