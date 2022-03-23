package io.github.stickacupcakeinmyeye.harmonycraft.mixin;

import io.github.stickacupcakeinmyeye.harmonycraft.block.HarmonyBlockTags;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
	@Shadow protected ClientWorld world;
	@Shadow private Random random;

	@Shadow public Particle addParticle(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {return null;}

	@Inject(at = @At("HEAD"), cancellable = true, method = "Lnet/minecraft/client/particle/ParticleManager;addBlockBreakParticles(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V")
	private void addBlockBreakParticles(BlockPos pos, BlockState state, CallbackInfo ci) {
		if(state.isIn(HarmonyBlockTags.CLOUDS)) {
			VoxelShape voxelShape = state.getOutlineShape(world, pos);
			voxelShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
				double d = Math.min(1.0d, maxX - minX);
				double e = Math.min(1.0d, maxY - minY);
				double f = Math.min(1.0d, maxZ - minZ);
				int i = Math.max(2, MathHelper.ceil(d / 0.25d));
				int j = Math.max(2, MathHelper.ceil(e / 0.25d));
				int k = Math.max(2, MathHelper.ceil(f / 0.25d));
				for (int l = 0; l < i; ++l) {
					for (int m = 0; m < j; ++m) {
						for (int n = 0; n < k; ++n) {
							double g = (l + 0.5d) / i;
							double h = (m + 0.5d) / j;
							double o = (n + 0.5d) / k;
							double p = g * d + minX;
							double q = h * e + minY;
							double r = o * f + minZ;
							addParticle(ParticleTypes.CLOUD, pos.getX() + p, pos.getY() + q, pos.getZ() + r, 0.0d, 0.0d, 0.0d);
						}
					}
				}
			});
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), cancellable = true, method = "Lnet/minecraft/client/particle/ParticleManager;addBlockBreakingParticles(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)V")
	public void addBlockBreakingParticles(BlockPos pos, Direction direction, CallbackInfo ci) {
		BlockState blockState = this.world.getBlockState(pos);
		if(blockState.isIn(HarmonyBlockTags.CLOUDS)) {
			if (blockState.getRenderType() == BlockRenderType.INVISIBLE) {
				return;
			}
			int i = pos.getX();
			int j = pos.getY();
			int k = pos.getZ();
			float f = 0.1f;
			Box box = blockState.getOutlineShape(this.world, pos).getBoundingBox();
			double d = (double)i + this.random.nextDouble() * (box.maxX - box.minX - (double)0.2f) + (double)0.1f + box.minX;
			double e = (double)j + this.random.nextDouble() * (box.maxY - box.minY - (double)0.2f) + (double)0.1f + box.minY;
			double g = (double)k + this.random.nextDouble() * (box.maxZ - box.minZ - (double)0.2f) + (double)0.1f + box.minZ;
			if (direction == Direction.DOWN) {
				e = (double)j + box.minY - (double)0.1f;
			}
			if (direction == Direction.UP) {
				e = (double)j + box.maxY + (double)0.1f;
			}
			if (direction == Direction.NORTH) {
				g = (double)k + box.minZ - (double)0.1f;
			}
			if (direction == Direction.SOUTH) {
				g = (double)k + box.maxZ + (double)0.1f;
			}
			if (direction == Direction.WEST) {
				d = (double)i + box.minX - (double)0.1f;
			}
			if (direction == Direction.EAST) {
				d = (double)i + box.maxX + (double)0.1f;
			}
			addParticle(ParticleTypes.CLOUD, d, e, g, 0.0d, 0.0d, 0.0d);
			ci.cancel();
		}
	}
}
