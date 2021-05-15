package io.github.fallOut015.cartomancy.item.card;

import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.util.math.MathHelperCartomancy;
import net.minecraft.world.server.ServerWorld;

public class TheSunCard extends EntityCard {
    TheSunCard(final String id, final float number) {
        super(id, number);
    }

    @Override
    public void onEntityActiveTick(CardEntity cardEntity) {
        if(!cardEntity.level.isClientSide) {
            ServerWorld world = ((ServerWorld) cardEntity.level);



            long time = MathHelperCartomancy.lerp(0.5d, world.getDayTime(), 6000L);
            for(int i = 0; i <= Math.abs(time); ++ i) {
                world.setDayTime(world.getDayTime() + time / Math.abs(time));
                //world.setDayTime(i);
                // send packet?
                //Minecraft.getInstance().worldRenderer.renderSky(matrixstack, 0);
            }
        }
    }
}