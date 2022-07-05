package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.world.item.Item;

public class ItemWoodenCasePanel extends Item {

    public ItemWoodenCasePanel() {
        super(new Item.Properties().group(Deepworld.itemGroup));
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        if (context.getWorld().isRemote) {
            return ActionResultType.PASS;
        }

        TileEntity aTileEntity = context.getWorld().getTileEntity(context.getPos());

        if (aTileEntity instanceof TileEntityWoodenFrame) {
            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame) aTileEntity;

            if (WoodenFrameComponent.getResultFromTile(tileEntity) == null) {
                return ActionResultType.FAIL;
            }

            boolean success = tileEntity.addCover();

            if (!success) {
                return ActionResultType.FAIL;
            }

            if (!context.getPlayer().isCreative()) {
                stack.shrink(1);
            }
            return ActionResultType.SUCCESS;
    } else {
            return ActionResultType.PASS;
        }
    }
}
