package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.Block;
import net.minecraft.particle.ParticleEffect;

// TODO integrate with MineLP so only pegasus ponies can stand on clouds
public class CloudBlock extends Block {
	public CloudBlock(Settings settings) {
		super(settings);
	}

	public ParticleEffect getCloudParticle() {
		return HarmonyParticles.CLOUD;
	}
}
