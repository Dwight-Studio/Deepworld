package fr.dwightstudio.deepworld.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldSoundEventRegister {

    @SubscribeEvent
    public static void onSoundEventRegistration(final RegistryEvent.Register<SoundEvent> event) {

        event.getRegistry().register(new SoundEvent(new ResourceLocation(Deepworld.MOD_ID, "wooden_machine")).setRegistryName(new ResourceLocation(Deepworld.MOD_ID, "wooden_machine")));
    }
}
