package io.github.stickacupcakeinmyeye.harmonycraft.effect.effects;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;

import java.util.Random;
import java.util.UUID;

public class PoisonJokeStatusEffect extends StatusEffect {
	public PoisonJokeStatusEffect() {
		super(StatusEffectCategory.HARMFUL, 0x5b94f7);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			final UUID uuid = entity.getUuid();
			// it's ok to only use the least significant bits, we just need this
			// to be random and the same each time you get it, not unique
			Random uuidRandom = new Random(uuid.getLeastSignificantBits());
			Random random = new Random();

			// TODO add more effects and also make sure this works on dedicated servers because i have no clue
			switch(uuidRandom.nextInt(2)) {
				case 0: // fake creeper
					if(entity.world instanceof ClientWorld)
						if(random.nextInt(100) == 0)
							entity.world.playSound((PlayerEntity) entity, entity.getX() - Math.sin(entity.getYaw()), entity.getY(), entity.getZ() - Math.cos(entity.getYaw()), SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.HOSTILE, 1.0f, 0.5f);
					break;
				case 1: // light gray stained glass
					if(entity.world instanceof ServerWorld)
						for(int i = 0; i < ((PlayerEntity) entity).getInventory().size(); i++)
							if (((PlayerEntity) entity).getInventory().getStack(i).isEmpty())
								((PlayerEntity) entity).getInventory().setStack(i, new ItemStack(Items.LIGHT_GRAY_STAINED_GLASS_PANE, 1).setCustomName(new LiteralText(trololol(random.nextInt(1, 8)))));
					break;
			}
		}
	}

	protected String trololol(int i) {
		return "trolol" + "ol".repeat(Math.max(0, i));
	}
}
