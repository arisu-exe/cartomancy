package io.github.fallOut015.cartomancy.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.cartomancy.client.renderer.entity.model.CardModel;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.item.card.Cards;
import io.github.fallOut015.cartomancy.item.card.EntityCard;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardRenderer<T extends CardEntity> extends EntityRenderer<T> {
    private static final ResourceLocation CARD_TEXTURE = new ResourceLocation("cartomancy", "textures/item/card.png");
    CardModel<CardEntity> model;

    public CardRenderer(EntityRendererManager renderManager) {
        super(renderManager);

        this.model = new CardModel<>();
        this.shadowRadius = 0.25f;
    }

    // TODO fix choppy spin


    @Override
    public boolean shouldRender(T livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return livingEntityIn.getCardType().get() == Cards.THE_CHARIOT || super.shouldRender(livingEntityIn, camera, camX, camY, camZ);
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.scale(-0.5f, 1f, 0.5f);
        matrixStackIn.translate(0, -1.47d, 0);
        IVertexBuilder vertexBuilder;
        if(entityIn.isAttuned()) {
            vertexBuilder = ItemRenderer.getCompassFoilBuffer(bufferIn, RenderType.entityCutoutNoCull(this.getTextureLocation(entityIn)), matrixStackIn.last());
        } else {
            vertexBuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(entityIn), false));
        }
        this.model.setupAnim(entityIn, 0, 0, entityIn.tickCount, 0, 0);
        this.model.renderToBuffer(matrixStackIn, vertexBuilder, packedLightIn, 0, 1f, 1f, 1f, 1f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();

        ((EntityCard) entityIn.getCardType().get()).render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        try {
            return entity.getCardType().get().getTexture();
        } catch (Exception exception) {
            return CARD_TEXTURE;
        }
    }
}
