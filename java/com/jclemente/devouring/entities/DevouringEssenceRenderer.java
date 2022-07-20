package com.jclemente.devouring.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.jclemente.devouring.Main;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class DevouringEssenceRenderer extends EntityRenderer<DevouringEssenceEntity> {
	private final ModelRenderer cube;
	private final ModelRenderer core;
	private final ModelRenderer core2;
	private final ModelRenderer core3;
	private int progress;
	private int goal;
	private static final ResourceLocation DEOVURING_ESSENCE_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/entities/devouring_essence_entity.png");
	private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(DEOVURING_ESSENCE_TEXTURE);

	enum Colors {
		OUTER(110.0F / 255.0F, 255.0F / 255.0F, 90.0F / 255.0F), INNER(170.0F / 255.0F, 255.0F / 255.0F, 255.0F / 255.0F);

		public final float r, g, b;

		Colors(float r, float g, float b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}

	public DevouringEssenceRenderer(EntityRendererManager p_i46179_1_) {
		super(p_i46179_1_);
		this.shadowRadius = 0.0F;
		this.cube = new ModelRenderer(4, 4, 0, 0);
		this.cube.addBox(-2.0F, 1.5F, -2.0F, 4.0F, 4.0F, 4.0F);
		this.core = new ModelRenderer(4, 4, 0, 0);
		this.core.addBox(-2.00625F, 2.5F, -1.0F, 4.0125F, 2.0F, 2.0F);
		this.core2 = new ModelRenderer(4, 4, 0, 0);
		this.core2.addBox(-1.0F, 1.49375F, -1.0F, 2.0F, 4.0125F, 2.0F);
		this.core3 = new ModelRenderer(4, 4, 0, 0);
		this.core3.addBox(-1.0F, 2.5F, -2.00625F, 2.0F, 2.0F, 4.0125F);
	}

	@Override
	public ResourceLocation getTextureLocation(DevouringEssenceEntity p_110775_1_) {
		return DEOVURING_ESSENCE_TEXTURE;
	}

	@Override
	public boolean shouldRender(DevouringEssenceEntity p_225626_1_, ClippingHelper p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
		return super.shouldRender(p_225626_1_, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
	}

	@Override
	public void render(DevouringEssenceEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLight) {
		progress = entityIn.getProgress();
		goal = entityIn.getGoal();
		float percentageComplete = ((float) progress) / ((float) goal);
		int i = OverlayTexture.NO_OVERLAY;
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RENDER_TYPE);
		float funFloat = ((float) entityIn.innerRotation + partialTicks) * 3.0F;
		matrixStack.pushPose();
		matrixStack.translate(0.0D, Math.sin((entityIn.innerRotation + partialTicks) * 0.2D) * 0.1D, 0.0D);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(funFloat));
		float whiteBoost = 15.0F / 255.0F;
		this.cube.render(matrixStack, ivertexbuilder, packedLight, i, Colors.OUTER.r, Colors.OUTER.g , Colors.OUTER.b + (Colors.INNER.b - Colors.OUTER.b) * percentageComplete, 1.0F);
		this.core.render(matrixStack, ivertexbuilder, packedLight, i, Colors.INNER.r, Colors.INNER.g , Colors.OUTER.b + whiteBoost + (Colors.INNER.b - Colors.OUTER.b - whiteBoost) * percentageComplete, 1.0F);
		this.core2.render(matrixStack, ivertexbuilder, packedLight, i, Colors.INNER.r, Colors.INNER.g , Colors.OUTER.b + whiteBoost + (Colors.INNER.b - Colors.OUTER.b - whiteBoost) * percentageComplete, 1.0F);
		this.core3.render(matrixStack, ivertexbuilder, packedLight, i, Colors.INNER.r, Colors.INNER.g , Colors.OUTER.b + whiteBoost + (Colors.INNER.b - Colors.OUTER.b - whiteBoost) * percentageComplete, 1.0F);
		matrixStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStack, bufferIn, packedLight);
	}
}
