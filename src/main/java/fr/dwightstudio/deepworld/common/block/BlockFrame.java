package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.Deepworld;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFrame extends Block {

    public BlockFrame(Material material) {
        super(material);
        this.setCreativeTab(Deepworld.creativeTab);
    }

    public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final IProperty<Boolean> BOTTOM = PropertyBool.create("bottom");
    public static final IProperty<Boolean> TOP = PropertyBool.create("top");
    public static final IProperty<Boolean> FRONT = PropertyBool.create("front");
    public static final IProperty<Boolean> BACK = PropertyBool.create("back");
    public static final IProperty<Boolean> LEFT = PropertyBool.create("left");
    public static final IProperty<Boolean> RIGHT = PropertyBool.create("right");

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.NORTH;

        switch (meta) {
            case 0:
                facing = EnumFacing.NORTH;
                break;
            case 1:
                facing = EnumFacing.EAST;
                break;
            case 2:
                facing = EnumFacing.SOUTH;
                break;
            case 3:
                facing = EnumFacing.WEST;
                break;
        }

        IBlockState state = this.getDefaultState()
                .withProperty(FACING, facing);

        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;

        switch (state.getValue(FACING)) {
            case NORTH:
                meta = 0;
                break;
            case EAST:
                meta = 1;
                break;
            case SOUTH:
                meta = 2;
                break;
            case WEST:
                meta = 3;
                break;
        }

        return meta;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }
    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack tool)
    {
        super.harvestBlock(world, player, pos, state, te, tool);
        world.setBlockToAir(pos);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), Constants.BlockFlags.DEFAULT);
        world.markBlockRangeForRenderUpdate(pos, pos);
        return true;
    }
}
