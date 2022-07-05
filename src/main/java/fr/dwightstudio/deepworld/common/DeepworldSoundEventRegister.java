package fr.dwightstudio.deepworld.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class DeepworldSoundEventRegister {

    @SubscribeEvent
    public static void onSoundEventRegistration(final RegisterEvent event) {

        event.register(ForgeRegistries.Keys.SOUND_EVENTS, helper -> {
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_machine"), new SoundEvent(new ResourceLocation(Deepworld.MOD_ID, "wooden_machine")));
        });
    }
}
