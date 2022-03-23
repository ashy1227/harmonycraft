package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import io.github.stickacupcakeinmyeye.harmonycraft.effect.HarmonyStatusEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PoisonJokeBlock extends PlantBlock {
	public PoisonJokeBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return super.canPlantOnTop(floor, world, pos) || floor.getBlock().equals(Blocks.GRASS_BLOCK);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if(entity instanceof PlayerEntity) {
			entity.slowMovement(state, new Vec3d(0.9f, 1.0, 0.9f));

			if(!world.isClient) {
				((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(HarmonyStatusEffects.POISON_JOKE, 2400));
			}
		}
	}
}
