package fr.dwightstudio.deepworld.common.blockentities.machines.wood;

import fr.dwightstudio.deepworld.common.registries.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.registries.DeepworldMenus;
import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeBookTypes;
import fr.dwightstudio.deepworld.common.registries.DeepworldRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodenLatheBlockEntity extends WoodenMachineBlockEntity {

    public WoodenLatheBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_LATHE.get(), DeepworldMenus.WOODEN_LATHE.get(), DeepworldRecipeBookTypes.LATHE, blockPos, blockState, DeepworldRecipeTypes.LATHING.get());
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.deepworld.wooden_lathe");
    }
}
