package fr.dwightstudio.deepworld.common.blocks.machines.wood;

import fr.dwightstudio.deepworld.common.blockentity.machines.wood.WoodenGearShaperBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoodenGearShaperBlock extends WoodenMachineBlock implements EntityBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new WoodenGearShaperBlockEntity(blockPos, blockState);
    }

}
