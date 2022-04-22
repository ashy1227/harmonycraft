package io.github.stickacupcakeinmyeye.harmonycraft.stat;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonyStats {
	public static final Identifier HUGGED_BY_PARASPRITE = getStat("hugged_by_pararsprite");

	public static void registerAll() {
		registerStat(HUGGED_BY_PARASPRITE, StatFormatter.DEFAULT);
	}
	protected static void registerStat(Identifier identifier, StatFormatter formatter) {
		Registry.register(Registry.CUSTOM_STAT, identifier.getPath(), identifier);
		Stats.CUSTOM.getOrCreateStat(identifier, formatter);
	}
	protected static Identifier getStat(String string) {
		return new Identifier(HarmonyCraft.MODID, string);
	}
}
