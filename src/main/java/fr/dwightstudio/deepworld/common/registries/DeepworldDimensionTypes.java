package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.dimension.DimensionDeepworld;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldDimensionTypes {

    public static ResourceKey<DimensionType> DEEPWORLD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, Deepworld.loc("deepworld_dim_type"));

    public static Holder.Reference<DimensionType> DEEPWORLD;

    public static void register(BootstapContext<DimensionType> context) {
        DEEPWORLD = context.register(DEEPWORLD_DIM_TYPE, DimensionDeepworld.getDimensionType());
    }
}
