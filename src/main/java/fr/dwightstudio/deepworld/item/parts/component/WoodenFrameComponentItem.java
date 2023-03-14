package fr.dwightstudio.deepworld.item.parts.component;

import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class WoodenFrameComponentItem extends Item {

    public WoodenFrameComponentItem(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(ItemStack stack, ItemUsageContext context){
        if(context.getWorld().isClient){
            return ActionResult.PASS;
        }

        BlockEntity aTileEntity = context.getWorld().getBlockEntity(context.getBlockPos());

        if (true){
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
