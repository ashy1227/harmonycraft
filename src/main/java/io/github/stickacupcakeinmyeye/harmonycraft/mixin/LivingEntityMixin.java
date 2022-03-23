package io.github.stickacupcakeinmyeye.harmonycraft.mixin;

import io.github.stickacupcakeinmyeye.harmonycraft.block.HarmonyBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), cancellable = true, method = "Lnet/minecraft/entity/LivingEntity;fall(DZLnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)V")
	protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo ci) {
		if (!this.world.isClient && this.fallDistance > 2.0f && onGround) {
			if(landedState.isIn(HarmonyBlockTags.CLOUDS)) {
				int i = (int)Math.min(Math.ceil(this.fallDistance), 15);
				((ServerWorld)this.world).spawnParticles(ParticleTypes.CLOUD, this.getX()-0.2d, this.getY()+0.1d, this.getZ()-0.2d, i, 0.4d, 0.1d, 0.4d, 0.0d);
				super.fall(heightDifference, onGround, landedState, landedPosition);
				ci.cancel();
			}
		}
	}
}
