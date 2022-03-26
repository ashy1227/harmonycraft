package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
			Direction direction = Direction.DOWN;
			BlockPos blockPos = pos.offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
				world.addParticle(ParticleTypes.FALLING_WATER, pos.getX() + random.nextDouble(), pos.getY(), pos.getZ() + random.nextDouble(), 0.0d, 0.0d, 0.0d);
			}
		}
	}

	@Override
	public ParticleEffect getCloudParticle() {
		return HarmonyParticles.RAINCLOUD;
	}
}
