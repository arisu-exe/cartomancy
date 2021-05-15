package io.github.fallOut015.cartomancy.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SparkParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteWithAge;

    protected SparkParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.spriteWithAge = spriteWithAge;
        this.xd += motionX;
        this.yd += motionY;
        this.zd += motionZ;
        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
        this.quadSize = scale;
        this.lifetime = 32;
        this.setSpriteFromAge(spriteWithAge);
        this.hasPhysics = true;
    }

    @Override
    public void tick() {
        super.tick();

        this.xd *= 0.95;

        float interpolation = (float) this.age / (float) this.lifetime;

        if(Math.floor(interpolation * 4f) % 2 == 0) {
            this.setSprite(this.spriteWithAge.get(0, 1));
        } else {
            this.setSprite(this.spriteWithAge.get(1, 1));
        }

        this.alpha = (1f - interpolation) * 1.0f;
        this.quadSize = (1f - interpolation) * 0.1f;
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
            return new SparkParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 0.1F, this.spriteSet);
        }
    }
}