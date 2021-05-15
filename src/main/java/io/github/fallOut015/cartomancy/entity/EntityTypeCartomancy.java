package io.github.fallOut015.cartomancy.entity;

import io.github.fallOut015.cartomancy.entity.effect.SigilEntity;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.entity.projectile.DivineArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeCartomancy {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, "cartomancy");



    public static final RegistryObject<EntityType<CardEntity>> CARD = ENTITIES.register("card", () -> EntityType.Builder.of(CardEntity::new, EntityClassification.MISC).sized(0.5f, 0.1f).noSummon().fireImmune().build("card"));
    public static final RegistryObject<EntityType<DivineArrowEntity>> DIVINE_ARROW = ENTITIES.register("divine_arrow", () -> EntityType.Builder.of(DivineArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).setTrackingRange(4)/*.updateInterval(20)*/.fireImmune().build("divine_arrow"));
    public static final RegistryObject<EntityType<SigilEntity>> SIGIL = ENTITIES.register("sigil", () -> EntityType.Builder.of(SigilEntity::new, EntityClassification.MISC).sized(4F, 0.1F).fireImmune().build("sigil"));



    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
