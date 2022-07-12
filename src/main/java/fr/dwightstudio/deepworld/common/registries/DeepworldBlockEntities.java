package fr.dwightstudio.deepworld.common.registries;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenGearShaperBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenLatheBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenPressBlockEntity;
import fr.dwightstudio.deepworld.common.blockentities.tanks.IronTankBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlockEntities {

    public static RegistryObject<BlockEntityType<?>> WOODEN_FRAME;

    public static RegistryObject<BlockEntityType<WoodenLatheBlockEntity>> WOODEN_LATHE;
    public static RegistryObject<BlockEntityType<WoodenPressBlockEntity>> WOODEN_PRESS;
    public static RegistryObject<BlockEntityType<WoodenGearShaperBlockEntity>> WOODEN_GEAR_SHAPER;

    public static RegistryObject<BlockEntityType<IronTankBlockEntity>> IRON_TANK;

    public DeepworldBlockEntities() {
        WOODEN_FRAME = Deepworld.BLOCK_ENTITIES.register("wooden_frame", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenFrameBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_FRAME.get()).build(null));

        WOODEN_LATHE = Deepworld.BLOCK_ENTITIES.register("wooden_lathe", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenLatheBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_LATHE.get()).build(null));
        WOODEN_PRESS = Deepworld.BLOCK_ENTITIES.register("wooden_press", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenPressBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_PRESS.get()).build(null));
        WOODEN_GEAR_SHAPER = Deepworld.BLOCK_ENTITIES.register("wooden_gear_shaper", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenGearShaperBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_GEAR_SHAPER.get()).build(null));

        IRON_TANK = Deepworld.BLOCK_ENTITIES.register("iron_tank", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new IronTankBlockEntity(blockPos, blockState), DeepworldBlocks.IRON_TANK.get()).build(null));
    }
}
