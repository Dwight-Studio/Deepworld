package fr.dwightstudio.deepworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import fr.dwightstudio.deepworld.common.Deepworld;
import fr.dwightstudio.deepworld.common.blockentities.tanks.SimpleTankBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

public class FluidTankRenderer implements BlockEntityRenderer<SimpleTankBlockEntity> {

    private final BlockEntityRendererProvider.Context context;

    public FluidTankRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(@NotNull SimpleTankBlockEntity blockEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        if (!blockEntity.isEmpty()) {
            renderFluidInTank(poseStack, blockEntity.getFluid(), bufferSource, blockEntity.getFluidLevel());

        }
    }

    private void renderFluidInTank(PoseStack poseStack, FluidStack fluidStack, MultiBufferSource bufferSource, float fluidLevel) {
        poseStack.pushPose();
        poseStack.translate(0.5d, 0.5d, 0.5d);

        IClientFluidTypeExtensions attributes = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(attributes.getStillTexture(fluidStack));
        Matrix4f matrix4f = poseStack.last().pose();
        Matrix3f matrix3f = poseStack.last().normal();

        int color = attributes.getTintColor(fluidStack);

        VertexConsumer builder = bufferSource.getBuffer(RenderType.translucent());

        for (int i = 0; i < 4; i++) {
            this.renderSideFace(sprite, matrix4f, matrix3f, builder, color, fluidLevel);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        }

        if (fluidLevel < 1.0f) {
            this.renderTopFace(sprite, matrix4f, matrix3f, builder, color, fluidLevel);
        }

        poseStack.popPose();
    }

    private void renderSideFace(TextureAtlasSprite sprite, Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer builder, int color, float fluidLevel) {
        final float WIDTH = 8 / 16f;
        final float HEIGHT = 16 / 16f;

        final float MIN_U = sprite.getU(16f / 2 - 4 + 1); // 16 = Total length | 4 = Half display length
        final float MAX_U = sprite.getU(16f / 2 + 4 + 1);
        final float MIN_V = sprite.getV(1);
        final float MAX_V = sprite.getV(15 * fluidLevel);

        builder.vertex(matrix4f, -WIDTH / 2, -HEIGHT / 2 + HEIGHT * fluidLevel, (-WIDTH / 2) + 0.001f).color(color)
                .uv(MIN_U, MIN_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 0, 1)
                .endVertex();

        builder.vertex(matrix4f, WIDTH / 2, -HEIGHT / 2 + HEIGHT * fluidLevel, (-WIDTH / 2) + 0.001f).color(color)
                .uv(MAX_U, MIN_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 0, 1)
                .endVertex();

        builder.vertex(matrix4f, WIDTH / 2, -HEIGHT / 2, (-WIDTH / 2) + 0.001f).color(color)
                .uv(MAX_U, MAX_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 0, 1)
                .endVertex();

        builder.vertex(matrix4f, -WIDTH / 2, -HEIGHT / 2, (-WIDTH / 2) + 0.001f).color(color)
                .uv(MIN_U, MAX_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 0, 1)
                .endVertex();
    }

    private void renderTopFace(TextureAtlasSprite sprite, Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer builder, int color, float fluidLevel) {
        final float WIDTH = 8 / 16f;
        final float HEIGHT = 16 / 16f;

        final float MIN_U = sprite.getU(16f / 2 - 4 + 1); // 16 = Total length | 4 = Half display length
        final float MAX_U = sprite.getU(16f / 2 + 4 + 1);
        final float MIN_V = sprite.getV(16f / 2 - 4 + 1);
        final float MAX_V = sprite.getV(16f / 2 + 4 + 1);

        builder.vertex(matrix4f, -WIDTH / 2, -HEIGHT / 2 + fluidLevel * HEIGHT, -WIDTH / 2).color(color)
                .uv(MIN_U, MIN_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 1, 0)
                .endVertex();

        builder.vertex(matrix4f, -WIDTH / 2, -HEIGHT / 2 + fluidLevel * HEIGHT, WIDTH / 2).color(color)
                .uv(MIN_U, MAX_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 1, 0)
                .endVertex();

        builder.vertex(matrix4f, WIDTH / 2, -HEIGHT / 2 + fluidLevel * HEIGHT, WIDTH / 2).color(color)
                .uv(MAX_U, MAX_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 1, 0)
                .endVertex();

        builder.vertex(matrix4f, WIDTH / 2, -HEIGHT / 2 + fluidLevel * HEIGHT, -WIDTH / 2).color(color)
                .uv(MAX_U, MIN_V)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0, 1, 0)
                .endVertex();
    }
}
