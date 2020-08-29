package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.DeepworldBlocks;
import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWoodenFrame extends BlockFrame {
    public BlockWoodenFrame() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3);
        this.setResistance(2);

        this.setDefaultState(
                this.getBlockState().getBaseState()
                        .withProperty(FACING, EnumFacing.NORTH)
                        .withProperty(BOTTOM, false)
                        .withProperty(TOP, false)
                        .withProperty(FRONT, false)
                        .withProperty(BACK, false)
                        .withProperty(LEFT, false)
                        .withProperty(RIGHT, false)
                        .withProperty(MAIN_COMPONENT, 0)
                        .withProperty(SECONDARY_COMPONENT, 0)
                        .withProperty(TERTIARY_COMPONENT, 0)
        );
    }

    public static final IProperty<Integer> MAIN_COMPONENT = PropertyInteger.create("main_component", 0, 1);
    public static final IProperty<Integer> SECONDARY_COMPONENT = PropertyInteger.create("secondary_component", 0, 1);
    public static final IProperty<Integer> TERTIARY_COMPONENT = PropertyInteger.create("tertiary_component", 0, 1);

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWoodenFrame();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        TileEntity aTileEntity = worldIn.getTileEntity(pos);

        if (aTileEntity instanceof TileEntityWoodenFrame) {

            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame)aTileEntity;

            boolean[] covers = tileEntity.getCovers();

            int mainComp = tileEntity.getMainComponent();
            int secComp = tileEntity.getSecondaryComponent();
            int terComp = tileEntity.getTertiaryComponent();

            return state.withProperty(BOTTOM, covers[0])
                    .withProperty(TOP, covers[1])
                    .withProperty(FRONT, covers[2])
                    .withProperty(BACK, covers[3])
                    .withProperty(LEFT, covers[4])
                    .withProperty(RIGHT, covers[5])
                    .withProperty(MAIN_COMPONENT, mainComp)
                    .withProperty(SECONDARY_COMPONENT, secComp)
                    .withProperty(TERTIARY_COMPONENT, terComp);
        }

        return state;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BOTTOM, TOP, FRONT, BACK, LEFT, RIGHT, MAIN_COMPONENT, SECONDARY_COMPONENT, TERTIARY_COMPONENT);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {

        TileEntity aTileEntity = world.getTileEntity(pos);

        if (aTileEntity instanceof TileEntityWoodenFrame) {
            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame) aTileEntity;

            // CasePlate
            int casePlate = 0;
            for (Boolean isPresent : tileEntity.getCovers()) {
                if (isPresent) {
                    casePlate++;
                }
            }

            if (casePlate > 0) {
                drops.add(new ItemStack(DeepworldItems.WOODEN_CASE_PANEL, casePlate));
            }

            // Components
            switch (tileEntity.getMainComponent()) {
                case 0:
                    break;
                case 1:
                    drops.add(new ItemStack(DeepworldItems.SIMPLE_PRESSING_CHAMBER, 1));
                    break;
            }

            switch (tileEntity.getSecondaryComponent()) {
                case 0:
                    break;
                case 1:
                    drops.add(new ItemStack(DeepworldItems.WOODEN_GEARBOX, 1));
                    break;
            }

            switch (tileEntity.getTertiaryComponent()) {
                case 0:
                    break;
                case 1:
                    drops.add(new ItemStack(DeepworldItems.WOODEN_CRANK, 1));
                    break;
            }

        }

        drops.add(new ItemStack(Items.STICK, 4));
        drops.add(new ItemStack(Blocks.PLANKS, 4));
        super.getDrops(drops, world, pos, state, fortune);
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
}
