package io.github.stickacupcakeinmyeye.harmonycraft.item;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.block.HarmonyBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class HarmonyItemGroups {
	public static final ItemGroup HARMONYCRAFT = FabricItemGroupBuilder.build(new Identifier(HarmonyCraft.MODID, "items"), () -> new ItemStack(HarmonyBlocks.CLOUD));
}
