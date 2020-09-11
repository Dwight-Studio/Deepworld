package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;

public class ItemSimpleCutter extends Item {

    public ItemSimpleCutter() {
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

            if (!(tileEntity.getPrimaryComponent() == 0)) {
                return ActionResultType.FAIL;
            }

            tileEntity.setPrimaryComponent(WoodenFrameComponent.SIMPLE_CUTTER.getID());

            if (!context.getPlayer().isCreative()) {
                stack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.FAIL;
        }
    }
}
