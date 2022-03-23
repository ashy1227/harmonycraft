package io.github.stickacupcakeinmyeye.harmonycraft.effect;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.effect.effects.PoisonJokeStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonyStatusEffects {
	public static final PoisonJokeStatusEffect POISON_JOKE = new PoisonJokeStatusEffect();

	public static void registerAll() {
		registerStatusEffect("poison_joke", POISON_JOKE);
	}

	protected static void registerStatusEffect(String ID, StatusEffect effect) {
		Registry.register(Registry.STATUS_EFFECT, new Identifier(HarmonyCraft.MODID, ID), effect);
	}
}
