package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.blockentity.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.blockentity.WoodenLatheBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public class DeepworldBlockEntities {

    public static RegistryObject<BlockEntityType<?>> WOODEN_LATHE;
    public static RegistryObject<BlockEntityType<?>> WOODEN_FRAME;

    public DeepworldBlockEntities() {
        WOODEN_LATHE = Deepworld.BLOCK_ENTITIES.register("wooden_lathe", () -> BlockEntityType.Builder.of((blockPos, blockState) ->  new WoodenLatheBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_LATHE.get()).build(null));
        WOODEN_FRAME = Deepworld.BLOCK_ENTITIES.register("wooden_frame", () -> BlockEntityType.Builder.of((blockPos, blockState) -> new WoodenFrameBlockEntity(blockPos, blockState), DeepworldBlocks.WOODEN_FRAME.get()).build(null));
    }
}
