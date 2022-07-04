package fr.dwightstudio.deepworld.common.machine.wooden;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.antlr.v4.runtime.misc.Predicate;

public class WoodenMachineZoneContents extends Inventory {

    /**
     * Use this constructor to create a WoodenMachineZoneContents which is linked to its parent TileEntity.
     * On the server, this link will be used by the Container to request information and provide notifications to the parent
     * On the client, the link will be unused.
     * There are additional notificationLambdas available; these two are explicitly specified because your TileEntity will
     *   nearly always need to implement at least these two
     * @param size  the max number of ItemStacks in the inventory
     * @param canPlayerAccessInventoryLambda the function that the container should call in order to decide if the given player
     *                                       can access the container's contents not.  Usually, this is a check to see
     *                                       if the player is closer than 8 blocks away.
     * @param markDirtyNotificationLambda  the function that the container should call in order to tell the parent TileEntity
     *                                     that the contents of its inventory have been changed and need to be saved.  Usually,
     *                                     this is TileEntity::markDirty
     * @return the new ChestContents.
     */
    public static WoodenMachineZoneContents createForTileEntity(int size,
                                                                   Predicate<Player> canPlayerAccessInventoryLambda,
                                                                WoodenMachineZoneContents.Notify markDirtyNotificationLambda) {
        return new WoodenMachineZoneContents(size, canPlayerAccessInventoryLambda, markDirtyNotificationLambda);
    }

    /**
     * Use this constructor to create a WoodenMachineZoneContents which is not linked to any parent TileEntity; i.e.
     *   is used by the client side container:
     * * does not permanently store items
     * * cannot ask questions/provide notifications to a parent TileEntity
     * @param size  the max number of ItemStacks in the inventory
     * @return the new ChestContents
     */
    public static WoodenMachineZoneContents createForClientSideContainer(int size) {
        return new WoodenMachineZoneContents(size);
    }

    // ----Methods used to load / save the contents to NBT

    /**
     * Writes the chest contents to a CompoundNBT tag (used to save the contents to disk)
     * @return the tag containing the contents
     */
    public CompoundTag serializeNBT()  {
        return WoodenMachineZoneContents.serializeNBT();
    }

    /**
     * Fills the chest contents from the nbt; resizes automatically to fit.  (used to load the contents from disk)
     * @param nbt
     */
    public void deserializeNBT(CompoundTag nbt)   {
        WoodenMachineZoneContents.deserializeNBT(nbt);
    }

    //  ------------- linking methods  -------------
    //  The following group of methods are used to establish a link between the parent TileEntity and the chest contents,
    //    so that the container can communicate with the parent TileEntity without having to talk to it directly.
    //  This is important because the link to the TileEntity only exists on the server side.  On the client side, the
    //    container gets a dummy link instead- there is no link to the client TileEntity.  Linking to the client TileEntity
    //    is prohibited because of synchronisation clashes, i.e. vanilla would attempt to synchronise the TileEntity in two
    //    different ways at the same time: via the tileEntity server->client packets and via the container directly poking
    //    around in the inventory contents.
    //  I've used lambdas to make the decoupling more explicit.  You could instead
    //  * provide an Optional TileEntity to the ChestContents constructor (and ignore the markDirty() etc calls), or
    //  * implement IInventory directly in your TileEntity, and construct your client-side container using an Inventory
    //    instead of passing it a TileEntity.  (This is how vanilla does it)
    //

    /**
     * sets the function that the container should call in order to decide if the given player can access the container's
     *   contents not.  The lambda function is only used on the server side
     */
    public void setCanPlayerAccessInventoryLambda(Predicate<Player> canPlayerAccessInventoryLambda) {
        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
    }

    // the function that the container should call in order to tell the parent TileEntity that the
    // contents of its inventory have been changed.
    // default is "do nothing"
    public void setMarkDirtyNotificationLambda(WoodenMachineZoneContents.Notify markDirtyNotificationLambda) {
        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
    }

    // the function that the container should call in order to tell the parent TileEntity that the
    // container has been opened by a player (eg so that the chest can animate its lid being opened)
    // default is "do nothing"
    public void setOpenInventoryNotificationLambda(WoodenMachineZoneContents.Notify openInventoryNotificationLambda) {
        this.openInventoryNotificationLambda = openInventoryNotificationLambda;
    }

    // the function that the container should call in order to tell the parent TileEntity that the
    // container has been closed by a player
    // default is "do nothing"
    public void setCloseInventoryNotificationLambda(WoodenMachineZoneContents.Notify closeInventoryNotificationLambda) {
        this.closeInventoryNotificationLambda = closeInventoryNotificationLambda;
    }

    // ---------- These methods are used by the container to ask whether certain actions are permitted
    //  If you need special behaviour (eg a chest can only be used by a particular player) then either modify this method
    //    or ask the parent TileEntity.

    @Override
    public boolean isUsableByPlayer(Player player) {
        return canPlayerAccessInventoryLambda.test(player);  // on the client, this does nothing. on the server, ask our parent TileEntity.
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return WoodenMachineZoneContents.isItemValid(index, stack);
    }

    // ----- Methods used to inform the parent tile entity that something has happened to the contents
    //  you can make direct calls to the parent if you like, I've used lambdas because I think it shows the separation
    //   of responsibilities more clearly.

    @FunctionalInterface
    public interface Notify {   // Some folks use Runnable, but I prefer not to use it for non-thread-related tasks
        void invoke();
    }

    @Override
    public void markDirty() {
        markDirtyNotificationLambda.invoke();
    }

    @Override
    public void openInventory(Player player) {
        openInventoryNotificationLambda.invoke();
    }

    @Override
    public void closeInventory(Player player) {
        closeInventoryNotificationLambda.invoke();
    }

    //---------These following methods are called by Vanilla container methods to manipulate the inventory contents ---

    @Override
    public int getSizeInventory() {
        return WoodenMachineZoneContents.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < WoodenMachineZoneContents.getSlots(); ++i) {
            if (!WoodenMachineZoneContents.getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    public ItemStack getStackInSlot(int index) {
        return WoodenMachineZoneContents.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (count < 0) throw new IllegalArgumentException("count should be >= 0:" + count);
        return WoodenMachineZoneContents.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        int maxPossibleItemStackSize = WoodenMachineZoneContents.getSlotLimit(index);
        return WoodenMachineZoneContents.extractItem(index, maxPossibleItemStackSize, false);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        WoodenMachineZoneContents.setStackInSlot(index, stack);
    }

    @Override
    public void clear() {
        for (int i = 0; i < WoodenMachineZoneContents.getSlots(); ++i) {
            WoodenMachineZoneContents.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    //--------- useful functions that aren't in IInventory but are useful anyway

    /**
     *  Tries to insert the given ItemStack into the given slot.
     * @param index the slot to insert into
     * @param itemStackToInsert the itemStack to insert.  Is not mutated by the function.
     * @return if successful insertion: ItemStack.EMPTY.  Otherwise, the leftover itemstack
     *         (eg if ItemStack has a size of 23, and only 12 will fit, then ItemStack with a size of 11 is returned
     */
    public ItemStack increaseStackSize(int index, ItemStack itemStackToInsert) {
        ItemStack leftoverItemStack = WoodenMachineZoneContents.insertItem(index, itemStackToInsert, false);
        return leftoverItemStack;
    }

    /**
     *  Checks if the given slot will accept all of the given itemStack
     * @param index the slot to insert into
     * @param itemStackToInsert the itemStack to insert
     * @return if successful insertion: ItemStack.EMPTY.  Otherwise, the leftover itemstack
     *         (eg if ItemStack has a size of 23, and only 12 will fit, then ItemStack with a size of 11 is returned
     */
    public boolean doesItemStackFit(int index, ItemStack itemStackToInsert) {
        ItemStack leftoverItemStack = WoodenMachineZoneContents.insertItem(index, itemStackToInsert, true);
        return leftoverItemStack.isEmpty();
    }

    // ---------

    private WoodenMachineZoneContents(int size) {
        this.WoodenMachineZoneContents = new ItemStackHandler(size);
    }

    private WoodenMachineZoneContents(int size, Predicate<Player> canPlayerAccessInventoryLambda, WoodenMachineZoneContents.Notify markDirtyNotificationLambda) {
        this.WoodenMachineZoneContents = new ItemStackHandler(size);
        this.canPlayerAccessInventoryLambda = canPlayerAccessInventoryLambda;
        this.markDirtyNotificationLambda = markDirtyNotificationLambda;
    }

    // the function that the container should call in order to decide if the
    // given player can access the container's Inventory or not.  Only valid server side
    //  default is "true".
    private Predicate<Player> canPlayerAccessInventoryLambda = x-> true;

    // the function that the container should call in order to tell the parent TileEntity that the
    // contents of its inventory have been changed.
    // default is "do nothing"
    private WoodenMachineZoneContents.Notify markDirtyNotificationLambda = ()->{};

    // the function that the container should call in order to tell the parent TileEntity that the
    // container has been opened by a player (eg so that the chest can animate its lid being opened)
    // default is "do nothing"
    private WoodenMachineZoneContents.Notify openInventoryNotificationLambda = ()->{};

    // the function that the container should call in order to tell the parent TileEntity that the
    // container has been closed by a player
    // default is "do nothing"
    private WoodenMachineZoneContents.Notify closeInventoryNotificationLambda = ()->{};

    private final ItemStackHandler WoodenMachineZoneContents;
}
