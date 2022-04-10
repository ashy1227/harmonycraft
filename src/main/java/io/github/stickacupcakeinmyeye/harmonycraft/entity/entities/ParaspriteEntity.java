package io.github.stickacupcakeinmyeye.harmonycraft.entity.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ParaspriteEntity extends PathAwareEntity implements Flutterer {
	public ParaspriteEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new FlightMoveControl(this, 90, true);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new EscapeDangerGoal(this, 2.0));
		this.goalSelector.add(1, new ParaspriteEntity.ParaspriteWanderAroundGoal(this, 1.0d, 2));
		this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
		this.goalSelector.add(4, new LookAroundGoal(this));
	}

	@Override
	public boolean isInAir() {
		return !this.onGround;
	}

	public static DefaultAttributeContainer.Builder createParaspriteAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6f).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
	}

	class ParaspriteWanderAroundGoal extends WanderAroundGoal {
		public ParaspriteWanderAroundGoal(PathAwareEntity mob, double speed) {
			super(mob, speed);
		}
		public ParaspriteWanderAroundGoal(PathAwareEntity mob, double speed, int chance) {
			super(mob, speed, chance);
		}
		public ParaspriteWanderAroundGoal(PathAwareEntity entity, double speed, int chance, boolean canDespawn) {
			super(entity, speed, chance, canDespawn);
		}

		@Override
		public void start() {
			Vec3d vec3d = this.getWanderTarget();
			if (vec3d != null) {
				ParaspriteEntity.this.navigation.startMovingAlong(ParaspriteEntity.this.navigation.findPathTo(new BlockPos(vec3d), 1), 1.0);
			}
		}
		@Nullable
		protected Vec3d getWanderTarget() {
			Vec3d vec = ParaspriteEntity.this.getRotationVec(0.0f);
			return AboveGroundTargeting.find(ParaspriteEntity.this, 8, 7, vec.x, vec.z, 1.5707964f, 3, 1);
		}
	}

	class ParaspriteHugGoal extends Goal {
		@Override
		public boolean canStart() {
			return false;
		}
	}
}
