package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden_gear_shaper.ContainerWoodenGearShaper;
import fr.dwightstudio.deepworld.common.machine.wooden_lathe.ContainerWoodenLathe;
import fr.dwightstudio.deepworld.common.machine.wooden_press.ContainerWoodenPress;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Deepworld.MOD_ID)
public class DeepworldContainers {

    public static final ContainerType<ContainerWoodenPress> WOODEN_PRESS_CONTAINER = null;
    public static final ContainerType<ContainerWoodenGearShaper> WOODEN_GEAR_SHAPER_CONTAINER = null;
    public static final ContainerType<ContainerWoodenLathe> WOODEN_LATHE_CONTAINER = null;
}
