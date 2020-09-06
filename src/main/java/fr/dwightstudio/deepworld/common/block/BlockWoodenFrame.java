package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.frame.ComponentClass;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Random;

public class BlockWoodenFrame extends BlockFrame {

    // Block property initializing
    public static final IProperty<Integer> PRIMARY_COMPONENT = IntegerProperty.create("primary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.PRIMARY));
    public static final IProperty<Integer> SECONDARY_COMPONENT = IntegerProperty.create("secondary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.SECONDARY));
    public static final IProperty<Integer> TERTIARY_COMPONENT = IntegerProperty.create("tertiary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.TERTIARY));

    // Adding custom properties
    @Override
    protected IProperty<?>[] getComponentsProperties() {
        return new IProperty[] {PRIMARY_COMPONENT, SECONDARY_COMPONENT, TERTIARY_COMPONENT};
    }

    // Adding default custom properties value
    @Override
    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state
                .with(PRIMARY_COMPONENT, 0)
                .with(SECONDARY_COMPONENT, 0)
                .with(TERTIARY_COMPONENT, 0);
    }

    // Constructor
    public BlockWoodenFrame() {
        super(Properties.create(Material.WOOD)
        .sound(SoundType.WOOD)
        .hardnessAndResistance(3, 2)
        .harvestLevel(0)
        .harvestTool(ToolType.AXE));
    }

    // Assign the TileEntity
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityWoodenFrame();
    }

    // Override the state getter to get data from the tile entity
    @Override
    public BlockState getActualState(BlockState state, IBlockReader worldIn, BlockPos pos) {

        TileEntity aTileEntity = worldIn.getTileEntity(pos);

        if (aTileEntity instanceof TileEntityWoodenFrame) {

            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame)aTileEntity;

            boolean[] covers = tileEntity.getCovers();

            int primaryComp = tileEntity.getPrimaryComponent();
            int secComp = tileEntity.getSecondaryComponent();
            int terComp = tileEntity.getTertiaryComponent();

            return state.with(BOTTOM, covers[0])
                    .with(TOP, covers[1])
                    .with(FRONT, covers[2])
                    .with(BACK, covers[3])
                    .with(LEFT, covers[4])
                    .with(RIGHT, covers[5])
                    .with(PRIMARY_COMPONENT, primaryComp)
                    .with(SECONDARY_COMPONENT, secComp)
                    .with(TERTIARY_COMPONENT, terComp);
        }

        return state;
    }


    @Override
    // Setting customs drops (depends on what was stored inside)
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        TileEntity aTileEntity = builder.getWorld();

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
}
