package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden_press.ContainerWoodenPress;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldContainerRegister {

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        ContainerType<?> WOODEN_PRESS_CONTAINER = IForgeContainerType.create(ContainerWoodenPress::createContainerClientSide);

        event.getRegistry().register(WOODEN_PRESS_CONTAINER.setRegistryName(Deepworld.MOD_ID, "wooden_press_container"));
    }
}
