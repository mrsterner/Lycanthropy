package net.zestyblaze.lycanthropy.client.renderer;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.zestyblaze.lycanthropy.Lycanthropy;
import net.zestyblaze.lycanthropy.client.model.BearTrapBlockModel;
import net.zestyblaze.lycanthropy.common.block.blockentity.BearTrapBlockEntity;
import net.zestyblaze.lycanthropy.common.utils.LycanthropyUtils;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;


public class BearTrapBlockRenderer extends GeoBlockRenderer<BearTrapBlockEntity> {
    private static final Identifier LAYER = new Identifier(Lycanthropy.MODID, "textures/block/leaves.png");
    private static final Identifier MODEL = new Identifier(Lycanthropy.MODID, "geo/leaves.geo.json");
    public BearTrapBlockRenderer() {
        super(new BearTrapBlockModel());
    }

    @Override
    public RenderLayer getRenderType(BearTrapBlockEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(BlockEntity tile, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        super.render(tile, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if(tile instanceof BearTrapBlockEntity bearTrapBlockEntity){
            int i = BiomeColors.getFoliageColor(tile.getWorld(), tile.getPos());
            matrixStackIn.push();
            matrixStackIn.translate(0.5F,0.25,0.5F);
            if(!bearTrapBlockEntity.getStack(0).isEmpty()){
                render(getGeoModelProvider().getModel(MODEL), bearTrapBlockEntity, partialTicks, RenderLayer.getEntityTranslucent(LAYER), matrixStackIn, bufferIn, bufferIn.getBuffer(RenderLayer.getEntityTranslucent(LAYER)), combinedLightIn, OverlayTexture.DEFAULT_UV,
                LycanthropyUtils.intToRGB(i).getX(),
                LycanthropyUtils.intToRGB(i).getY(),
                LycanthropyUtils.intToRGB(i).getZ(),
                1);
            }
            matrixStackIn.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));
            matrixStackIn.scale(0.5F,0.5F,0.5F);
            MinecraftClient.getInstance().getItemRenderer().renderItem(bearTrapBlockEntity.getStack(1), ModelTransformation.Mode.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, 0);
            matrixStackIn.pop();
        }
    }
}
