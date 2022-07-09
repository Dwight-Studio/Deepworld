package fr.dwightstudio.deepworld.common.blockentity.machines.wood;

import fr.dwightstudio.deepworld.common.DeepworldBlockEntities;
import fr.dwightstudio.deepworld.common.DeepworldMenus;
import fr.dwightstudio.deepworld.common.DeepworldRecipeBookTypes;
import fr.dwightstudio.deepworld.common.DeepworldRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WoodenLatheBlockEntity extends WoodenMachineBlockEntity{

    public WoodenLatheBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.WOODEN_LATHE.get(), DeepworldMenus.WOODEN_LATHE_MENU.get(), DeepworldRecipeBookTypes.LATHE, blockPos, blockState, DeepworldRecipeTypes.LATHING.get());
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.deepworld.wooden_lathe");
    }
}
