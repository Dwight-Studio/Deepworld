package fr.dwightstudio.deepworld.common.blockentities.tanks;

import fr.dwightstudio.deepworld.common.registries.DeepworldBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class IronTankBlockEntity extends SimpleTankBlockEntity {

    public static final int CAPACITY = 8000;

    public IronTankBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DeepworldBlockEntities.IRON_TANK.get(), blockPos, blockState, CAPACITY);
    }

}
