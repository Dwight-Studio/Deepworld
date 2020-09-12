package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.frame.ComponentClass;
import fr.dwightstudio.deepworld.common.frame.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenFrame;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockWoodenFrame extends BlockFrame implements ITileEntityProvider {

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
        return createNewTileEntity(world);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new TileEntityWoodenFrame();
    }

    // Setting customs drops (depends on what was stored inside)
    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {

        TileEntity aTileEntity = world.getTileEntity(pos);

        if (aTileEntity instanceof TileEntityWoodenFrame) {
            TileEntityWoodenFrame tileEntity = (TileEntityWoodenFrame) aTileEntity;

            // CasePlate
            int casePlate = tileEntity.getCovers();

            if (casePlate > 0) {
                spawnAsEntity(world, pos, new ItemStack(DeepworldItems.WOODEN_CASE_PANEL, casePlate));
            }

            // Components
            if (tileEntity.getPrimaryComponent() != 0) {
                spawnAsEntity(world, pos, new ItemStack(WoodenFrameComponent.getByID(tileEntity.getPrimaryComponent()).getItem(), 1));
            }

            if (tileEntity.getSecondaryComponent() != 0) {
                spawnAsEntity(world, pos, new ItemStack(WoodenFrameComponent.getByID(tileEntity.getSecondaryComponent()).getItem(), 1));
            }

            if (tileEntity.getTertiaryComponent() != 0) {
                spawnAsEntity(world, pos, new ItemStack(WoodenFrameComponent.getByID(tileEntity.getTertiaryComponent()).getItem(), 1));
            }

        }

        spawnAsEntity(world, pos, new ItemStack(Items.STICK, 4));
        spawnAsEntity(world, pos, new ItemStack(Blocks.OAK_PLANKS, 4));
    }
}
