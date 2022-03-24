package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class StormcloudBlock extends RaincloudBlock {
	public StormcloudBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		super.onLandedUpon(world, state, pos, entity, fallDistance);
		if(fallDistance > 2) {
			LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
			lightning.refreshPositionAfterTeleport(entity.getPos());
			world.spawnEntity(lightning);
		}
	}
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(2) == 0) {
			Direction direction = Direction.random(random);
			BlockPos blockPos = pos.offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
				double xOffset = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetX() * 0.6d;
				double yOffset = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetY() * 0.6d;
				double zOffset = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetZ() * 0.6d;
				world.addParticle(HarmonyParticles.ZAP, pos.getX() + xOffset, pos.getY() + yOffset, pos.getZ() + zOffset, 0.0d, 0.0d, 0.0d);
			}
		}
	}
}
