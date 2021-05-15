package io.github.fallOut015.cartomancy.client.renderer.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CardModel<T extends CardEntity> extends EntityModel<T> {
    ModelRenderer card;

    public CardModel() {
        super();

        this.card = new ModelRenderer(this);
        this.card.setPos(0, 24, 0);
        this.card.setTexSize(16, 16);
        this.card.texOffs(-16, 0).addBox(-8f, 0, -8f, 16f, 0.0625f, 16f, 0f, false);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.card.yRot = entityIn.yRot;
    }
    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.card.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
