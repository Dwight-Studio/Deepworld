package fr.dwightstudio.deepworld.blockentities;

import fr.dwightstudio.deepworld.Deepworld;
import fr.dwightstudio.deepworld.block.DeepworldBlocks;
import fr.dwightstudio.deepworld.blockentities.frames.WoodenFrameBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DeepworldEntities {

    public static BlockEntityType<WoodenFrameBlockEntity> WOODEN_FRAME;

    public static void registerBlockEntities(){
        WOODEN_FRAME = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Deepworld.MOD_ID, "wooden_frame"),
                FabricBlockEntityTypeBuilder.create(WoodenFrameBlockEntity::new,
                        DeepworldBlocks.WOODEN_FRAME).build(null));
    }

}
