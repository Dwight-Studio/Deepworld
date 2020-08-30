package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.frame.ComponentClass;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockWoodenFrame extends BlockFrame {

    // Block property initializing
    public static final IProperty<Integer> PRIMARY_COMPONENT = PropertyInteger.create("primary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.PRIMARY));
    public static final IProperty<Integer> SECONDARY_COMPONENT = PropertyInteger.create("secondary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.SECONDARY));
    public static final IProperty<Integer> TERTIARY_COMPONENT = PropertyInteger.create("tertiary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.TERTIARY));

    // Adding custom properties
    @Override
    protected IProperty[] getComponentsProperties() {
        return new IProperty[] {PRIMARY_COMPONENT, SECONDARY_COMPONENT, TERTIARY_COMPONENT};
    }

    // Adding default custom properties value
    @Override
    protected IBlockState setComponentPropertiesDefaultValues(IBlockState state) {
        return state
                .withProperty(PRIMARY_COMPONENT, 0)
                .withProperty(SECONDARY_COMPONENT, 0)
                .withProperty(TERTIARY_COMPONENT, 0);
    }

    // Constructor
    public BlockWoodenFrame() {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3);
        this.setResistance(2);
    }

    // Assign the TileEntity
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWoodenFrame();
    }

    // Override the state getter to get data from the tile entity
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

        TileEntity aTileEntity = worldIn.getTileEntity(pos);

        if (aTileEntity instanceof TileEntityWoodenFrame) {

            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame)aTileEntity;

            boolean[] covers = tileEntity.getCovers();

            int primaryComp = tileEntity.getPrimaryComponent();
            int secComp = tileEntity.getSecondaryComponent();
            int terComp = tileEntity.getTertiaryComponent();

            return state.withProperty(BOTTOM, covers[0])
                    .withProperty(TOP, covers[1])
                    .withProperty(FRONT, covers[2])
                    .withProperty(BACK, covers[3])
                    .withProperty(LEFT, covers[4])
                    .withProperty(RIGHT, covers[5])
                    .withProperty(PRIMARY_COMPONENT, primaryComp)
                    .withProperty(SECONDARY_COMPONENT, secComp)
                    .withProperty(TERTIARY_COMPONENT, terComp);
        }

        return state;
    }

    // Setting customs drops (depends on what was stored inside)
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
            if (tileEntity.getPrimaryComponent() != 0) {
                drops.add(new ItemStack(WoodenFrameComponent.getByID(tileEntity.getPrimaryComponent()).getItem(), 1));
            }

            if (tileEntity.getSecondaryComponent() != 0) {
                drops.add(new ItemStack(WoodenFrameComponent.getByID(tileEntity.getSecondaryComponent()).getItem(), 1));
            }

            if (tileEntity.getTertiaryComponent() != 0) {
                drops.add(new ItemStack(WoodenFrameComponent.getByID(tileEntity.getTertiaryComponent()).getItem(), 1));
            }

        }

        drops.add(new ItemStack(Items.STICK, 4));
        drops.add(new ItemStack(Blocks.PLANKS, 4));
        super.getDrops(drops, world, pos, state, fortune);
    }

    // Disabling regular drop (the block itself)
    @Override
    public int quantityDropped(Random random) {
        return 0;
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
}
