package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldTileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {

        TileEntityType<TileEntityWoodenFrame> WOODEN_FRAME = TileEntityType.Builder.create(TileEntityWoodenFrame::new,DeepworldBlocks.WOODEN_FRAME).build(null);
        // TileEntityType<TileEntityWoodenPress> WOODEN_PRESS_ENTITY_TYPE = TileEntityType.Builder.create(TileEntityWoodenPress::new, DeepworldBlocks.WOODEN_PRESS).build(null);

        WOODEN_FRAME.setRegistryName(new ResourceLocation(Deepworld.MOD_ID, "wooden_frame"));

        event.getRegistry().register(WOODEN_FRAME);
        // event.getRegistry().register(WOODEN_PRESS);
    }
}
