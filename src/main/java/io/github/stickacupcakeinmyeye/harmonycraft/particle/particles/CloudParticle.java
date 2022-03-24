package io.github.stickacupcakeinmyeye.harmonycraft.particle.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class CloudParticle extends AscendingParticle {
	CloudParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
		super(world, x, y, z, 0.1f, 0.1f, 0.1f, velX, velY, velZ, 3.0f, spriteProvider, 1.0f, 4, -0.1f, false);
		this.red = 1.0f;
		this.green = 1.0f;
		this.blue = 1.0f;
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
			return new CloudParticle(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed, spriteProvider);
		}
	}
}