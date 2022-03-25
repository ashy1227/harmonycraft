package io.github.stickacupcakeinmyeye.harmonycraft.mixin;

import io.github.stickacupcakeinmyeye.harmonycraft.block.blocks.CloudBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Entity.class)
public class EntityMixin {
	@Shadow public World world;
	@Shadow protected Random random;
	@Shadow private EntityDimensions dimensions;

	@Shadow @Final public final double getX() {return 0;}
	@Shadow @Final public final double getY() {return 0;}
	@Shadow @Final public final double getZ() {return 0;}

	@Shadow @Final public Vec3d getVelocity() {return null;}

	@Inject(at = @At(value = "HEAD", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true, method = "Lnet/minecraft/entity/Entity;spawnSprintingParticles()V")
	protected void spawnSprintingParticles(CallbackInfo ci) {
		int k;
		int j;
		int i = MathHelper.floor(this.getX());
		BlockPos blockPos = new BlockPos(i, j = MathHelper.floor(this.getY() - (double)0.2f), k = MathHelper.floor(this.getZ()));
		BlockState blockState = this.world.getBlockState(blockPos);
		if(blockState.getBlock() instanceof CloudBlock) {
			if(random.nextInt(4) == 0) {
				this.world.addParticle(((CloudBlock) blockState.getBlock()).getCloudParticle(), this.getX() + (this.random.nextDouble() - 0.5d) * (double) this.dimensions.width, this.getY() + 0.1d, this.getZ() + (this.random.nextDouble() - 0.5d) * (double) this.dimensions.width, 0.0d, 0.0d, 0.0d);
			}
			ci.cancel();
		}
	}
}
