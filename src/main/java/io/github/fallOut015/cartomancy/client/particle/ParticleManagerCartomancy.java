package io.github.fallOut015.cartomancy.client.particle;

import io.github.fallOut015.cartomancy.particles.ParticleTypesCartomancy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;

public class ParticleManagerCartomancy {
    public static void onParticleFactoryRegistry(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleTypesCartomancy.SPARKLE.get(), SparkleParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleTypesCartomancy.SPARKLE_ATTUNEMENT.get(), SparkleAttunementParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleTypesCartomancy.SPARK.get(), SparkParticle.Factory::new);
    }
}