package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.machine.wooden.ContainerWoodenMachineClientBuilder;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenLathe;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class DeepworldContainerRegister {

    @SubscribeEvent
    public static void registerContainers(final RegisterEvent event) {

        // Wooden Machine
        MenuType<?> WOODEN_PRESS_CONTAINER = IForgeMenuType.create(new ContainerWoodenMachineClientBuilder<>(TileEntityWoodenPress.class, () -> DeepworldContainers.WOODEN_PRESS_CONTAINER)::build);
        MenuType<?> WOODEN_GEAR_SHAPER_CONTAINER = IForgeMenuType.create(new ContainerWoodenMachineClientBuilder<>(TileEntityWoodenGearShaper.class, () -> DeepworldContainers.WOODEN_GEAR_SHAPER_CONTAINER)::build);
        MenuType<?> WOODEN_LATHE_CONTAINER = IForgeMenuType.create(new ContainerWoodenMachineClientBuilder<>(TileEntityWoodenLathe.class, () -> DeepworldContainers.WOODEN_LATHE_CONTAINER)::build);

        event.register(ForgeRegistries.Keys.CONTAINER_TYPES, helper -> {
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_gear_shaper_container"), WOODEN_GEAR_SHAPER_CONTAINER);
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_press_container"), WOODEN_PRESS_CONTAINER);
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_lathe_container"), WOODEN_LATHE_CONTAINER);
        });
    }
}
