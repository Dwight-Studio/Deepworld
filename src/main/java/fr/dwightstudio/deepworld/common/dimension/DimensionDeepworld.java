package fr.dwightstudio.deepworld.common.dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.OptionalLong;

public class DimensionDeepworld implements IDeepworldDimensionType {

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
                BuiltinDimensionTypes.NETHER_EFFECTS,
                0f,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 7), 7)
        );
    }
}
