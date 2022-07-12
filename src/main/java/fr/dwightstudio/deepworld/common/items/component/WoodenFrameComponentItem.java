package fr.dwightstudio.deepworld.common.items.component;

import fr.dwightstudio.deepworld.common.components.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.blockentities.frames.WoodenFrameBlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WoodenFrameComponentItem extends Item {
    public WoodenFrameComponentItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockEntity aTileEntity = context.getLevel().getBlockEntity(context.getClickedPos());

        if (aTileEntity instanceof WoodenFrameBlockEntity tileEntity) {

            switch (getComponent().getComponentClass()) {
                case PRIMARY:
                    if (!(tileEntity.getPrimaryComponent() == 0)) {
                        return InteractionResult.FAIL;
                    }
                    break;
                case SECONDARY:
                    if (!(tileEntity.getSecondaryComponent() == 0)) {
                        return InteractionResult.FAIL;
                    }
                    break;
                case TERTIARY:
                    if (!(tileEntity.getTertiaryComponent() == 0)) {
                        return InteractionResult.FAIL;
                    }
                    break;
                default:
                    return InteractionResult.FAIL;

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

    // Get the component (to override)
    public WoodenFrameComponent getComponent() {
        throw new NullPointerException("Component undefined.");
    }
}
