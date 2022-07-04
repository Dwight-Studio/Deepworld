package fr.dwightstudio.deepworld.common.tile;

import fr.dwightstudio.deepworld.client.sound.TickableSoundWoodenMachine;
import fr.dwightstudio.deepworld.common.DeepworldContainers;
import fr.dwightstudio.deepworld.common.DeepworldTileEntities;
import fr.dwightstudio.deepworld.common.block.BlockWoodenGearShaper;
import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachine;
import fr.dwightstudio.deepworld.common.machine.wooden.WoodenMachineStateData;
import fr.dwightstudio.deepworld.common.machine.wooden.WoodenMachineZoneContents;
import fr.dwightstudio.deepworld.common.recipe.wooden_gear_shaper.WoodenGearShaperRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

public class TileEntityWoodenGearShaper extends BlockEntity implements SidedInvWrapper, RecipeHolder, NamedContainerProvider, BlockEntityTicker, ITileEntityWoodenMachine {

    public static final int INPUT_SLOTS_COUNT = 1;
    public static final int OUTPUT_SLOTS_COUNT = 1;
    public static final int TOTAL_SLOTS_COUNT = INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

    private final WoodenMachineZoneContents inputZoneContents;
    private final WoodenMachineZoneContents outputZoneContents;

    private final WoodenMachineStateData woodenMachineStateData = new WoodenMachineStateData();
    private ItemStack currentlyProcessingItemLastTick = ItemStack.EMPTY;

    public TileEntityWoodenGearShaper(){
        super(DeepworldTileEntities.WOODEN_GEAR_SHAPER);
        inputZoneContents = WoodenMachineZoneContents.createForTileEntity(INPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::markDirty);
        outputZoneContents = WoodenMachineZoneContents.createForTileEntity(OUTPUT_SLOTS_COUNT, this::canPlayerAccessInventory, this::markDirty);
    }

    @Override
    public void onLoad() {
        assert this.level != null;
        if (this.level.isClientSide) {
            Minecraft.getInstance().getSoundManager().play(new TickableSoundWoodenMachine(this.worldPosition));
        }
    }

    // Return true if the given player is able to use this block. In this case it checks that
    // 1) the world tileentity hasn't been replaced in the meantime, and
    // 2) the player isn't too far away from the centre of the block
    public boolean canPlayerAccessInventory(Player player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE = 8.0 * 8.0;
        return player.getPosition(0).x() + X_CENTRE_OFFSET < MAXIMUM_DISTANCE &&
                player.getPosition(0).y() + Y_CENTRE_OFFSET < MAXIMUM_DISTANCE &&
                player.getPosition(0).z() + Z_CENTRE_OFFSET < MAXIMUM_DISTANCE;
    }

    // This method is called every tick to update the tile entity, i.e.
    // - see if the current smelting input item has finished smelting; if so, convert it to output
    // It runs both on the server and the client but we only need to do updates on the server side.
    @Override
    public void tick() {
        assert this.level != null;
        if (this.level.isClientSide()) return; // do nothing on client.
        ItemStack currentlyProcessingItem = inputZoneContents.getStackInSlot(0);

        // if user has changed the input slots, reset the smelting time
        if (!ItemStack.isSameItemSameTags(currentlyProcessingItem, currentlyProcessingItemLastTick)) {  // == and != don't work!
            woodenMachineStateData.processTimeElapsed = 0;
        }

        currentlyProcessingItemLastTick = currentlyProcessingItem.copy();

        if (woodenMachineStateData.inertiaTimeRemaining > 0) {
            --woodenMachineStateData.inertiaTimeRemaining;
        }

        WoodenGearShaperRecipe recipe = getMatchingRecipeForInput(this.level, currentlyProcessingItem);

        if (recipe != null) {
            if (recipe.isValidInput(inputZoneContents, this.level)) {

                // If inertia is greater than 0, process block
                if (woodenMachineStateData.inertiaTimeRemaining > 0) {
                    woodenMachineStateData.processTimeElapsed += (int) (((float) woodenMachineStateData.inertiaTimeRemaining / (float) woodenMachineStateData.inertiaTimeInitialValue) * (float) 10);
                }

                if (woodenMachineStateData.processTimeElapsed < 0) woodenMachineStateData.processTimeElapsed = 0;

                int processTimeForCurrentItem = getProcessTime(this.level, currentlyProcessingItem);
                woodenMachineStateData.processTimeForCompletion = processTimeForCurrentItem;

                // If processTime has reached maxProcessTime process the item and reset processTime
                if (woodenMachineStateData.processTimeElapsed >= processTimeForCurrentItem) {
                    processInputItem();
                    woodenMachineStateData.processTimeElapsed = 0;
                }
            } else {
                woodenMachineStateData.processTimeElapsed = 0;
            }
        }

        // when the state of the machine change, we need to force the block to re-render, otherwise the change in
        //   state will not be visible.  Likewise, we need to force a lighting recalculation.
        // The block update (for renderer) is only required on client side, but the lighting is required on both, since
        //    the client needs it for rendering and the server needs it for crop growth etc

        BlockState currentBlockState = this.level.getBlockState(this.worldPosition);
        BlockState newBlockState = currentBlockState.setValue(BlockWoodenGearShaper.WORKING, woodenMachineStateData.inertiaTimeRemaining > 0);

        if (!newBlockState.equals(currentBlockState)) {
            this.level.setBlockAndUpdate(this.worldPosition, newBlockState);

            markDirty();
        } else if (woodenMachineStateData.inertiaTimeRemaining > 0) {
            this.level.markAndNotifyBlock(this.worldPosition, null, currentBlockState, newBlockState, Constants.BlockFlags.BLOCK_UPDATE | 2 /*SEND_TO_CLIENT*/);
        }
    }

    /**
     * checks that there is an item to be smelted in one of the input slots and that there is room for the result in the output slots
     * If desired, performs the smelt
     * @return a copy of the ItemStack of the input item processed or to-be-processed
     */
    private ItemStack processInputItem() {
        WoodenGearShaperRecipe recipe = getMatchingRecipeForInput(this.world, inputZoneContents.getStackInSlot(0));

        if (recipe.isValidInput(inputZoneContents, this.world)) {
            ItemStack result = recipe.getResult();

                    // is output slot suitable for process - either empty, or with identical item that has enough space
                    if (willItemStackFit(outputZoneContents, 0, result)) {
                        ItemStack rtn = inputZoneContents.getStackInSlot(0).copy();

                       recipe.applyCraft(inputZoneContents, this.world);
                        outputZoneContents.increaseStackSize(0, result);

                        markDirty();
                        return rtn;
                    }
        }
        return ItemStack.EMPTY;
    }

    /**
     * Will the given ItemStack fully fit into the target slot?
     * @param woodenGearShaperZoneContents
     * @param slotIndex
     * @param itemStackOrigin
     * @return true if the given ItemStack will fit completely; false otherwise
     */
    public boolean willItemStackFit(WoodenMachineZoneContents woodenGearShaperZoneContents, int slotIndex, ItemStack itemStackOrigin) {
        ItemStack itemStackDestination = woodenGearShaperZoneContents.getStackInSlot(0);

        if (itemStackDestination.isEmpty() || itemStackOrigin.isEmpty()) {
            return true;
        }

        if (!itemStackOrigin.isItemEqual(itemStackDestination)) {
            return false;
        }

        int sizeAfterMerge = itemStackDestination.getCount() + itemStackOrigin.getCount();
        if (sizeAfterMerge <= woodenGearShaperZoneContents.getInventoryStackLimit() && sizeAfterMerge <= itemStackDestination.getMaxStackSize()) {
            return true;
        }
        return false;
    }

    // gets the recipe which matches the given input, or Missing if none.
    public static WoodenGearShaperRecipe getMatchingRecipeForInput(Level world, ItemStack itemStack) {
        return world.getRecipeManager().getRecipe(WoodenGearShaperRecipe.SHAPING, new Inventory(itemStack), world).orElse(null);
    }

    public boolean isThereRecipeForInput(ItemStack sourceItemStack) {
        return getMatchingRecipeForInput(world, sourceItemStack) != null;
    }

    /**
     * Gets the processing time for this recipe input
     * @param world
     * @param itemStack the input item to be smelted
     * @return processing time (ticks) or 0 if no matching recipe
     */
    public static int getProcessTime(Level world, ItemStack itemStack) {
        WoodenGearShaperRecipe matchingRecipe = getMatchingRecipeForInput(world, itemStack);
        if (matchingRecipe == null) return 0;
        return matchingRecipe.getProcessingTime();
    }

    // Return true if the given stack is allowed to be inserted in the given slot
    // Unlike the vanilla furnace, we allow anything to be placed in the input slots
    public boolean isItemValidForInputSlot(ItemStack itemStack) {
        return true;
    }

    // Return true if the given stack is allowed to be inserted in the given slot
    public boolean isItemValidForOutputSlot(ItemStack itemStack)
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

        woodenMachineStateData.putIntoNBT(parentNBTTagCompound);
        parentNBTTagCompound.put(INPUT_SLOTS_NBT, inputZoneContents.serializeNBT());
        parentNBTTagCompound.put(OUTPUT_SLOTS_NBT, outputZoneContents.serializeNBT());
        return parentNBTTagCompound;
    }

    // This is where you load the data that you saved in writeToNBT
    @Override
    public void read(CompoundNBT nbtTagCompound)
    {
        super.read(nbtTagCompound); // The super call is required to save and load the tile's location

        woodenMachineStateData.readFromNBT(nbtTagCompound);

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
        return new TranslationTextComponent("container.deepworld.wooden_gear_shaper");
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
        return new ContainerWoodenMachine<>(DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER,
                this,
                windowID,
                playerInventory,
                inputZoneContents,
                outputZoneContents,
                woodenMachineStateData);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return new int[] {1};
        } else {
            return new int[] {0};
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return isItemValidForInputSlot(itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return index == 1;
    }

    @Override
    public int getSizeInventory() {
        return inputZoneContents.getSizeInventory() + outputZoneContents.getSizeInventory();
    }

    @Override
    public boolean isEmpty() {
        return inputZoneContents.getStackInSlot(0).isEmpty() && outputZoneContents.getStackInSlot(0).isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        switch (index) {
            case 0:
                return inputZoneContents.getStackInSlot(0);
            case 1:
                return outputZoneContents.getStackInSlot(0);
        };
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        switch (index) {
            case 0:
                return inputZoneContents.decrStackSize(0, count);
            case 1:
                return outputZoneContents.decrStackSize(0, count);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        switch (index) {
            case 0:
                return inputZoneContents.removeStackFromSlot(0);
            case 1:
                return outputZoneContents.removeStackFromSlot(0);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        switch (index) {
            case 0:
                inputZoneContents.setInventorySlotContents(0, stack);
                return;
            case 1:
                outputZoneContents.setInventorySlotContents(0, stack);
                return;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean isUsableByPlayer(Player player) {
        return true;
    }

    @Override
    public void clear() {
        inputZoneContents.clear();
        outputZoneContents.clear();
    }

    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {

    }

    @Override
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    @Override
    public float getVolume() {
        if (woodenMachineStateData.inertiaTimeInitialValue <= 0 ) return 0;
        double fraction = woodenMachineStateData.inertiaTimeRemaining / (double)woodenMachineStateData.inertiaTimeInitialValue;
        return (float)MathHelper.clamp(fraction, 0.0, 1.0);
    }

    @Override
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, BlockEntity p_155256_) {

    }
}
