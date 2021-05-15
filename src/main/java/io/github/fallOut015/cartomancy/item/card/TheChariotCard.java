package io.github.fallOut015.cartomancy.item.card;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;

public class TheChariotCard extends EntityCard {
    TheChariotCard(final String id, final float number) {
        super(id, number);
    }

    @Override
    public void onEntityActivate(CardEntity cardEntity) {
    }
    @Override
    public void onEntityDeactivate(CardEntity cardEntity) {
    }

    @Override
    public void onEntityActiveTick(CardEntity cardEntity) {
    }

    @Override
    public void render(CardEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        /*if(entityIn.isActive()) {
            long i = entityIn.getEntityWorld().getGameTime();
            List<BeaconTileEntity.BeamSegment> list = new LinkedList<>();
            float[] afloat = new float[] { 0.749019608f, 0.776470588f, 0.298039216f };
            list.add(new BeaconTileEntity.BeamSegment(afloat));
            int j = 0;

            matrixStackIn.push();
            for(int k = 0; k < list.size(); ++k) {
                BeaconTileEntity.BeamSegment beacontileentity$beamsegment = list.get(k);
                matrixStackIn.translate(-0.5f, 0f, -0.5f);
                BeaconTileEntityRenderer.renderBeamSegment(matrixStackIn, bufferIn, BeaconTileEntityRenderer.TEXTURE_BEACON_BEAM, partialTicks, 1.0f, i, j, k == list.size() - 1 ? 1024 : beacontileentity$beamsegment.getHeight(), beacontileentity$beamsegment.getColors(), 0.1f, 0.2f);
                j += beacontileentity$beamsegment.getHeight();
            }
            matrixStackIn.pop();
            // TODO copy code for beacon tile entity rendering and override alpha channel
        }*/
    }
}