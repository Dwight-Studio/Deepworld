package fr.dwightstudio.deepworld.common.blocks;

import fr.dwightstudio.deepworld.common.blockentities.tanks.IronTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IronTankBlock extends Block implements EntityBlock {

    private IronTankBlockEntity blockEntity;

    public IronTankBlock() {
        super(Properties.of(Material.STONE)
                .sound(SoundType.GLASS));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        this.blockEntity = new IronTankBlockEntity(blockPos, blockState);
        return this.blockEntity;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            LogManager.getLogger().log(org.apache.logging.log4j.Level.DEBUG, FluidUtil.tryEmptyContainer(player.getItemInHand(interactionHand), this.blockEntity, this.blockEntity.getCapacity(), player, false) != FluidActionResult.FAILURE);
            return InteractionResult.CONSUME;
        }

    }
}
