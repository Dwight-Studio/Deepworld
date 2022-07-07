package fr.dwightstudio.deepworld.common.block;

import fr.dwightstudio.deepworld.common.block.component.ComponentClass;
import fr.dwightstudio.deepworld.common.block.component.WoodenFrameComponent;
import fr.dwightstudio.deepworld.common.blockentity.WoodenFrameBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

public class WoodenFrameBlock extends FrameBlock implements EntityBlock {

    public static final Property<Integer> PRIMARY_COMPONENT = IntegerProperty.create("primary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.PRIMARY));
    public static final Property<Integer> SECONDARY_COMPONENT = IntegerProperty.create("secondary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.SECONDARY));
    public static final Property<Integer> TERTIARY_COMPONENT = IntegerProperty.create("tertiary_component", 0, WoodenFrameComponent.getLastID(ComponentClass.TERTIARY));

    public WoodenFrameBlock() {
        super(Properties.of(Material.WOOD)
                .sound(SoundType.WOOD)
                .strength(3, 2));
    }

    // Adding default custom properties value
    @Override
    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state
                .setValue(PRIMARY_COMPONENT, 0)
                .setValue(SECONDARY_COMPONENT, 0)
                .setValue(TERTIARY_COMPONENT, 0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WoodenFrameBlockEntity(blockPos, blockState);
    }
}