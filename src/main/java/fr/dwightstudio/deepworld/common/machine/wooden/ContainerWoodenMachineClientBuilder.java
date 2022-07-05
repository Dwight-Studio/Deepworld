package fr.dwightstudio.deepworld.common.machine.wooden;

import fr.dwightstudio.deepworld.common.tile.ITileEntityWoodenMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ContainerWoodenMachineClientBuilder<T extends ITileEntityWoodenMachine> {

    public interface ContainerTypeGetter {
        public MenuType<?> get();
    }

    private final ContainerTypeGetter getter;
    private final Class<T> tileClass;

    @Nullable
    private T getTileEntity(Player player) {
        Level world = player.level;
        if(Minecraft.getInstance().hitResult.getType() != HitResult.Type.BLOCK){return null;}

        Vec3 blockVector = Minecraft.getInstance().hitResult.getLocation();

        double bX = blockVector.x; double bY = blockVector.y; double bZ = blockVector.z;
        double pX = player.getX(); double pY = player.getY(); double pZ = player.getZ();

        if(bX == Math.floor(bX) && bX <= pX){bX--;}
        if(bY == Math.floor(bY) && bY <= pY+1){bY--;} // +1 on Y to get y from player eyes instead of feet
        if(bZ == Math.floor(bZ) && bZ <= pZ){bZ--;}

        BlockEntity tileEntity = world.getBlockEntity(new BlockPos(bX, bY, bZ));
        if (tileClass.isInstance(tileEntity)) {
            return (T) tileEntity;
        } else {
            return null;
        }
    }

    public ContainerWoodenMachineClientBuilder(Class<T> tileClass, ContainerTypeGetter getter) {
        this.getter = getter;
        this.tileClass = tileClass;
    }

    public ContainerWoodenMachine<T> build(int windowID, Inventory playerInventory, FriendlyByteBuf extraData) {
        Player player = playerInventory.player;

        if (player.level.isClientSide()) {
            return new ContainerWoodenMachine<T>(getter.get(),
                    getTileEntity(player),
                    windowID,
                    playerInventory,
                    WoodenMachineZoneContents.createForClientSideContainer(ContainerWoodenMachine.INPUT_SLOTS_COUNT),
                    WoodenMachineZoneContents.createForClientSideContainer(ContainerWoodenMachine.OUTPUT_SLOTS_COUNT),
                    new WoodenMachineStateData());
        } else {
            throw new IllegalStateException("Must be invoked at client side");
        }
    }
}
