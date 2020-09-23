package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;

public class ItemWoodenFrameComponent extends Item {

    public ItemWoodenFrameComponent(Properties properties) {
        super(properties);
    }

    @Override
    public final ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        if (context.getWorld().isRemote) {
            return ActionResultType.PASS;
        }

        TileEntity aTileEntity = context.getWorld().getTileEntity(context.getPos());

        if (aTileEntity instanceof TileEntityWoodenFrame) {
            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame) aTileEntity;

            switch (getComponent().getComponentClass()) {
                case PRIMARY:
                    if (!(tileEntity.getPrimaryComponent() == 0)) {
                        return ActionResultType.FAIL;
                    }
                    break;
                case SECONDARY:
                    if (!(tileEntity.getSecondaryComponent() == 0)) {
                        return ActionResultType.FAIL;
                    }
                    break;
                case TERTIARY:
                    if (!(tileEntity.getTertiaryComponent() == 0)) {
                        return ActionResultType.FAIL;
                    }
                    break;
                default:
                    return ActionResultType.FAIL;

            }

            switch (getComponent().getComponentClass()) {
                case PRIMARY:
                    tileEntity.setPrimaryComponent(getComponent().getID());
                    break;
                case SECONDARY:
                    tileEntity.setSecondaryComponent(getComponent().getID());
                    break;
                case TERTIARY:
                    tileEntity.setTertiaryComponent(getComponent().getID());
                    break;
                default:
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

    // Get the component (to override)
    public WoodenFrameComponent getComponent() {
        throw new NullPointerException("Component undefined.");
    }
}
