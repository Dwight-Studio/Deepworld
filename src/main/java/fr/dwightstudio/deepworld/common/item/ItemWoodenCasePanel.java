package fr.dwightstudio.deepworld.common.item;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWoodenCasePanel extends Item {

    public ItemWoodenCasePanel() {
        setCreativeTab(Deepworld.creativeTab);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (world.isRemote) {
            return EnumActionResult.PASS;
        }

        ItemStack stack = player.getHeldItem(hand);
        TileEntity aTileEntity = world.getTileEntity(pos);

        if (aTileEntity instanceof TileEntityWoodenFrame) {
            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame) aTileEntity;

            if (tileEntity.getMachineBlock() == null) {
                Deepworld.logger.info("FAIL1");
                return EnumActionResult.FAIL;
            }

            boolean success = tileEntity.addCover();

            if (!success) {
                Deepworld.logger.info("FAIL2");
                return EnumActionResult.FAIL;
            }

            if (!player.capabilities.isCreativeMode) {
                stack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
    } else {
            Deepworld.logger.info("PASS");
            return EnumActionResult.PASS;
        }
    }
}
