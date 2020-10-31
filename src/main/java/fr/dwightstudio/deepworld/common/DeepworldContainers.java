package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachine;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenLathe;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Deepworld.MOD_ID)
public class DeepworldContainers {

    public static final ContainerType<ContainerWoodenMachine<TileEntityWoodenPress>> WOODEN_PRESS_CONTAINER = null;
    public static final ContainerType<ContainerWoodenMachine<TileEntityWoodenGearShaper>> WOODEN_GEAR_SHAPER_CONTAINER = null;
    public static final ContainerType<ContainerWoodenMachine<TileEntityWoodenLathe>> WOODEN_LATHE_CONTAINER = null;
}
