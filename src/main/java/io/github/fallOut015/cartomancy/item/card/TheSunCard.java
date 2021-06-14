package io.github.fallOut015.cartomancy.item.card;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.fallOut015.cartomancy.MainCartomancy;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.server.PacketHandler;
import io.github.fallOut015.cartomancy.server.SetDayTimePacketHandler;
import io.github.fallOut015.cartomancy.util.math.MathHelperCartomancy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.PacketDistributor;

public class TheSunCard extends EntityCard {
    TheSunCard(final String id, final float number) {
        super(id, number);
    }

    @Override
    public void onEntityActiveTick(CardEntity cardEntity) {

    }
    @Override
    public void render(CardEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if(entityIn.isActive()) {
            final ClientWorld level = (ClientWorld) entityIn.level;
            long time = MathHelperCartomancy.lerp(0.5d, level.getDayTime(), 6000L);
            PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new SetDayTimePacketHandler(time));
            level.setDayTime(time);
            if(Math.abs(time - 6000L) < 2) {
                entityIn.setActive(false);
            }
        }
    }
}