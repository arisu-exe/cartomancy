package io.github.fallOut015.cartomancy.item.card;

import io.github.fallOut015.cartomancy.entity.EntityTypeCartomancy;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.entity.projectile.DivineArrowEntity;
import net.minecraft.entity.monster.EvokerEntity;

public class JudgementCard extends EntityCard {
    JudgementCard(final String id, final float number) {
        super(id, number);
    }

    @Override
    public void onEntityActiveTick(CardEntity cardEntity) {
        if(!cardEntity.level.isClientSide) {
            for(int i = 0; i < 5; ++ i) {
                DivineArrowEntity divineArrow = EntityTypeCartomancy.DIVINE_ARROW.get().create(cardEntity.level);
                divineArrow.moveTo(cardEntity.position());
                divineArrow.setDeltaMovement(0, 0.75d, 0);
                divineArrow.setWarmupDelayTicks(i * 5);
                divineArrow.setCasterID(cardEntity.getCasterID().get());
                cardEntity.level.addFreshEntity(divineArrow);
            }

            // TODO do after last arrow disappears
            if(cardEntity.isActive()) {
                cardEntity.setActive(false);
            }
            cardEntity.setAttuned(false);
        }
    }
}