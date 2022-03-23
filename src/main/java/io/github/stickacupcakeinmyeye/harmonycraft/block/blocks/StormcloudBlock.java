package io.github.stickacupcakeinmyeye.harmonycraft.block.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
}
