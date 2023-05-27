package fr.dwightstudio.deepworld.common.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class DeepworldBiomeSource extends BiomeSource {

    public static final Codec<DeepworldBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            RegistryOps.retrieveElement(Biomes.LUSH_CAVES)
    ).apply(instance, instance.stable(DeepworldBiomeSource::new)));

    private final Holder<Biome> biome1;

    public DeepworldBiomeSource(Holder.Reference<Biome> biome1) {
        this.biome1 = biome1;
    }

    public static BiomeSource create(HolderGetter<Biome> biomes) {
        return new DeepworldBiomeSource(biomes.getOrThrow(Biomes.LUSH_CAVES));
    }

    @Override
    protected @NotNull Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(this.biome1);
    }

    @Override
    public @NotNull Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.@NotNull Sampler sampler) {
        return this.biome1;
    }
}
