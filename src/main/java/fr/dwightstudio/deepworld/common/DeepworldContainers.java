package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachine;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenLathe;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.ObjectHolder;

public class DeepworldContainers {

    @ObjectHolder(registryName = "minecraft:container_types", value = "deepworld:wooden_press_container")
    public static final MenuType<ContainerWoodenMachine<TileEntityWoodenPress>> WOODEN_PRESS_CONTAINER = null;
    @ObjectHolder(registryName = "minecraft:container_types", value = "deepworld:wooden_gear_shaper_container")
    public static final MenuType<ContainerWoodenMachine<TileEntityWoodenGearShaper>> WOODEN_GEAR_SHAPER_CONTAINER = null;
    @ObjectHolder(registryName = "minecraft:container_types", value = "deepworld:wooden_lathe_container")
    public static final MenuType<ContainerWoodenMachine<TileEntityWoodenLathe>> WOODEN_LATHE_CONTAINER = null;
}
