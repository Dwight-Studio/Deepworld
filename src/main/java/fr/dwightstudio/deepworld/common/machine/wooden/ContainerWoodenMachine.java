package fr.dwightstudio.deepworld.common.machine.wooden;

import fr.dwightstudio.deepworld.common.tile.ITileEntityWoodenMachine;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ContainerWoodenMachine<T extends ITileEntityWoodenMachine> extends AbstractContainerMenu {

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

    public static final int INPUT_SLOTS_COUNT = 1;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int WOODEN_GEAR_SHAPER_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    // slot index is the unique index for all slots in this container
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int HOTBAR_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX;
    private static final int PLAYER_INVENTORY_FIRST_SLOT_INDEX = HOTBAR_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT;
    private static final int FIRST_INPUT_SLOT_INDEX = PLAYER_INVENTORY_FIRST_SLOT_INDEX + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int FIRST_OUTPUT_SLOT_INDEX = FIRST_INPUT_SLOT_INDEX + INPUT_SLOTS_COUNT;

    // slot number is the slot number within each component;
    // ex. invPlayer slots 0 - 35 (hotbar 0 - 8 then main inventory 9 to 35)
    // and furnace: inputZone slots 0, outputZone slots 0

    private T machine;

    public ContainerWoodenMachine(MenuType<?> containerType,
                                  T machine,
                                  int windowID, Inventory invPlayer,
                                  WoodenMachineZoneContents inputZoneContents,
                                  WoodenMachineZoneContents outputZoneContents,
                                  WoodenMachineStateData stateData) {
        super(containerType, windowID);
        this.machine = machine;
        if (containerType == null)
            throw new IllegalStateException("Must initialise containerTypeContainerWoodenGearShaper before constructing a ContainerWoodenGearShaper!");
        this.inputZoneContents = inputZoneContents;
        this.outputZoneContents = outputZoneContents;
        this.stateData = stateData;
        this.level = invPlayer.player.level;

        //trackIntArray(stateData);    // tell vanilla to keep the woodenGearShaperStateData synchronised between client and server Containers

        final int SLOT_X_SPACING = 18;
        final int SLOT_Y_SPACING = 18;
        final int HOTBAR_XPOS = 8;
        final int HOTBAR_YPOS = 142;
        // Add the players hotbar to the gui - the [xpos, ypos] location of each item
        for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
            addSlot(new Slot(invPlayer, x, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
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
            addSlot(new SlotProcessableInput(inputZoneContents, y, INPUT_SLOT_XPOS, INPUT_SLOT_YPOS));
        }

        final int OUTPUT_SLOT_XPOS = 116;
        final int OUTPUT_SLOT_YPOS = 35;
        // Add the tile output slots
        for (int y = 0; y < OUTPUT_SLOTS_COUNT; y++) {
            addSlot(new SlotOutput(outputZoneContents, y, OUTPUT_SLOT_XPOS, OUTPUT_SLOT_YPOS));
        }
    }


    // Checks each tick to make sure the player is still able to access the inventory and if not closes the gui
    @Override
    public boolean stillValid(Player player) {
        return inputZoneContents.isUsableByPlayer(player)  && outputZoneContents.isUsableByPlayer(player);
    }

    // This is where you specify what happens when a player shift clicks a slot in the gui
    // Code copied & refactored from vanilla furnace AbstractFurnaceContainer



    @Override
    public ItemStack quickMoveStack(Player player, int sourceSlotIndex) {
        Slot sourceSlot = this.slots.get(sourceSlotIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceItemStack = sourceSlot.getItem();
        ItemStack sourceStackBeforeMerge = sourceItemStack.copy();
        boolean successfulTransfer = false;

        ContainerWoodenMachine.SlotZone sourceZone = ContainerWoodenMachine.SlotZone.getZoneFromIndex(sourceSlotIndex);

        switch (sourceZone) {
            case OUTPUT_ZONE: // taking out of the output zone - try the hotbar first, then main inventory.  fill from the end.
                successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.PLAYER_HOTBAR, sourceItemStack, true);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, true);
                }
                if (successfulTransfer) {  // removing from output means we have just crafted an item -> need to inform
                    sourceSlot.onQuickCraft(sourceItemStack, sourceStackBeforeMerge);
                }
                break;

            case INPUT_ZONE: // taking out of input zone - try player main inv first, then hotbar.  fill from the start
                successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, false);
                if (!successfulTransfer) {
                    successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.PLAYER_HOTBAR, sourceItemStack, false);
                }
                break;

            case PLAYER_HOTBAR:
            case PLAYER_MAIN_INVENTORY: // taking out of inventory - find the appropriate furnace zone
                if (machine.isThereRecipeForInput(sourceItemStack)) { // processable -> add to input
                    successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.INPUT_ZONE, sourceItemStack, false);
                }
                if (!successfulTransfer) {  // didn't fit into furnace; try player main inventory or hotbar
                    if (sourceZone == ContainerWoodenMachine.SlotZone.PLAYER_HOTBAR) { // main inventory
                        successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.PLAYER_MAIN_INVENTORY, sourceItemStack, false);
                    } else {
                        successfulTransfer = mergeInto(ContainerWoodenMachine.SlotZone.PLAYER_HOTBAR, sourceItemStack, false);
                    }
                }
                break;

            default:
                throw new IllegalArgumentException("unexpected sourceZone:" + sourceZone);
        }
        if (!successfulTransfer) return ItemStack.EMPTY;

        // If source stack is empty (the entire stack was moved) set slot contents to empty
        if (sourceItemStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
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
    private boolean mergeInto(ContainerWoodenMachine.SlotZone destinationZone, ItemStack sourceItemStack, boolean fillFromEnd) {
        return mergeInto(destinationZone, sourceItemStack, fillFromEnd);
    }

    // -------- methods used by the ContainerScreen to render parts of the display

    /**
     * Returns the amount of inertia remaining on the currently processing item.
     * @return fraction remaining, between 0.0 - 1.0
     */
    public double getInertiaFraction() {
        if (stateData.inertiaTimeInitialValue <= 0 ) return 0;
        double fraction = stateData.inertiaTimeRemaining / (double) stateData.inertiaTimeInitialValue;
        return Mth.clamp(fraction, 0.0, 1.0);
    }

    /**
     * Returns the amount of process time completed on the currently processing item.
     * @return fraction remaining, between 0 - 1
     */
    public double fractionOfProcessTimeComplete() {
        if (stateData.processTimeForCompletion == 0) return 0;
        double fraction = stateData.processTimeElapsed / (double) stateData.processTimeForCompletion;
        return Mth.clamp(fraction, 0.0, 1.0);
    }

    @Override
    public boolean clickMenuButton(Player playerIn, int id) {
        stateData.inertiaTimeRemaining += 10;
        stateData.inertiaTimeRemaining = Math.min(stateData.inertiaTimeRemaining, stateData.inertiaTimeInitialValue);
        stateData.inertiaTimeInitialValue = 100;
        return true;
    }

    // --------- Customise the different slots (in particular - what items they will accept)

    // SlotProcessableInput is a slot for input item
    public class SlotProcessableInput extends Slot {
        public SlotProcessableInput(Inventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given item into this slot

        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return machine.isItemValidForInputSlot(stack);
        }
    }

    // SlotOutput is a slot that will not accept any item
    public class SlotOutput extends Slot {
        public SlotOutput(Inventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        // if this function returns false, the player won't be able to insert the given item into this slot
        @Override
        public boolean mayPlace(@NotNull ItemStack stack) {
            return machine.isItemValidForOutputSlot(stack);
        }
    }

    private WoodenMachineZoneContents inputZoneContents;
    private WoodenMachineZoneContents outputZoneContents;
    private WoodenMachineStateData stateData;

    private Level level; //needed for some helper methods

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

        public static ContainerWoodenMachine.SlotZone getZoneFromIndex(int slotIndex) {
            for (ContainerWoodenMachine.SlotZone slotZone : ContainerWoodenMachine.SlotZone.values()) {
                if (slotIndex >= slotZone.firstIndex && slotIndex < slotZone.lastIndexPlus1) return slotZone;
            }
            throw new IndexOutOfBoundsException("Unexpected slotIndex");
        }
    }
}