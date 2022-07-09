package fr.dwightstudio.deepworld.common.blockentities.machines.wood;

import fr.dwightstudio.deepworld.common.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.DeepworldMenus;
import fr.dwightstudio.deepworld.common.DeepworldRecipeBookTypes;
import fr.dwightstudio.deepworld.common.DeepworldRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodenGearShaperBlockEntity extends WoodenMachineBlockEntity {

    public WoodenGearShaperBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_GEAR_SHAPER.get(), DeepworldMenus.WOODEN_GEAR_SHAPER.get(), DeepworldRecipeBookTypes.GEAR_SHAPER, blockPos, blockState, DeepworldRecipeTypes.GEAR_SHAPING.get());
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.deepworld.wooden_gear_shaper");
    }
}
