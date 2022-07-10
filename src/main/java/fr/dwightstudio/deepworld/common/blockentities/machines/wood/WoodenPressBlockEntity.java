package fr.dwightstudio.deepworld.common.blockentities.machines.wood;

import fr.dwightstudio.deepworld.common.registries.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.registries.DeepworldMenus;
import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeBookTypes;
import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodenPressBlockEntity extends WoodenMachineBlockEntity {

    public WoodenPressBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_PRESS.get(), DeepworldMenus.WOODEN_PRESS.get(), DeepworldRecipeBookTypes.PRESS, blockPos, blockState, DeepworldRecipeTypes.PRESSING.get());
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.deepworld.wooden_press");
    }
}
