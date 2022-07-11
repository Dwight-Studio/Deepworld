package fr.dwightstudio.deepworld.common.blockentities.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.dwightstudio.deepworld.common.blockentities.tanks.SimpleTankBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public class FluidTankRenderer implements BlockEntityRenderer<SimpleTankBlockEntity> {

    private final BlockEntityRendererProvider.Context context;

    public FluidTankRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(@NotNull SimpleTankBlockEntity blockEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {

        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        FluidState fluidState = blockEntity.getFluid().copy().getRawFluid().defaultFluidState();
        BlockState blockState = blockEntity.getFluid().copy().getFluid().defaultFluidState().createLegacyBlock();

        /*LogManager.getLogger().log(Level.DEBUG, fluidState);
        LogManager.getLogger().log(Level.DEBUG, Fluids.WATER.defaultFluidState());*/

        poseStack.pushPose();
        poseStack.translate(0.25f, 0, 0.25F);
        poseStack.scale(0.5f, 1.3f, 0.5f);
        dispatcher.renderLiquid(blockEntity.getBlockPos(), blockEntity.getLevel(), new LiquidBlockVertexConsumer(bufferSource.getBuffer(ItemBlockRenderTypes.getRenderLayer(Fluids.WATER.defaultFluidState())), poseStack, blockEntity.getBlockPos()), Blocks.WATER.defaultBlockState(), Fluids.WATER.defaultFluidState());

        poseStack.popPose();
    }
}
