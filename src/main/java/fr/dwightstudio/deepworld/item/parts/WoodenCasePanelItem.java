package fr.dwightstudio.deepworld.item.parts;

import fr.dwightstudio.deepworld.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WoodenCasePanelItem extends Item {

    public WoodenCasePanelItem() {
        super(new FabricItemSettings());
    }

    public ActionResult onUse(ItemStack stack, @NotNull ItemUsageContext context){
        if(context.getWorld().isClient){
            return ActionResult.PASS;
        }

        BlockEntity aTileEntity = context.getWorld().getBlockEntity(context.getBlockPos());

        if (aTileEntity instanceof WoodenFrameBlockEntity blockEntity){
            if (DeepworldWoodenFrameComponent.getResultFromTile(blockEntity) == null) {
                return ActionResult.FAIL;
            }
            boolean success = blockEntity.addCover();
            if (!success) {
                return ActionResult.FAIL;
            }
            if (!Objects.requireNonNull(context.getPlayer()).isCreative()) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }

    }

}
