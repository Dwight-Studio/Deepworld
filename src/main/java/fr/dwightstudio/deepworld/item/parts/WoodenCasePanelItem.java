package fr.dwightstudio.deepworld.item.parts;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class WoodenCasePanelItem extends Item {

    public WoodenCasePanelItem() {
        super(new FabricItemSettings());
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

}
