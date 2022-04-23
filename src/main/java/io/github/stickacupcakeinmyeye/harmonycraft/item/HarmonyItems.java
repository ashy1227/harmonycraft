package io.github.stickacupcakeinmyeye.harmonycraft.item;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.entity.HarmonyEntities;
import io.github.stickacupcakeinmyeye.harmonycraft.item.items.MiracleTonicItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonyItems {
	public static Item PARASPRITE_SPAWN_EGG = new SpawnEggItem(HarmonyEntities.PARASPRITE, 	25521, 2485085, itemSettings());

	public static MiracleTonicItem MIRACLE_TONIC = new MiracleTonicItem(itemSettings().maxCount(16));

	public static void registerAll() {
		registerItem("parasprite_spawn_egg", PARASPRITE_SPAWN_EGG);

		registerItem("miracle_tonic", MIRACLE_TONIC);
	}

	protected static void registerItem(String ID, Item item) {
		Registry.register(Registry.ITEM, new Identifier(HarmonyCraft.MODID, ID), item);
	}
	
	protected static FabricItemSettings itemSettings() {
		return new FabricItemSettings().group(HarmonyItemGroups.HARMONYCRAFT);
	}
}
