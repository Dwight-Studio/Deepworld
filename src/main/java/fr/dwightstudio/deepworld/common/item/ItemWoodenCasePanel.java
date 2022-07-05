package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ItemWoodenCasePanel extends Item {

    public ItemWoodenCasePanel() {
        super(new Item.Properties().tab(Deepworld.itemGroup));
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockEntity aTileEntity = context.getLevel().getBlockEntity(context.getClickedPos());

        if (aTileEntity instanceof TileEntityWoodenFrame tileEntity) {

            if (WoodenFrameComponent.getResultFromTile(tileEntity) == null) {
                return InteractionResult.FAIL;
            }

            boolean success = tileEntity.addCover();

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
