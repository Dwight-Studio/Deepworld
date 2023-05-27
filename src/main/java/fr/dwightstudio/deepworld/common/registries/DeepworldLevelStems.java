package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

public class DeepworldLevelStems {

    public static ResourceKey<LevelStem> DEEPWORLD_LEVEL_STEM = ResourceKey.create(Registries.LEVEL_STEM, Deepworld.loc("deepworld"));

    public static Holder.Reference<LevelStem> DEEPWORLD;

    public static void register(BootstapContext<LevelStem> context) {
        /*DEEPWORLD = context.register(DEEPWORLD_LEVEL_STEM,
                new LevelStem(context.lookup(Registries.DIMENSION_TYPE).getOrThrow(DeepworldDimensionTypes.DEEPWORLD.key()),
                new NoiseBasedChunkGenerator()
                ));*/

    }
}
