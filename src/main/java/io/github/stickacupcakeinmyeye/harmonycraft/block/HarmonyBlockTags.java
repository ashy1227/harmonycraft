package io.github.stickacupcakeinmyeye.harmonycraft.block;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonyBlockTags {
	public static final TagKey<Block> CLOUDS = TagKey.of(Registry.BLOCK_KEY, new Identifier(HarmonyCraft.MODID, "clouds"));
}
