package io.github.fallOut015.cartomancy.item.card;

import io.github.fallOut015.cartomancy.entity.EntityTypeCartomancy;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.particles.ParticleTypesCartomancy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

import java.util.List;

public class TheTowerCard extends EntityCard {
    TheTowerCard(final String id, final float number) {
        super(id, number);
    }

    @Override
    public void onEntityActiveTick(CardEntity cardEntity) {
        List<Entity> entities = cardEntity.level.getEntities(cardEntity, cardEntity.getBoundingBox(), entity -> entity.getType() != EntityTypeCartomancy.CARD.get() && entity != cardEntity.getServerCaster() && entity.getType() != EntityType.ITEM);
        if (!entities.isEmpty()) {
            if(!cardEntity.level.isClientSide) {
                LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(cardEntity.level);
                lightningBoltEntity.moveTo(cardEntity.position());
                cardEntity.level.addFreshEntity(lightningBoltEntity);
                if(cardEntity.isActive()) {
                    cardEntity.setActive(false);
                }
                cardEntity.setAttuned(false);
            }
        }
        if (cardEntity.getActiveTime() >= 500) {
            if(!cardEntity.level.isClientSide) {
                LightningBoltEntity lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(cardEntity.level);
                lightningBoltEntity.moveTo(cardEntity.position());
                cardEntity.level.addFreshEntity(lightningBoltEntity);
                if(cardEntity.isActive()) {
                    cardEntity.setActive(false);
                }
                cardEntity.setAttuned(false);
            }
        } else if (cardEntity.getActiveTime() % 100 == 0) {
            cardEntity.level.playSound((PlayerEntity) null, cardEntity.getX(), cardEntity.getY(), cardEntity.getZ(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, 2000f * cardEntity.getActiveTime(), 0.8F + cardEntity.getRand().nextFloat() * 0.2F);
            cardEntity.level.setSkyFlashTime(2);
            for (int i = 0; i < 8; ++i) {
                cardEntity.level.addParticle(ParticleTypesCartomancy.SPARK.get(), cardEntity.getX(), cardEntity.getY(), cardEntity.getZ(), (cardEntity.getRand().nextFloat() - 0.5f) / 16f, (cardEntity.getRand().nextFloat() - 0.5f) / 32f, (cardEntity.getRand().nextFloat() - 0.5f) / 16f);
            }
        }
    }
}