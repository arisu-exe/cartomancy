package io.github.fallOut015.cartomancy.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.cartomancy.client.renderer.entity.model.SigilModel;
import io.github.fallOut015.cartomancy.entity.effect.SigilEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SigilRenderer<T extends SigilEntity> extends EntityRenderer<T> {
    SigilModel<SigilEntity> model;

    public SigilRenderer(EntityRendererManager renderManager) {
        super(renderManager);

        this.model = new SigilModel<>();
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        IVertexBuilder vertexBuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entityIn), false));
        this.model.renderToBuffer(matrixStackIn, vertexBuilder, packedLightIn, 0, 1f, 1f, 1f, 1f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    } // TODO glow
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return new ResourceLocation("cartomancy", entity.getResource());
    }
}