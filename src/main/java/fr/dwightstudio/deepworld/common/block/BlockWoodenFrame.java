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
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

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
        .harvestTool(ToolType.AXE)
        .notSolid());
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

    // Setting drops
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        drops.add(new ItemStack(DeepworldItems.WOODEN_FRAME, 1));

        if (state.get(COVER) > 0) {
            drops.add(new ItemStack(DeepworldItems.WOODEN_CASE_PANEL, state.get(COVER)));
        }

        if (state.get(PRIMARY_COMPONENT) > 0) {
            drops.add(new ItemStack(WoodenFrameComponent.getByID(state.get(PRIMARY_COMPONENT), ComponentClass.PRIMARY).getItem(), 1));
        }

        if (state.get(SECONDARY_COMPONENT) > 0) {
            drops.add(new ItemStack(WoodenFrameComponent.getByID(state.get(SECONDARY_COMPONENT), ComponentClass.SECONDARY).getItem(), 1));
        }

        if (state.get(TERTIARY_COMPONENT) > 0) {
            drops.add(new ItemStack(WoodenFrameComponent.getByID(state.get(TERTIARY_COMPONENT), ComponentClass.TERTIARY).getItem(), 1));
        }

        return drops;
    }
}
