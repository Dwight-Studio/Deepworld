package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.blockentity.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.blockentity.WoodenMachineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlockEntities {

    public static RegistryObject<BlockEntityType<?>> WOODEN_FRAME;

    public static RegistryObject<BlockEntityType<WoodenMachineBlockEntity>> WOODEN_LATHE;
    public static RegistryObject<BlockEntityType<WoodenMachineBlockEntity>> WOODEN_PRESS;

    public DeepworldBlockEntities() {
        WOODEN_FRAME = Deepworld.BLOCK_ENTITIES.register("wooden_frame", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenFrameBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_FRAME.get()).build(null));

        WOODEN_LATHE = Deepworld.BLOCK_ENTITIES.register("wooden_lathe", () -> BlockEntityType.Builder.of((blockPos, blockState) ->  new WoodenMachineBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_LATHE.get()).build(null));
        WOODEN_PRESS = Deepworld.BLOCK_ENTITIES.register("wooden_press", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenMachineBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_PRESS.get()).build(null));
    }
}
