package net.kzeroko.ktmresource.client.particles;

import java.util.Random;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BladeNormParticle extends HitParticle {
    public BladeNormParticle(ClientLevel world, double x, double y, double z, SpriteSet animatedSprite) {
        super(world, x, y, z, animatedSprite);
        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
        this.quadSize = 1.0F;
        this.lifetime = 4;

        Random rand = new Random();
        float angle = (float)Math.toRadians(rand.nextFloat() * 90.0F);
        this.oRoll = angle;
        this.roll = angle;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            BladeNormParticle particle = new BladeNormParticle(worldIn, x, y, z, spriteSet);
            return particle;
        }
    }
}
