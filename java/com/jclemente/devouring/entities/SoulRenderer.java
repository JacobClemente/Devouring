package com.jclemente.devouring.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.jclemente.devouring.Main;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class SoulRenderer extends EntityRenderer<SoulEntity> {

	private static final ResourceLocation SOUL_TEXTURE = new ResourceLocation(Main.MOD_ID,
			"textures/entities/soul_entity.png");
	private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(SOUL_TEXTURE);

	public SoulRenderer(EntityRendererManager p_i46179_1_) {
		super(p_i46179_1_);
		this.shadowRadius = 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(SoulEntity p_110775_1_) {
		return SOUL_TEXTURE;
	}

	@Override
	public void render(SoulEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		float f = 0.0F;
		float f1 = 1F;
		float f2 = 0.0F;
		float f3 = 1F;
		matrixStackIn.translate(0.0D, (double) 0.1F, 0.0D);
		matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		matrixStackIn.scale(0.3F, 0.3F, 0.3F);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RENDER_TYPE);
		MatrixStack.Entry matrixstackEntry = matrixStackIn.last();
		Matrix4f matrix4f = matrixstackEntry.pose();
		Matrix3f matrix3f = matrixstackEntry.normal();
		vertex(ivertexbuilder, matrix4f, matrix3f, -0.5F, -0.25F, 255, 255, 255, f, f3, packedLightIn);
		vertex(ivertexbuilder, matrix4f, matrix3f, 0.5F, -0.25F, 255, 255, 255, f1, f3, packedLightIn);
		vertex(ivertexbuilder, matrix4f, matrix3f, 0.5F, 0.75F, 255, 255, 255, f1, f2, packedLightIn);
		vertex(ivertexbuilder, matrix4f, matrix3f, -0.5F, 0.75F, 255, 255, 255, f, f2, packedLightIn);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, 15728880);
	}

	private static void vertex(IVertexBuilder bufferIn, Matrix4f matrixIn, Matrix3f matrixNormalIn, float x, float y,
			int red, int green, int blue, float texU, float texV, int packedLight) {
		bufferIn.vertex(matrixIn, x, y, 0.0F).color(red, green, blue, 255).uv(texU, texV)
				.overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrixNormalIn, 0.0F, 1.0F, 0.0F)
				.endVertex();

	}

}
