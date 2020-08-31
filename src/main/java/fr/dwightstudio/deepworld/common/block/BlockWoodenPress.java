package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldItemRegister;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
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

    // Block property initializing
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final IProperty<Boolean> WORKING = PropertyBool.create("working");

    // Block state creation (registering properties)
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, WORKING);
    }

    // Constructor
    public BlockWoodenPress() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(Deepworld.creativeTab);
        this.setHardness(3);
        this.setResistance(2);

        this.setDefaultState(
                this.getBlockState().getBaseState()
                        .withProperty(FACING, EnumFacing.NORTH)
                        .withProperty(WORKING, false)
        );
    }

    // Setting customs drops
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(DeepworldItems.WOODEN_CRANK, 1));
        drops.add(new ItemStack(DeepworldItems.SIMPLE_PRESSING_CHAMBER, 1));
        drops.add(new ItemStack(DeepworldItems.WOODEN_GEARBOX, 1));
        drops.add(new ItemStack(Items.STICK, 4));
        drops.add(new ItemStack(Blocks.PLANKS, 4));
        drops.add(new ItemStack(DeepworldItems.WOODEN_CASE_PANEL, 6));
        super.getDrops(drops, world, pos, state, fortune);
    }

    // Disabling regular drop (the block itself)
    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    // Setting visual block properties
    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    /*
     * Setting custom harvest level & tool
     * -> Harvest level
     *      0 - wood
     *      1 - stone
     *      2 - iron
     *      3 - diamond
     *      >3 - custom (mod implemented tools)
     *
     *  -> Harvest tool
     *      pickaxe
     *      axe
     *      shovel
     *      sword
     *      hoe
     */
    @Override
    public int getHarvestLevel(IBlockState state) {
        return 0;
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "axe";
    }

    // Allow transparency
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /*
     * Those functions are required but may be optional if
     * block state is saved in a Tile Entity.
     * (functions must be declared anyway to avoid crashing but
     * can just return default state & 0)
     *
     * Here, only the facing property is saved within the metadata
     */
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
