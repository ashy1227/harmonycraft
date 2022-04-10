package io.github.stickacupcakeinmyeye.harmonycraft;

import io.github.stickacupcakeinmyeye.harmonycraft.block.HarmonyBlocks;
import io.github.stickacupcakeinmyeye.harmonycraft.client.model.HarmonyModels;
import io.github.stickacupcakeinmyeye.harmonycraft.entity.HarmonyEntities;
import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class HarmonyCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HarmonyModels.bootstrap();

		HarmonyBlocks.registerClient();
		HarmonyParticles.registerClient();
		HarmonyEntities.registerClient();
	}
}
