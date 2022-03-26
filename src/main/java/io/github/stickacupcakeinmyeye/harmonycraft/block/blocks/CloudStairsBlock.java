package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CloudStairsBlock extends StairsBlock implements ICloudBlock {
	public CloudStairsBlock(BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
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
