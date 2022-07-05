package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachine;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenLathe;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ObjectHolder;

public class DeepworldContainers {

    public static final MenuType<ContainerWoodenMachine<TileEntityWoodenPress>> WOODEN_PRESS_CONTAINER = null;
    public static final MenuType<ContainerWoodenMachine<TileEntityWoodenGearShaper>> WOODEN_GEAR_SHAPER_CONTAINER = null;
    public static final MenuType<ContainerWoodenMachine<TileEntityWoodenLathe>> WOODEN_LATHE_CONTAINER = null;
}
