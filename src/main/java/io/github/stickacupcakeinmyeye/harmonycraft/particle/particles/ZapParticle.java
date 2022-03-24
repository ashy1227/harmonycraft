package io.github.stickacupcakeinmyeye.harmonycraft.particle.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class ZapParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;

	ZapParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, velX, velY, velZ);

		this.velocityX = velX;
		this.velocityY = velY;
		this.velocityZ = velZ;

		this.maxAge = 5;
		this.spriteProvider = spriteProvider;

		this.scale(1.0f + random.nextFloat());
		this.setSpriteForAge(spriteProvider);
	}

	@Override
	public void tick() {
		super.tick();
		this.setSpriteForAge(this.spriteProvider);
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	}

	@Environment(value=EnvType.CLIENT)
	public static class Factory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ZapParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, spriteProvider);
		}
	}
}
