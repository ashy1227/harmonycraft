package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;

import java.util.Random;

public class RaincloudSlabBlock extends CloudSlabBlock {
	public RaincloudSlabBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		return ActionResult.FAIL;
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(2) == 0) {
			Direction direction = Direction.DOWN;
			BlockPos blockPos = pos.offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
				VoxelShape outlineShape = state.getOutlineShape(world, pos);

				double x = random.nextDouble();
				double z = random.nextDouble();

				final double[] y = {1.0d};
				outlineShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
					if(x >= minX && x <= maxX && z >= minZ && z <= maxZ)
						if(minY < y[0])
							y[0] = minY;
				});

				world.addParticle(ParticleTypes.FALLING_WATER, pos.getX() + x, pos.getY() + y[0], pos.getZ() + z, 0.0d, 0.0d, 0.0d);
			}
		}
	}

	@Override
	public ParticleEffect getCloudParticle() {
		return HarmonyParticles.RAINCLOUD;
	}
}
