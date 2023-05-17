/*
 *       ____           _       __    __     _____ __            ___
 *      / __ \_      __(_)___ _/ /_  / /_   / ___// /___  ______/ (_)___
 *     / / / / | /| / / / __ `/ __ \/ __/   \__ \/ __/ / / / __  / / __ \
 *    / /_/ /| |/ |/ / / /_/ / / / / /_    ___/ / /_/ /_/ / /_/ / / /_/ /
 *   /_____/ |__/|__/_/\__, /_/ /_/\__/   /____/\__/\__,_/\__,_/_/\____/
 *                    /____/
 *   Copyright (c) 2022-2023 Dwight Studio's Team <support@dwight-studio.fr>
 *
 *   This Source Code From is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at https://mozilla.org/MPL/2.0/ .
 *
 */

package fr.dwightstudio.deepworld.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.dwightstudio.deepworld.common.blockentities.tanks.SimpleTankBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class FluidTankRenderer implements BlockEntityRenderer<SimpleTankBlockEntity> {

    private final BlockEntityRendererProvider.Context context;

    public FluidTankRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(@NotNull SimpleTankBlockEntity blockEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        if (!blockEntity.isEmpty()) {
            renderFluidInTank(poseStack, blockEntity.getFluid(), bufferSource, blockEntity.getFluidLevel(), blockEntity);

        }
    }

    private void renderFluidInTank(PoseStack poseStack, FluidStack fluidStack, MultiBufferSource bufferSource, float fluidLevel, SimpleTankBlockEntity blockEntity) {
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
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
        }

        BlockEntity above = blockEntity.getLevel().getBlockEntity(blockEntity.getBlockPos().above());

        if (fluidLevel < 1.0f || (above instanceof SimpleTankBlockEntity && ((SimpleTankBlockEntity) above).isEmpty())) {
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
