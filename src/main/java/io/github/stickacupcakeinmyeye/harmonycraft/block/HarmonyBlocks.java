package io.github.stickacupcakeinmyeye.harmonycraft.block;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.block.blocks.*;
import io.github.stickacupcakeinmyeye.harmonycraft.item.HarmonyItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class HarmonyBlocks {
	public static final Block CLOUD = new CloudBlock(cloudBlockSettings());
	public static final Block CLOUD_STAIRS = new CloudStairsBlock(CLOUD.getDefaultState(), FabricBlockSettings.copy(CLOUD));
	public static final Block RAINCLOUD = new RaincloudBlock(cloudBlockSettings().mapColor(MapColor.LIGHT_GRAY));
	public static final Block RAINCLOUD_STAIRS = new RaincloudStairsBlock(RAINCLOUD.getDefaultState(), FabricBlockSettings.copy(RAINCLOUD));
	public static final Block STORMCLOUD = new StormcloudBlock(cloudBlockSettings().mapColor(MapColor.GRAY));
	public static final Block STORMCLOUD_STAIRS = new StormcloudStairsBlock(STORMCLOUD.getDefaultState(), FabricBlockSettings.copy(STORMCLOUD));

	public static final Block POISON_JOKE = new PoisonJokeBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).noCollision());

	public static void registerAll() {
		registerBlock("cloud", CLOUD);
		registerBlock("cloud_stairs", CLOUD_STAIRS);
		registerBlock("raincloud", RAINCLOUD);
		registerBlock("raincloud_stairs", RAINCLOUD_STAIRS);
		registerBlock("stormcloud", STORMCLOUD);
		registerBlock("stormcloud_stairs", STORMCLOUD_STAIRS);

		registerBlock("poison_joke", POISON_JOKE);
	}

	protected static void registerBlock(String ID, Block block, Item.Settings itemSettings) {
		Registry.register(Registry.BLOCK, new Identifier(HarmonyCraft.MODID, ID), block);
		Registry.register(Registry.ITEM, new Identifier(HarmonyCraft.MODID, ID), new BlockItem(block, itemSettings));
	}
	protected static void registerBlock(String ID, Block block) {
		registerBlock(ID, block, itemSettings());
	}

	protected static FabricBlockSettings cloudBlockSettings() {
		return FabricBlockSettings.of(HarmonyMaterials.CLOUD_MATERIAL).requiresTool().strength(0.1f).sounds(BlockSoundGroup.SNOW);
	}
	protected static FabricBlockSettings logBlockSettings(MapColor topMapColor, MapColor sideMapColor) {
		return (FabricBlockSettings) FabricBlockSettings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0f).sounds(BlockSoundGroup.WOOD);
	}

	protected static FabricItemSettings itemSettings() {
		return new FabricItemSettings().group(HarmonyItemGroups.HARMONYCRAFT);
	}
}
