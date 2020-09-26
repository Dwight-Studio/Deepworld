package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden_gear_shaper.ContainerWoodenGearShaper;
import fr.dwightstudio.deepworld.common.machine.wooden_lathe.ContainerWoodenLathe;
import fr.dwightstudio.deepworld.common.machine.wooden_press.ContainerWoodenPress;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldContainerRegister {

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        ContainerType<?> WOODEN_PRESS_CONTAINER = IForgeContainerType.create(ContainerWoodenPress::createContainerClientSide);
        ContainerType<?> WOODEN_GEAR_SHAPER_CONTAINER = IForgeContainerType.create(ContainerWoodenGearShaper::createContainerClientSide);
        ContainerType<?> WOODEN_LATHE_CONTAINER = IForgeContainerType.create(ContainerWoodenLathe::createContainerClientSide);

        event.getRegistry().register(WOODEN_PRESS_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_press_container"));
        event.getRegistry().register(WOODEN_GEAR_SHAPER_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_gear_shaper_container"));
        event.getRegistry().register(WOODEN_LATHE_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_lathe"));
    }
}
