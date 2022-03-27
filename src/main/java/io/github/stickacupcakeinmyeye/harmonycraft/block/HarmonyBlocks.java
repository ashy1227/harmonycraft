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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class HarmonyBlocks {
	public static final Block CLOUD = new CloudBlock(cloudBlockSettings());
	public static final Block CLOUD_STAIRS = new CloudStairsBlock(CLOUD.getDefaultState(), FabricBlockSettings.copy(CLOUD));
	public static final Block CLOUD_SLAB = new CloudSlabBlock(FabricBlockSettings.copy(CLOUD));
	public static final Block RAINCLOUD = new RaincloudBlock(cloudBlockSettings().mapColor(MapColor.LIGHT_GRAY));
	public static final Block RAINCLOUD_STAIRS = new RaincloudStairsBlock(RAINCLOUD.getDefaultState(), FabricBlockSettings.copy(RAINCLOUD));
	public static final Block RAINCLOUD_SLAB = new RaincloudSlabBlock(FabricBlockSettings.copy(RAINCLOUD));
	public static final Block STORMCLOUD = new StormcloudBlock(cloudBlockSettings().mapColor(MapColor.GRAY));
	public static final Block STORMCLOUD_STAIRS = new StormcloudStairsBlock(STORMCLOUD.getDefaultState(), FabricBlockSettings.copy(STORMCLOUD));
	public static final Block STORMCLOUD_SLAB = new StormcloudSlabBlock(FabricBlockSettings.copy(STORMCLOUD));

	public static final Block POISON_JOKE = new PoisonJokeBlock(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.CROP).noCollision());

	public static void registerAll() {
		registerBlock("cloud", CLOUD);
		registerBlock("cloud_stairs", CLOUD_STAIRS);
		registerBlock("cloud_slab", CLOUD_SLAB);
		registerBlock("raincloud", RAINCLOUD);
		registerBlock("raincloud_stairs", RAINCLOUD_STAIRS);
		registerBlock("raincloud_slab", RAINCLOUD_SLAB);
		registerBlock("stormcloud", STORMCLOUD);
		registerBlock("stormcloud_stairs", STORMCLOUD_STAIRS);
		registerBlock("stormcloud_slab", STORMCLOUD_SLAB);

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
		return FabricBlockSettings.of(HarmonyMaterials.CLOUD_MATERIAL).requiresTool().strength(0.1f).sounds(BlockSoundGroup.SNOW).suffocates(HarmonyBlocks::never);
	}
	protected static FabricBlockSettings logBlockSettings(MapColor topMapColor, MapColor sideMapColor) {
		return (FabricBlockSettings) FabricBlockSettings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0f).sounds(BlockSoundGroup.WOOD);
	}

	protected static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}

	protected static FabricItemSettings itemSettings() {
		return new FabricItemSettings().group(HarmonyItemGroups.HARMONYCRAFT);
	}
}
