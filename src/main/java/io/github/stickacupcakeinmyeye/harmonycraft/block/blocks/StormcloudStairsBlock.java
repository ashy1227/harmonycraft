package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.Random;

public class StormcloudStairsBlock extends RaincloudStairsBlock {
	public StormcloudStairsBlock(BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
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

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(4) == 0) {
			Direction direction = Direction.random(random);
			BlockPos blockPos = pos.offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
				// yes this is technically not uniform but doing it in such a way would most likely be very painful and this is not very noticeable
				VoxelShape outlineShape = state.getOutlineShape(world, pos);
				Vec3d randomPosition = new Vec3d(
					direction.getOffsetX() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetX() * 0.6d,
					direction.getOffsetY() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetY() * 0.6d,
					direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetZ() * 0.6d
				);
				Vec3d position = outlineShape.getClosestPointTo(randomPosition).get();
				world.addParticle(HarmonyParticles.ZAP, pos.getX() + position.getX(), pos.getY() + position.getY(), pos.getZ() + position.getZ(), 0.0d, 0.0d, 0.0d);
			}
		}
		Direction direction = Direction.DOWN;
		BlockPos blockPos = pos.offset(direction);
		BlockState blockState = world.getBlockState(blockPos);
		if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
			// yes this is technically not uniform but doing it in such a way would most likely be very painful and this is not very noticeable
			VoxelShape outlineShape = state.getOutlineShape(world, pos);
			Vec3d randomPosition = new Vec3d(
				direction.getOffsetX() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetX() * 0.6d,
				direction.getOffsetY() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetY() * 0.6d,
				direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5d + direction.getOffsetZ() * 0.6d
			);
			Vec3d position = outlineShape.getClosestPointTo(randomPosition).get();
			world.addParticle(ParticleTypes.FALLING_WATER, pos.getX() + position.getX(), pos.getY() + position.getY(), pos.getZ() + position.getZ(), 0.0d, 0.0d, 0.0d);
		}
	}

	@Override
	public ParticleEffect getCloudParticle() {
		return HarmonyParticles.STORMCLOUD;
	}
}
