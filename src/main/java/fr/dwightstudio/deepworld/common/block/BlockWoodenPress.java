package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockWoodenPress extends Block {
    public BlockWoodenPress() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(Deepworld.creativeTab);
        this.setHardness(3);
        this.setResistance(2);

        this.setDefaultState(
                this.getBlockState().getBaseState()
                        .withProperty(FACING, EnumFacing.NORTH)
        );
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(Items.STICK, 4));
        drops.add(new ItemStack(Blocks.PLANKS, 4));
        drops.add(new ItemStack(DeepworldItems.WOODEN_CASE_PANEL, 6));
        super.getDrops(drops, world, pos, state, fortune);
    }

    public static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return 0;
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "axe";
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

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
}
