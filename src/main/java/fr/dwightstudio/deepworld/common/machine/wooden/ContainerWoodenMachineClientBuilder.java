package fr.dwightstudio.deepworld.common.machine.wooden;

import fr.dwightstudio.deepworld.common.tile.ITileEntityWoodenMachine;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ContainerWoodenMachineClientBuilder<T extends ITileEntityWoodenMachine> {

    public interface ContainerTypeGetter {
        public ContainerType<?> get();
    }

    private final ContainerTypeGetter getter;
    private final Class<T> tileClass;

    @Nullable
    private T getTileEntity(PlayerEntity player) {
        World world = player.world;
        if(Minecraft.getInstance().objectMouseOver.getType() != RayTraceResult.Type.BLOCK){return null;}

        Vec3d blockVector = Minecraft.getInstance().objectMouseOver.getHitVec();

        double bX = blockVector.getX(); double bY = blockVector.getY(); double bZ = blockVector.getZ();
        double pX = player.getPosX(); double pY = player.getPosY(); double pZ = player.getPosZ();

        if(bX == Math.floor(bX) && bX <= pX){bX--;}
        if(bY == Math.floor(bY) && bY <= pY+1){bY--;} // +1 on Y to get y from player eyes instead of feet
        if(bZ == Math.floor(bZ) && bZ <= pZ){bZ--;}

        TileEntity tileEntity = world.getTileEntity(new BlockPos(bX, bY, bZ));
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

    public ContainerWoodenMachine<T> build(int windowID, PlayerInventory playerInventory, PacketBuffer extraData) {
        PlayerEntity player = playerInventory.player;

        if (player.world.isRemote()) {
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
