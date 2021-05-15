package io.github.fallOut015.cartomancy.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SparkleAttunementParticle extends RisingParticle {
    private final IAnimatedSprite spriteWithAge;

    protected SparkleAttunementParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z, 0.05F, 0.1F, 0.05F, motionX, motionY, motionZ, scale, spriteWithAge, 1.0F, 8, 0.004D, false);
        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
        this.lifetime = 32;
        this.spriteWithAge = spriteWithAge;
    }

    @Override
    public void tick() {
        super.tick();

        float interpolation = (float) this.age / (float) this.lifetime;

        if(Math.floor(interpolation * 4f) % 2 == 0) {
            this.setSprite(this.spriteWithAge.get(0, 1));
        } else {
            this.setSprite(this.spriteWithAge.get(1, 1));
        }

        this.alpha = (1f - interpolation) * 1.0f;
        this.quadSize = (1f - interpolation) * 0.2f;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SparkleAttunementParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 0.75F, this.spriteSet);
        }
    }
}