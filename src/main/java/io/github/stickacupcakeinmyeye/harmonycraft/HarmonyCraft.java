package io.github.stickacupcakeinmyeye.harmonycraft;

import io.github.stickacupcakeinmyeye.harmonycraft.block.HarmonyBlocks;
import io.github.stickacupcakeinmyeye.harmonycraft.effect.HarmonyStatusEffects;
import io.github.stickacupcakeinmyeye.harmonycraft.item.HarmonyItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarmonyCraft implements ModInitializer {
	public static final String MODID = "harmony";
	public static final Logger LOGGER = LoggerFactory.getLogger("HarmonyCraft");

	@Override
	public void onInitialize() {
		HarmonyItems.registerAll();
		HarmonyBlocks.registerAll();
		HarmonyStatusEffects.registerAll();
	}
}