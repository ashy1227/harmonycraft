package io.github.stickacupcakeinmyeye.harmonycraft;

import io.github.stickacupcakeinmyeye.harmonycraft.particle.HarmonyParticles;
import net.fabricmc.api.ClientModInitializer;

public class HarmonyCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HarmonyParticles.registerClient();
	}
}
