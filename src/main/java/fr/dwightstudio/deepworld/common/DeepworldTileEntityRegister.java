package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenGearShaper;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenLathe;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeepworldTileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {

        TileEntityType<TileEntityWoodenFrame> WOODEN_FRAME = TileEntityType.Builder.create(TileEntityWoodenFrame::new,DeepworldBlocks.WOODEN_FRAME).build(null);
        TileEntityType<TileEntityWoodenPress> WOODEN_PRESS = TileEntityType.Builder.create(TileEntityWoodenPress::new, DeepworldBlocks.WOODEN_PRESS).build(null);
        TileEntityType<TileEntityWoodenGearShaper> WOODEN_GEAR_SHAPER = TileEntityType.Builder.create(TileEntityWoodenGearShaper::new, DeepworldBlocks.WOODEN_GEAR_SHAPER).build(null);
        TileEntityType<TileEntityWoodenLathe> WOODEN_LATHE = TileEntityType.Builder.create(TileEntityWoodenLathe::new, DeepworldBlocks.WOODEN_LATHE).build(null);

        event.getRegistry().register(WOODEN_FRAME.setRegistryName(Deepworld.MOD_ID, "wooden_frame"));
        event.getRegistry().register(WOODEN_PRESS.setRegistryName(Deepworld.MOD_ID, "wooden_press"));
        event.getRegistry().register(WOODEN_GEAR_SHAPER.setRegistryName(Deepworld.MOD_ID, "wooden_gear_shaper"));
        event.getRegistry().register(WOODEN_LATHE.setRegistryName(Deepworld.MOD_ID, "wooden_lathe"));
    }
}
