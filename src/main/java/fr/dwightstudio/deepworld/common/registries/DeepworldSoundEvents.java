package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldSoundEvents {

    public static RegistryObject<SoundEvent> WOODEN_MACHINE;

    public DeepworldSoundEvents() {
        WOODEN_MACHINE = register("block.wooden_machine.working");
    }

    private RegistryObject<SoundEvent> register(String name) {
        return Deepworld.SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Deepworld.MOD_ID, name)));
    }

}
