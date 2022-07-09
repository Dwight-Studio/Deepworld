package fr.dwightstudio.deepworld.common.blocks.machines.wood;

import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenPressBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodenPressBlock extends WoodenMachineBlock implements EntityBlock {

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new WoodenPressBlockEntity(blockPos, blockState);
    }

}
