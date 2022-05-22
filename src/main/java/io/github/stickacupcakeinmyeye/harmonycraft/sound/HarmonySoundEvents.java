package io.github.stickacupcakeinmyeye.harmonycraft.sound;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonySoundEvents {
	public static final SoundEvent PARASPRITE_AMBIENT = soundEvent("entity.parasprite.ambient");
	public static final SoundEvent PARASPRITE_HUG = soundEvent("entity.parasprite.hug");
	public static final SoundEvent PARASPRITE_HAPPY = soundEvent("entity.parasprite.happy");
	public static final SoundEvent PARASPRITE_REPRODUCE = soundEvent("entity.parasprite.reproduce");

	public static void registerAll() {
		registerSoundEvent(PARASPRITE_AMBIENT);
		registerSoundEvent(PARASPRITE_HUG);
		registerSoundEvent(PARASPRITE_HAPPY);
		registerSoundEvent(PARASPRITE_REPRODUCE);
	}

	protected static SoundEvent registerSoundEvent(String ID, SoundEvent event) {
		return Registry.register(Registry.SOUND_EVENT, new Identifier(HarmonyCraft.MODID, ID), event);
	}
	protected static SoundEvent registerSoundEvent(SoundEvent event) {
		return Registry.register(Registry.SOUND_EVENT, event.getId(), event);
	}

	protected static SoundEvent soundEvent(String ID) {
		return new SoundEvent(new Identifier(HarmonyCraft.MODID, ID));
	}
}
