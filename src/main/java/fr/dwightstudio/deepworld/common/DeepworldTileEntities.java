package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.tile.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class DeepworldTileEntities {

    // Wooden machines
    @ObjectHolder(registryName = "minecraft:block_entity", value = "deepworld:wooden_frame")
    public static final BlockEntityType<TileEntityWoodenFrame> WOODEN_FRAME = null;
    @ObjectHolder(registryName = "minecraft:block_entity", value = "deepworld:wooden_press")
    public static final BlockEntityType<TileEntityWoodenPress> WOODEN_PRESS = null;
    @ObjectHolder(registryName = "minecraft:block_entity", value = "deepworld:wooden_gear_shaper")
    public static final BlockEntityType<TileEntityWoodenGearShaper> WOODEN_GEAR_SHAPER = null;
    @ObjectHolder(registryName = "minecraft:block_entity", value = "deepworld:wooden_lathe")
    public static final BlockEntityType<TileEntityWoodenLathe> WOODEN_LATHE = null;
    @ObjectHolder(registryName = "minecraft:block_entity", value = "deepworld:pipe")
    public static final BlockEntityType<TileEntityPipe> PIPE = null;
}
