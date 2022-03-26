package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CloudSlabBlock extends SlabBlock implements ICloudBlock {
	public CloudSlabBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		entity.handleFallDamage(fallDistance, 0.05f, DamageSource.FALL);
	}

	@Override
	public ParticleEffect getCloudParticle() {
		return HarmonyParticles.CLOUD;
	}
}
