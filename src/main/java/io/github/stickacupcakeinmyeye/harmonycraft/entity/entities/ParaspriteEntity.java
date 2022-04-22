package io.github.stickacupcakeinmyeye.harmonycraft.entity.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class ParaspriteEntity extends PathAwareEntity implements Flutterer, Tameable {
	protected static final TrackedData<Optional<UUID>> OWNER_UUID = DataTracker.registerData(ParaspriteEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);
	private static final TrackedData<Boolean> FOLLOWING = DataTracker.registerData(ParaspriteEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public ParaspriteEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new FlightMoveControl(this, 90, true);
		this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f);
		this.setPathfindingPenalty(PathNodeType.WATER, -1.0f);
		this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0f);
		this.setPathfindingPenalty(PathNodeType.COCOA, -1.0f);
		this.setPathfindingPenalty(PathNodeType.FENCE, -1.0f);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(OWNER_UUID, Optional.empty());
		this.dataTracker.startTracking(FOLLOWING, false);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		if (this.getOwnerUuid() != null)
			nbt.putUuid("Owner", this.getOwnerUuid());
		nbt.putBoolean("Following", this.isFollowing());
	}
	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		UUID uuid;

		if (nbt.containsUuid("Owner")) {
			uuid = nbt.getUuid("Owner");
		} else {
			String string = nbt.getString("Owner");
			uuid = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
		}
		if (uuid != null) {
			try {
				this.setOwnerUuid(uuid);
			}
			catch (Throwable string) {
				System.out.println(string.getMessage());
			}
		}
		this.setFollowing(nbt.getBoolean("Following"));
	}

	public void setOwnerUuid(@Nullable UUID uuid) {
		this.dataTracker.set(OWNER_UUID, Optional.ofNullable(uuid));
	}
	public void setOwner(PlayerEntity player) {
		this.setOwnerUuid(player.getUuid());
	}

	@Override
	@Nullable
	public UUID getOwnerUuid() {
		return this.dataTracker.get(OWNER_UUID).orElse(null);
	}
	@Override
	@Nullable
	public LivingEntity getOwner() {
		try {
			UUID uuid = this.getOwnerUuid();
			if (uuid == null)
				return null;
			return this.world.getPlayerByUuid(uuid);
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}
	public boolean isOwner(LivingEntity entity) {
		return entity == this.getOwner();
	}

	public void setFollowing(boolean following) {
		this.dataTracker.set(FOLLOWING, following);
	}
	public boolean isFollowing() {
		return this.dataTracker.get(FOLLOWING);
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		if (!this.world.isClient && (this.getOwnerUuid() == null || !this.getOwnerUuid().equals(player.getUuid()))) {
			this.setOwner(player);
			this.setFollowing(true);

			this.navigation.stop();
			this.setTarget(null);

			this.world.sendEntityStatus(this, (byte)7); // love hearts
			this.playSound(SoundEvents.ENTITY_FOX_AMBIENT, this.getSoundVolume(), 1.0f);
			return ActionResult.SUCCESS;
		} else if(!this.world.isClient && this.getOwnerUuid().equals(player.getUuid())) {
			this.setFollowing(!this.isFollowing());

			if(this.isFollowing())
				this.playSound(SoundEvents.ENTITY_VILLAGER_CELEBRATE, this.getSoundVolume(), 1.0f);
			else
				this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), 1.0f);
			return ActionResult.SUCCESS;
		}
		this.playSound(SoundEvents.ENTITY_CAT_PURR, this.getSoundVolume(), 1.0f);
		return ActionResult.SUCCESS;
	}
	@Override
	public void handleStatus(byte status) {
		if (status == 7) {
			this.showHeartParticles();
		} else {
			super.handleStatus(status);
		}
	}
	protected void showHeartParticles() {
		for (int i = 0; i < 7; ++i) {
			double velX = this.random.nextGaussian() * 0.02;
			double velY = this.random.nextGaussian() * 0.02;
			double velZ = this.random.nextGaussian() * 0.02;
			this.world.addParticle(ParticleTypes.HEART, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), velX, velY, velZ);
		}
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25f));
		this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 12.0f, 1.0f, true));
		this.goalSelector.add(2, new ParaspriteHugGoal());
		this.goalSelector.add(3, new ParaspriteFollowOwnerGoal(3.0f, 0.0f, 0.2f));
		this.goalSelector.add(4, new LookAtEntityGoal(this, LivingEntity.class, 8.0f, 0.025f, true));
		this.goalSelector.add(5, new ParaspriteEntity.ParaspriteWanderAroundGoal());
		this.goalSelector.add(6, new LookAroundGoal(this));
	}

	@Override
	public float getPathfindingFavor(BlockPos pos, WorldView world) {
		if (world.getBlockState(pos).isAir()) {
			return 10.0f;
		}
		return 0.0f;
	}

	public static DefaultAttributeContainer.Builder createParaspriteAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.7f).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
	}
	@Override
	protected EntityNavigation createNavigation(World world) {
		BirdNavigation birdNavigation = new BirdNavigation(this, world){
			@Override
			public boolean isValidPosition(BlockPos pos) {
				return !this.world.getBlockState(pos.down()).isAir();
			}
		};
		birdNavigation.setCanPathThroughDoors(true); // trololololol
		birdNavigation.setCanSwim(false);
		birdNavigation.setCanEnterOpenDoors(true);
		return birdNavigation;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
	}
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_FOX_AMBIENT;
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_FOX_HURT;
	}
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_FOX_DEATH;
	}
	@Override
	protected float getSoundVolume() {
		return 0.3f;
	}

	@Override
	protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.6f;
	}

	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}
	@Override
	protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
	}

	@Override
	public boolean isInAir() {
		return !this.onGround;
	}

	@Override
	public EntityGroup getGroup() {
		return EntityGroup.ARTHROPOD;
	}

	@Override
	protected void swimUpward(TagKey<Fluid> fluid) {
		this.setVelocity(this.getVelocity().add(0.0, 0.01, 0.0));
	}

	@Override
	public Vec3d getLeashOffset() {
		return new Vec3d(this.getWidth()/2.0f, 0.0f, this.getWidth() / 2.0f);
	}

	class ParaspriteEscapeDangerGoal extends Goal { // TODO
		@Override
		public boolean canStart() {
			return false;
		}
	}
	class ParaspriteSwarmGoal extends Goal { // TODO
		@Override
		public boolean canStart() {
			return false;
		}
	}
	class ParaspriteGatherGoal extends Goal { // TODO
		@Override
		public boolean canStart() {
			return false;
		}
	}
	class ParaspriteEatAppleGoal extends Goal { // TODO
		@Override
		public boolean canStart() {
			return false;
		}
	}
	class ParaspriteHeadToMusicGoal extends Goal { // TODO
		@Override
		public boolean canStart() {
			return false;
		}
	}
	class ParaspriteReproduceGoal extends Goal { // TODO
		@Override
		public boolean canStart() {
			return false;
		}
	}
	class ParaspriteHugGoal extends Goal {
		@Override
		public boolean canStart() {
			if(ParaspriteEntity.this.getOwner() == null)
				return false;
			if(ParaspriteEntity.this.squaredDistanceTo(ParaspriteEntity.this.getOwner()) > 32)
				return false;
			if(random.nextInt(75) == 0)
				return true;
			return false;
		}

		@Override
		public boolean shouldContinue() {
			return ParaspriteEntity.this.navigation.isFollowingPath();
		}

		@Override
		public void stop() {
			ParaspriteEntity.this.world.sendEntityStatus(ParaspriteEntity.this, (byte)7); // love hearts
			ParaspriteEntity.this.playSound(SoundEvents.ENTITY_CAT_PURREOW, ParaspriteEntity.this.getSoundVolume(), 1.0f);
		}

		@Override
		public void tick() {
			ParaspriteEntity.this.navigation.startMovingTo(ParaspriteEntity.this.getOwner(), 1.0d);
		}
	}
	class ParaspriteWanderAroundGoal extends Goal {
		public ParaspriteWanderAroundGoal() {
			this.setControls(EnumSet.of(Goal.Control.MOVE));
		}

		@Override
		public boolean canStart() {
			return ParaspriteEntity.this.navigation.isIdle() && ParaspriteEntity.this.random.nextInt(10) == 0;
		}
		@Override
		public boolean shouldContinue() {
			return ParaspriteEntity.this.navigation.isFollowingPath();
		}
		@Override
		public void start() {
			Vec3d vec3d = this.getRandomLocation();
			if (vec3d != null) {
				ParaspriteEntity.this.navigation.startMovingAlong(ParaspriteEntity.this.navigation.findPathTo(new BlockPos(vec3d), 1), 1.0);
			}
		}

		@Nullable
		private Vec3d getRandomLocation() {
			Vec3d rotationVec = ParaspriteEntity.this.getRotationVec(0.0f);
			Vec3d target = AboveGroundTargeting.find(ParaspriteEntity.this, 8, 7, rotationVec.x, rotationVec.z, 1.5707964f, 3, 1);

			if (target != null)
				return target;
			else
				return NoPenaltySolidTargeting.find(ParaspriteEntity.this, 8, 4, -2, rotationVec.x, rotationVec.z, 1.5707963705062866);
		}
	}
	class ParaspriteFollowOwnerGoal extends Goal {
		public final double speed;
		public final float maxDistance;
		public final float minDistance;
		private int updateCountdownTicks;
		private LivingEntity owner;

		public ParaspriteFollowOwnerGoal(double speed, float minDistance, float maxDistance) {
			this.speed = speed;
			this.minDistance = minDistance;
			this.maxDistance = maxDistance;
			this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
		}

		@Override
		public boolean canStart() {
			if(!ParaspriteEntity.this.isFollowing())
				return false;

			this.owner = ParaspriteEntity.this.getOwner();
			if (owner == null)
				return false;
			if (owner.isSpectator())
				return false;
			if (ParaspriteEntity.this.squaredDistanceTo(owner) < (double)(this.minDistance * this.minDistance))
				return false;
			return true;
		}

		@Override
		public boolean shouldContinue() {
			if (ParaspriteEntity.this.navigation.isIdle())
				return false;
			return !(ParaspriteEntity.this.squaredDistanceTo(owner) <= (double)(this.maxDistance * this.maxDistance));
		}

		@Override
		public void start() {
			this.updateCountdownTicks = 0;
		}

		@Override
		public void stop() {
			ParaspriteEntity.this.navigation.stop();
		}

		@Override
		public void tick() {
			ParaspriteEntity.this.getLookControl().lookAt(owner, 10.0f, ParaspriteEntity.this.getMaxLookPitchChange());
			if (--this.updateCountdownTicks > 0)
				return;
			this.updateCountdownTicks = this.getTickCount(10);
			if (ParaspriteEntity.this.isLeashed() || ParaspriteEntity.this.hasVehicle())
				return;
			ParaspriteEntity.this.navigation.startMovingTo(this.owner, this.speed);
		}
	}
}
