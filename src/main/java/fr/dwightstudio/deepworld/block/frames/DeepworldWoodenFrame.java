package fr.dwightstudio.deepworld.block.frames;

import fr.dwightstudio.deepworld.blockentities.frames.WoodenFrameBlockEntity;
import fr.dwightstudio.deepworld.components.ComponentClass;
import fr.dwightstudio.deepworld.components.DeepworldWoodenFrameComponent;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class DeepworldWoodenFrame extends DeepworldFrame implements BlockEntityProvider {

    public static final Property<Integer> PRIMARY_COMPONENT = IntProperty.of("primary_component", 0, DeepworldWoodenFrameComponent.getLastID(ComponentClass.PRIMARY));
    public static final Property<Integer> SECONDARY_COMPONENT = IntProperty.of("secondary_component", 0, DeepworldWoodenFrameComponent.getLastID(ComponentClass.SECONDARY));
    public static final Property<Integer> TERTIARY_COMPONENT = IntProperty.of("tertiary_component", 0, DeepworldWoodenFrameComponent.getLastID(ComponentClass.TERTIARY));

    public DeepworldWoodenFrame() {
        super(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(3.0f, 2.0f).nonOpaque());
    }

    // Adding default custom properties value
    @Override
    protected BlockState setComponentPropertiesDefaultValues(BlockState state) {
        return state
                .with(PRIMARY_COMPONENT, 0)
                .with(SECONDARY_COMPONENT, 0)
                .with(TERTIARY_COMPONENT, 0);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState){
        return new WoodenFrameBlockEntity(blockPos, blockState);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PRIMARY_COMPONENT, SECONDARY_COMPONENT, TERTIARY_COMPONENT);
    }
}
