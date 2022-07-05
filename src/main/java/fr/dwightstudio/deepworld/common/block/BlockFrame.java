package fr.dwightstudio.deepworld.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockFrame extends Block {

    // Block property initializing
    public static final DirectionProperty FACING =  HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty COVER = IntegerProperty.create("cover", 0, 6);

    // Constructor
    public BlockFrame(Block.Properties properties) {
        super(properties);

        this.setDefaultState(setComponentPropertiesDefaultValues(this.getStateContainer().getBaseState()
                        .with(FACING, Direction.NORTH)
                        .with(COVER, 0)
        ));
    }

    // Override this method to add new properties
    protected Property<?>[] getComponentsProperties() {
        return new Property[] {};
    }

    // Override this method to add default properties values
    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state;
    }



    // Block state creation (registering properties)
    @Override
    final protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        List<Property<?>> list = new ArrayList<Property<?>>();

        list.add(FACING);
        list.add(COVER);

        Collections.addAll(list, getComponentsProperties());

        builder.add(list.toArray(new IProperty[0]));
    }

    // Notify block update when activated
    @Override
    final public InteractionResult onBlockActivated(BlockState state, Level world, BlockPos pos, Player player, Hand hand, BlockRayTraceResult rayTraceResult) {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), state, Constants.BlockFlags.DEFAULT);
        return ActionResultType.PASS;
    }



    // Enable directional placement
    @Override
    final public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }


}
