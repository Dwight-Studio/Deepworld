package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.dimension.DimensionDeepworld;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;

public class DeepworldNoiseSettings {

    public static ResourceKey<NoiseGeneratorSettings> DEEPWORLD_NOISE_SETTINGS = ResourceKey.create(Registries.NOISE_SETTINGS, Deepworld.loc("deepworld_noise_settings"));

    public static Holder.Reference<NoiseGeneratorSettings> DEEPWORLD;

    public static void register(BootstapContext<NoiseGeneratorSettings> context) {
        DEEPWORLD = context.register(DEEPWORLD_NOISE_SETTINGS, DimensionDeepworld.getNoiseGeneratorSettings());
    }
}
