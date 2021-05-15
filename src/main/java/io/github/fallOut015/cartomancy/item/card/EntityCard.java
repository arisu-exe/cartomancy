package io.github.fallOut015.cartomancy.item.card;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;

public class EntityCard extends Card {
    EntityCard(final String id, final float number) {
        super(id, number);
    }
    EntityCard(final String id, final float number, final int rank, final Suit suit) {
        super(id, number, rank, suit);
    }

    public void onEntityActivate(CardEntity cardEntity) {

    }
    public void onEntityDeactivate(CardEntity cardEntity) {

    }

    public void onEntityActiveTick(CardEntity cardEntity) {

    }
    public void render(CardEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

    }
}