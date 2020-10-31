package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachineClientBuilder;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenLathe;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldContainerRegister {

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        ContainerType<?> WOODEN_PRESS_CONTAINER = IForgeContainerType.create(new ContainerWoodenMachineClientBuilder<>(TileEntityWoodenPress.class, () -> DeepworldContainers.WOODEN_PRESS_CONTAINER)::build);
        ContainerType<?> WOODEN_GEAR_SHAPER_CONTAINER = IForgeContainerType.create(new ContainerWoodenMachineClientBuilder<>(TileEntityWoodenGearShaper.class, () -> DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER)::build);
        ContainerType<?> WOODEN_LATHE_CONTAINER = IForgeContainerType.create(new ContainerWoodenMachineClientBuilder<>(TileEntityWoodenLathe.class, () -> DeepworldContainers.WOODEN_LATHE_CONTAINER)::build);

        event.getRegistry().register(WOODEN_PRESS_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_press_container"));
        event.getRegistry().register(WOODEN_GEAR_SHAPER_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_gear_shaper_container"));
        event.getRegistry().register(WOODEN_LATHE_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_lathe_container"));
    }
}
