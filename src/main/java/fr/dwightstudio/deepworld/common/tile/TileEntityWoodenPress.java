package fr.dwightstudio.deepworld.common.tile;

import java.util.Optional;
import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import fr.dwightstudio.deepworld.common.block.BlockWoodenPress;
import fr.dwightstudio.deepworld.common.machine.wooden_press.ContainerWoodenPress;
import fr.dwightstudio.deepworld.common.machine.wooden_press.WoodenPressStateData;
import fr.dwightstudio.deepworld.common.machine.wooden_press.WoodenPressZoneContents;
import fr.dwightstudio.deepworld.common.recipe.wooden_press.WoodenPressRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class TileEntityWoodenPress extends TileEntity implements INamedContainerProvider, ITickableTileEntity {

    public static final int INPUT_SLOTS_COUNT = 1;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    private WoodenPressZoneContents inputZoneContents;
    private WoodenPressZoneContents outputZoneContents;

    private final WoodenPressStateData woodenPressStateData = new WoodenPressStateData();
    private ItemStack currentlyProcessingItemLastTick = ItemStack.EMPTY;

    public TileEntityWoodenPress(){
        super(DeepworldTileEntities.WOODEN_PRESS);
        inputZoneContents = WoodenPressZoneContents.createForTileEntity(INPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::markDirty);
        outputZoneContents = WoodenPressZoneContents.createForTileEntity(OUTPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::markDirty);
    }

    // Return true if the given player is able to use this block. In this case it checks that
    // 1) the world tileentity hasn't been replaced in the meantime, and
    // 2) the player isn't too far away from the centre of the block
    public boolean canPlayerAccessInventory(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    // This method is called every tick to update the tile entity, i.e.
    // - see if the current smelting input item has finished smelting; if so, convert it to output
    // - burn fuel slots
    // It runs both on the server and the client but we only need to do updates on the server side.
    @Override
    public void tick() {
        if (world.isRemote) return; // do nothing on client.
        ItemStack currentlyProcessingItem = inputZoneContents.getStackInSlot(0);

        // if user has changed the input slots, reset the smelting time
        if (!ItemStack.areItemsEqual(currentlyProcessingItem, currentlyProcessingItem)) {  // == and != don't work!
            woodenPressStateData.processTimeElapsed = 0;
        }
        currentlyProcessingItemLastTick = currentlyProcessingItem.copy();

        if (!currentlyProcessingItem.isEmpty()) {
            reduceInertia();

            // If inertia is greater than 0, process block
            if (woodenPressStateData.inertiaTimeRemaining > 0) {

                double fraction = woodenPressStateData.inertiaTimeRemaining / (double) woodenPressStateData.inertiaTimeInitialValue;
                woodenPressStateData.processTimeElapsed += Math.ceil(fraction * 2);
            }

            if (woodenPressStateData.processTimeElapsed < 0) woodenPressStateData.processTimeElapsed = 0;

            int processTimeForCurrentItem = getProcessTime(this.world, currentlyProcessingItem);
            woodenPressStateData.processTimeForCompletion = processTimeForCurrentItem;

            // If cookTime has reached maxCookTime smelt the item and reset cookTime
            if (woodenPressStateData.processTimeElapsed >= processTimeForCurrentItem) {
                processInputItem(true);
                woodenPressStateData.processTimeElapsed = 0;
            }
        } else {
            woodenPressStateData.processTimeElapsed = 0;
        }

        // when the state of the machine change, we need to force the block to re-render, otherwise the change in
        //   state will not be visible.  Likewise, we need to force a lighting recalculation.
        // The block update (for renderer) is only required on client side, but the lighting is required on both, since
        //    the client needs it for rendering and the server needs it for crop growth etc

        BlockState currentBlockState = world.getBlockState(this.pos);
        BlockState newBlockState = currentBlockState.with(BlockWoodenPress.WORKING, woodenPressStateData.inertiaTimeRemaining > 0);

        if (!newBlockState.equals(currentBlockState)) {
            world.setBlockState(this.pos, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/ | Constants.BlockFlags.RERENDER_MAIN_THREAD);

            markDirty();
        }
    }

    /**
     * 	for each fuel slot: decreases the burn time, checks if burnTimeRemainings = 0 and tries to consume a new piece of fuel if one is available
     */
    private void reduceInertia() {
        if (woodenPressStateData.inertiaTimeRemaining > 0) {
            --woodenPressStateData.inertiaTimeRemaining;
        }
    }

    /**
     * checks that there is an item to be smelted in one of the input slots and that there is room for the result in the output slots
     * If desired, performs the smelt
     * @param performProcess if true, perform the process.  if false, check whether processing is possible, but don't change the inventory
     * @return a copy of the ItemStack of the input item processed or to-be-processed
     */
    private ItemStack processInputItem(boolean performProcess) {
        ItemStack result;

        // finds the first input slot which is processable and whose result fits into an output slot (stacking if possible)
        ItemStack itemStackToProcess = inputZoneContents.getStackInSlot(0);

        if (!itemStackToProcess.isEmpty()) {
            result = getProcessingResultForItem(this.world, itemStackToProcess);

            if (!result.isEmpty()) {
                // is output slot suitable for process - either empty, or with identical item that has enough space
                if (willItemStackFit(outputZoneContents, 0, result)) {
                    ItemStack rtn = inputZoneContents.getStackInSlot(0).copy();

                    if (!performProcess) return rtn;

                    inputZoneContents.decrStackSize(0, 1);
                    outputZoneContents.increaseStackSize(0, result);

                    markDirty();
                    return rtn;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * Will the given ItemStack fully fit into the target slot?
     * @param woodenPressZoneContents
     * @param slotIndex
     * @param itemStackOrigin
     * @return true if the given ItemStack will fit completely; false otherwise
     */
    public boolean willItemStackFit(WoodenPressZoneContents woodenPressZoneContents, int slotIndex, ItemStack itemStackOrigin) {
        ItemStack itemStackDestination = woodenPressZoneContents.getStackInSlot(0);

        if (itemStackDestination.isEmpty() || itemStackOrigin.isEmpty()) {
            return true;
        }

        if (!itemStackOrigin.isItemEqual(itemStackDestination)) {
            return false;
        }

        int sizeAfterMerge = itemStackDestination.getCount() + itemStackOrigin.getCount();
        if (sizeAfterMerge <= woodenPressZoneContents.getInventoryStackLimit() && sizeAfterMerge <= itemStackDestination.getMaxStackSize()) {
            return true;
        }
        return false;
    }

    // returns the smelting result for the given stack. Returns ItemStack.EMPTY if the given stack can not be smelted
    public static ItemStack getProcessingResultForItem(World world, ItemStack itemStack) {
        Optional<WoodenPressRecipe> matchingRecipe = getMatchingRecipeForInput(world, itemStack);
        if (!matchingRecipe.isPresent()) return ItemStack.EMPTY;
        return matchingRecipe.get().getRecipeOutput().copy();  // beware! You must deep copy otherwise you will alter the recipe itself
    }

    // gets the recipe which matches the given input, or Missing if none.
    public static Optional<WoodenPressRecipe> getMatchingRecipeForInput(World world, ItemStack itemStack) {
        RecipeManager recipeManager = world.getRecipeManager();
        Inventory singleItemInventory = new Inventory(itemStack);
        Optional<WoodenPressRecipe> matchingRecipe = recipeManager.getRecipe(WoodenPressRecipe.PRESSING, singleItemInventory, world);
        return matchingRecipe;
    }

    /**
     * Gets the processing time for this recipe input
     * @param world
     * @param itemStack the input item to be smelted
     * @return processing time (ticks) or 0 if no matching recipe
     */
    public static int getProcessTime(World world, ItemStack itemStack) {
        Optional<WoodenPressRecipe> matchingRecipe = getMatchingRecipeForInput(world, itemStack);
        if (!matchingRecipe.isPresent()) return 0;
        return matchingRecipe.get().getProcessingTime();
    }

    // Return true if the given stack is allowed to be inserted in the given slot
    // Unlike the vanilla furnace, we allow anything to be placed in the fuel slots
    static public boolean isItemValidForFuelSlot(ItemStack itemStack)
    {
        return true;
    }

    // Return true if the given stack is allowed to be inserted in the given slot
    // Unlike the vanilla furnace, we allow anything to be placed in the input slots
    static public boolean isItemValidForInputSlot(ItemStack itemStack)
    {
        return true;
    }

    // Return true if the given stack is allowed to be inserted in the given slot
    static public boolean isItemValidForOutputSlot(ItemStack itemStack)
    {
        return false;
    }

    //------------------------------
    private final String INPUT_SLOTS_NBT = "inputSlots";
    private final String OUTPUT_SLOTS_NBT = "outputSlots";

    // This is where you save any data that you don't want to lose when the tile entity unloads
    // In this case, it saves the state of the furnace (burn time etc) and the itemstacks stored in the fuel, input, and output slots
    @Override
    public CompoundNBT write(CompoundNBT parentNBTTagCompound)
    {
        super.write(parentNBTTagCompound); // The super call is required to save and load the tile's location

        woodenPressStateData.putIntoNBT(parentNBTTagCompound);
        parentNBTTagCompound.put(INPUT_SLOTS_NBT, inputZoneContents.serializeNBT());
        parentNBTTagCompound.put(OUTPUT_SLOTS_NBT, outputZoneContents.serializeNBT());
        return parentNBTTagCompound;
    }

    // This is where you load the data that you saved in writeToNBT
    @Override
    public void read(CompoundNBT nbtTagCompound)
    {
        super.read(nbtTagCompound); // The super call is required to save and load the tile's location

        woodenPressStateData.readFromNBT(nbtTagCompound);

        CompoundNBT inventoryNBT = nbtTagCompound.getCompound(INPUT_SLOTS_NBT);
        inputZoneContents.deserializeNBT(inventoryNBT);

        inventoryNBT = nbtTagCompound.getCompound(OUTPUT_SLOTS_NBT);
        outputZoneContents.deserializeNBT(inventoryNBT);

        if (inputZoneContents.getSizeInventory() != INPUT_SLOTS_COUNT || outputZoneContents.getSizeInventory() != OUTPUT_SLOTS_COUNT
        )
            throw new IllegalArgumentException("Corrupted NBT: Number of inventory slots did not match expected.");
    }

    //	When the world loads from disk, the server needs to send the TileEntity information to the client
    //  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this
    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT updateTagDescribingTileEntityState = getUpdateTag();
        final int METADATA = 42; // arbitrary.
        return new SUpdateTileEntityPacket(this.pos, METADATA, updateTagDescribingTileEntityState);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT updateTagDescribingTileEntityState = pkt.getNbtCompound();
        handleUpdateTag(updateTagDescribingTileEntityState);
    }

    /* Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client
       Warning - although our getUpdatePacket() uses this method, vanilla also calls it directly, so don't remove it.
     */
    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
     *  The vanilla default is suitable for this example but I've included an explicit definition anyway.
     */
    @Override
    public void handleUpdateTag(CompoundNBT tag) { read(tag); }

    /**
     * When this tile entity is destroyed, drop all of its contents into the world
     * @param world
     * @param blockPos
     */
    public void dropAllContents(World world, BlockPos blockPos) {
        InventoryHelper.dropInventoryItems(world, blockPos, inputZoneContents);
        InventoryHelper.dropInventoryItems(world, blockPos, outputZoneContents);
    }

    // -------------  The following two methods are used to make the TileEntity perform as a NamedContainerProvider, i.e.
    //  1) Provide a name used when displaying the container, and
    //  2) Creating an instance of container on the server, and linking it to the inventory items stored within the TileEntity

    /**
     *  standard code to look up what the human-readable name is.
     *  Can be useful when the tileentity has a customised name (eg "David's footlocker")
     */
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.deepworld.wooden_press");
    }

    /**
     * The name is misleading; createMenu has nothing to do with creating a Screen, it is used to create the Container on the server only
     * @param windowID
     * @param playerInventory
     * @param playerEntity
     * @return
     */
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return ContainerWoodenPress.createContainerServerSide(windowID, playerInventory, inputZoneContents, outputZoneContents, woodenPressStateData);
    }
}
