package fr.dwightstudio.deepworld.common.items;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blockentities.machines.wood.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WoodenCasePanelItem extends Item {

    public WoodenCasePanelItem() {
        super(new Properties().tab(Deepworld.MOD_TAB));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockEntity aTileEntity = context.getLevel().getBlockEntity(context.getClickedPos());

        if (aTileEntity instanceof WoodenFrameBlockEntity blockEntity) {

            if (WoodenFrameComponent.getResultFromTile(blockEntity) == null) {
                return InteractionResult.FAIL;
            }

            boolean success = blockEntity.addCover();

            if (!success) {
                return InteractionResult.FAIL;
            }

            if (!context.getPlayer().isCreative()) {
                stack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}
