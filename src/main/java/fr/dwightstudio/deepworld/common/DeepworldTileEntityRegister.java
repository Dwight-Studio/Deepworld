package fr.dwightstudio.deepworld.common;

import fr.dwightstudio.deepworld.common.block.BlockPipe;
import fr.dwightstudio.deepworld.common.tile.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;

public class DeepworldTileEntityRegister {

    @SubscribeEvent
    public static void onTileEntityTypeRegistration(final RegisterEvent event) {

        BlockEntityType<TileEntityWoodenFrame>      WOODEN_FRAME       = BlockEntityType.Builder.of(TileEntityWoodenFrame::new,DeepworldBlocks.WOODEN_FRAME).build(null);
        BlockEntityType<TileEntityWoodenPress>      WOODEN_PRESS       = BlockEntityType.Builder.of(TileEntityWoodenPress::new, DeepworldBlocks.WOODEN_PRESS).build(null);
        BlockEntityType<TileEntityWoodenGearShaper> WOODEN_GEAR_SHAPER = BlockEntityType.Builder.of(TileEntityWoodenGearShaper::new, DeepworldBlocks.WOODEN_GEAR_SHAPER).build(null);
        BlockEntityType<TileEntityWoodenLathe>      WOODEN_LATHE       = BlockEntityType.Builder.of(TileEntityWoodenLathe::new, DeepworldBlocks.WOODEN_LATHE).build(null);
        BlockEntityType<TileEntityPipe>             PIPE               = BlockEntityType.Builder.of(TileEntityPipe::new, DeepworldBlocks.PIPE).build(null);

        event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, helper -> {
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_frame"), WOODEN_FRAME);
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_press"), WOODEN_PRESS);
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_gear_shaper"), WOODEN_GEAR_SHAPER);
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "wooden_lathe"), WOODEN_LATHE);
            helper.register(new ResourceLocation(Deepworld.MOD_ID, "pipe"), PIPE);
        });
    }
}
