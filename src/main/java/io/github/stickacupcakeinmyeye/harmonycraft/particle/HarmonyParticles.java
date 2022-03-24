package io.github.stickacupcakeinmyeye.harmonycraft.particle;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.particle.particles.ZapParticle;
import io.github.stickacupcakeinmyeye.harmonycraft.particle.particles.CloudParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonyParticles {
	public static final DefaultParticleType ZAP = FabricParticleTypes.simple();
	public static final DefaultParticleType CLOUD = FabricParticleTypes.simple();

	public static void registerAll() {
		registerParticle("zap", ZAP);
		registerParticle("cloud", CLOUD);
	}
	public static void registerClient() {
		registerParticleClient(ZAP, ZapParticle.Factory::new);
		registerParticleClient(CLOUD, CloudParticle.Factory::new);
	}

	protected static <T extends ParticleEffect> void registerParticle(String ID, ParticleType<T> particleType) {
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(HarmonyCraft.MODID, ID), particleType);
	}

	protected static <T extends ParticleEffect> void registerParticleClient(ParticleType<T> particle, ParticleFactoryRegistry.PendingParticleFactory<T> constructor) {
		ParticleFactoryRegistry.getInstance().register(particle, constructor);
	}
}