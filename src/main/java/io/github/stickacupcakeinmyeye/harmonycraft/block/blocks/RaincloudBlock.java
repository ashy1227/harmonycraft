package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class RaincloudBlock extends CloudBlock {
	public RaincloudBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(2) == 0) {
			if (world.getBlockState(pos.down()).isAir()) {
				world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + random.nextDouble(), pos.getY(), pos.getZ() + random.nextDouble(), 0.0d, 0.0d, 0.0d);
			}
		}
	}
}
