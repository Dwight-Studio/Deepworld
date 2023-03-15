package fr.dwightstudio.deepworld.blockentities.machines.wood;

import fr.dwightstudio.deepworld.blockentities.DeepworldEntities;
import fr.dwightstudio.deepworld.blockentities.ImplementedInventory;
import fr.dwightstudio.deepworld.screen.WoodenLatheScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WoodenLatheBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    public int lastInertia;
    public int inertia;
    public int processProgress;
    public int processTimeTotal;

    public static final int MAX_INERTIA = 400;
    public static final int INERTIA_PER_CLICK = 20;

    public WoodenLatheBlockEntity(BlockPos pos, BlockState state) {
        super(DeepworldEntities.WOODEN_LATHE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0: return WoodenLatheBlockEntity.this.inertia;
                    case 1: return WoodenLatheBlockEntity.this.processProgress;
                    case 2: return WoodenLatheBlockEntity.this.processTimeTotal;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: WoodenLatheBlockEntity.this.inertia = value; break;
                    case 1: WoodenLatheBlockEntity.this.processProgress = value; break;
                    case 2: WoodenLatheBlockEntity.this.processTimeTotal = value; break;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Wooden Lathe");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new WoodenLatheScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    protected void writeNbt(NbtCompound nbt){
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("Inertia", inertia);
        nbt.putInt("ProcessProgress", processProgress);
        nbt.putInt("ProcessTimeTotal", processTimeTotal);
    }

    @Override
    public void readNbt(NbtCompound nbt){
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        inertia = nbt.getInt("Inertia");
        processProgress = nbt.getInt("ProcessProgress");
        processTimeTotal = nbt.getInt("ProcessTimeTotal");
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, WoodenLatheBlockEntity entity) {
        if(world.isClient()) return;

        if(hasRecipe(entity)){
            entity.inertia = Math.min(WoodenLatheBlockEntity.INERTIA_PER_CLICK + entity.inertia, WoodenLatheBlockEntity.MAX_INERTIA);
            entity.processProgress += entity.inertia;
            markDirty(world, blockPos, state);
            if(entity.processProgress >= 32000){
                craftItem(entity);
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private void resetProgress() {
        this.processProgress = 0;
    }

    private static void craftItem(WoodenLatheBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        if(hasRecipe(entity)){
            entity.removeStack(1, 1);

            entity.setStack(2, new ItemStack(Items.STICK, entity.getStack(2).getCount() + 1));
        }
    }

    private static boolean hasRecipe(WoodenLatheBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        boolean hasOakPlanks = entity.getStack(1).getItem() == Items.OAK_PLANKS;
        return hasOakPlanks && canInsertAmountIntoOutputSlot(inventory, 1) && canInsertItemIntoOutputSlot(inventory, Items.STICK);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory, int count) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount() + count;
    }

    public ItemStack getRenderStack() {
        if(this.getStack(2).isEmpty()) {
            return this.getStack(1);
        } else {
            return this.getStack(2);
        }
    }
}
