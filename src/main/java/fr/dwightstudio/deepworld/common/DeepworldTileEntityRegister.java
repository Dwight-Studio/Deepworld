package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldTileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {

        TileEntityType<TileEntityWoodenFrame> WOODEN_FRAME_TILE_ENTITY_TYPE = TileEntityType.Builder.create(TileEntityWoodenFrame::new,DeepworldBlocks.WOODEN_FRAME).build(null);
        // TileEntityType<TileEntityWoodenPress> WOODEN_PRESS_ENTITY_TYPE = TileEntityType.Builder.create(TileEntityWoodenPress::new, DeepworldBlocks.WOODEN_PRESS).build(null);

        WOODEN_FRAME_TILE_ENTITY_TYPE.setRegistryName(new ResourceLocation(Deepworld.MOD_ID, "wooden_frame_tile_entity_type"));

        event.getRegistry().register(WOODEN_FRAME_TILE_ENTITY_TYPE);
        // event.getRegistry().register(WOODEN_PRESS_ENTITY_TYPE);
    }
}
