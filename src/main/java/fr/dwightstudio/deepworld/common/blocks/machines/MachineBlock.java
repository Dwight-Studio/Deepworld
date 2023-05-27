package fr.dwightstudio.deepworld.common.blocks.machines;

import fr.dwightstudio.deepworld.common.blocks.frames.FrameBlock;
import fr.dwightstudio.deepworld.common.components.FrameComponent;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public abstract class MachineBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected MachineBlock(Properties properties) {
        super(properties);
    }

    public abstract FrameBlock getFrame();

    public abstract FrameComponent[] getComponents();
}
