package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.DeepworldItems;
import fr.dwightstudio.deepworld.common.tile.TileEntityWoodenPress;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.ArrayList;
import java.util.List;

public class BlockWoodenPress extends ContainerBlock implements ITileEntityProvider {

    // Block property initializing
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final IProperty<Boolean> WORKING = BooleanProperty.create("working");

    // Block state creation (registering properties)
    @Override
    final protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(WORKING);
    }

    // Constructor
    public BlockWoodenPress() {
        super(Properties.create(Material.WOOD)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(3, 2)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE));

        this.setDefaultState(this.getStateContainer().getBaseState()
                .with(FACING, Direction.NORTH)
                .with(WORKING, false)
        );
    }

    // Assign the TileEntity
    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new TileEntityWoodenPress();
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(WORKING) ? 10 : 0;
    }

    // Drop all contents
    @Override
    public void onReplaced(BlockState state, World world, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = world.getTileEntity(blockPos);
            if (tileentity instanceof TileEntityWoodenPress) {
                TileEntityWoodenPress tileEntityFurnace = (TileEntityWoodenPress)tileentity;
                tileEntityFurnace.dropAllContents(world, blockPos);
            }
            super.onReplaced(state, world, blockPos, newState, isMoving);  // call it last, because it removes the TileEntity
        }
    }

    // Setting customs drops
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        List<ItemStack> drops = new ArrayList<ItemStack>();

        drops.add(new ItemStack(DeepworldItems.WOODEN_CRANK, 1));
        drops.add(new ItemStack(DeepworldItems.SIMPLE_PRESSING_CHAMBER, 1));
        drops.add(new ItemStack(DeepworldItems.WOODEN_GEARBOX, 1));
        drops.add(new ItemStack(Items.STICK, 4));
        drops.add(new ItemStack(Blocks.OAK_PLANKS, 4));
        drops.add(new ItemStack(DeepworldItems.WOODEN_CASE_PANEL, 6));


        return drops;
    }

    // Open gui
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (worldIn.isRemote) return ActionResultType.SUCCESS; // on client side, don't do anything

        INamedContainerProvider namedContainerProvider = this.getContainer(state, worldIn, pos);
        if (namedContainerProvider != null) {

            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            NetworkHooks.openGui(serverPlayerEntity, namedContainerProvider, (packetBuffer)->{});
        }
        return ActionResultType.SUCCESS;
    }
}
