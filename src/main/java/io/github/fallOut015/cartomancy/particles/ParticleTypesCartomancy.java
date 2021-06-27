package io.github.fallOut015.cartomancy.particles;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleTypesCartomancy {
    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "cartomancy");



    public static final RegistryObject<BasicParticleType> SPARKLE = PARTICLE_TYPES.register("sparkle", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SPARKLE_ATTUNEMENT = PARTICLE_TYPES.register("sparkle_attunement", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SPARK = PARTICLE_TYPES.register("spark", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> SHINE = PARTICLE_TYPES.register("shine", () -> new BasicParticleType(true));



    public static void register(IEventBus bus) {
        PARTICLE_TYPES.register(bus);
    }
}
