package fr.dwightstudio.deepworld.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockFrame extends Block {

    // Block property initializing
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final IProperty<Boolean> BOTTOM = BooleanProperty.create("bottom");
    public static final IProperty<Boolean> TOP = BooleanProperty.create("top");
    public static final IProperty<Boolean> FRONT = BooleanProperty.create("front");
    public static final IProperty<Boolean> BACK = BooleanProperty.create("back");
    public static final IProperty<Boolean> LEFT = BooleanProperty.create("left");
    public static final IProperty<Boolean> RIGHT = BooleanProperty.create("right");

    // Constructor
    public BlockFrame(Block.Properties properties) {
        super(properties);

        this.setDefaultState(setComponentPropertiesDefaultValues(this.getStateContainer().getBaseState()
                        .with(FACING, Direction.NORTH)
                        .with(BOTTOM, false)
                        .with(TOP, false)
                        .with(FRONT, false)
                        .with(BACK, false)
                        .with(LEFT, false)
                        .with(RIGHT, false)
        ));
    }

    // Override this method to add new properties
    protected IProperty<?>[] getComponentsProperties() {
        return new IProperty[] {};
    }

    // Override this method to add default properties values
    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state;
    }

    // Block state creation (registering properties)
    @Override
    final protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        List<IProperty<?>> list = new ArrayList<IProperty<?>>();

        list.add(FACING);
        list.add(BOTTOM);
        list.add(TOP);
        list.add(FRONT);
        list.add(BACK);
        list.add(LEFT);
        list.add(RIGHT);

        Collections.addAll(list, getComponentsProperties());

        builder.add(list.toArray(new IProperty[0]));
    }

    // Notify block update when activated
    @Override
    final public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), state, Constants.BlockFlags.DEFAULT);
        return ActionResultType.PASS;
    }



    // Enable directional placement
    @Override
    final public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }


}
