package fr.dwightstudio.deepworld.common.dimension;

import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;

import java.util.List;
import java.util.OptionalLong;

public class DimensionDeepworld {

    public static DimensionType getDimensionType() {
        return new DimensionType(
                OptionalLong.empty(),
                true,
                true,
                false,
                false,
                1,
                false,
                true,
                -320,
                256,
                0,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0f,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 7)
        );
    }

    public static NoiseGeneratorSettings getNoiseGeneratorSettings() {
        NoiseSettings noiseSettings = NoiseSettings.create(
                16,
                256,
                1,
                2
        );

        return new NoiseGeneratorSettings(
                noiseSettings,
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                new NoiseRouter(
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero(),
                        DensityFunctions.zero()
                ),
                SurfaceRules.bandlands(),
                List.of(),
                0,
                false,
                false,
                false,
                false
        );
    }
}
