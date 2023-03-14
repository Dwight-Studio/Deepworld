package fr.dwightstudio.deepworld.item.parts.component;

import fr.dwightstudio.deepworld.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

import java.util.Objects;

public class WoodenFrameComponentItem extends Item {

    public WoodenFrameComponentItem(Settings settings) {
        super(settings);
    }


    public ActionResult onUse(ItemStack stack, ItemUsageContext context){
        if(context.getWorld().isClient){
            return ActionResult.PASS;
        }

        BlockEntity aTileEntity = context.getWorld().getBlockEntity(context.getBlockPos());

        if (aTileEntity instanceof WoodenFrameBlockEntity tileEntity){

            switch (getComponent().getComponentClass()) {
                case PRIMARY -> {
                    if (!(tileEntity.getPrimaryComponent() == 0)) {
                        return ActionResult.FAIL;
                    }
                }
                case SECONDARY -> {
                    if (!(tileEntity.getSecondaryComponent() == 0)) {
                        return ActionResult.FAIL;
                    }
                }
                case TERTIARY -> {
                    if (!(tileEntity.getTertiaryComponent() == 0)) {
                        return ActionResult.FAIL;
                    }
                }
                default -> {
                    return ActionResult.FAIL;
                }
            }

            switch (getComponent().getComponentClass()) {
                case PRIMARY -> tileEntity.setPrimaryComponent(getComponent().getID());
                case SECONDARY -> tileEntity.setSecondaryComponent(getComponent().getID());
                case TERTIARY -> tileEntity.setTertiaryComponent(getComponent().getID());
                default -> {
                    return ActionResult.FAIL;
                }
            }
            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }

    }

    // Get the component (to override)
    public DeepworldWoodenFrameComponent getComponent() {
        throw new NullPointerException("Component undefined.");
    }
}
