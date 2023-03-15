package fr.dwightstudio.deepworld.blockentities.client;

import fr.dwightstudio.deepworld.block.machines.wood.WoodenLatheBlock;
import fr.dwightstudio.deepworld.blockentities.machines.wood.WoodenLatheBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Objects;

public class WoodenLatheBlockEntityRenderer implements BlockEntityRenderer<WoodenLatheBlockEntity> {

    public WoodenLatheBlockEntityRenderer(BlockEntityRendererFactory.Context context){

    }

    @Override
    public void render(WoodenLatheBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5f, 0.645f, 0.5f);
        matrices.scale(0.2f, 0.2f, 0.2f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotation((float) Math.toRadians(-90)));

        switch (entity.getCachedState().get(WoodenLatheBlock.FACING)) {
            case NORTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(180)));
            case EAST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(270)));
            case SOUTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(0)));
            case WEST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotation((float) Math.toRadians(90)));
        }

        itemRenderer.renderItem(itemStack, ModelTransformation.Mode.GUI, getLightLevel(Objects.requireNonNull(entity.getWorld()), entity.getPos()),
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
