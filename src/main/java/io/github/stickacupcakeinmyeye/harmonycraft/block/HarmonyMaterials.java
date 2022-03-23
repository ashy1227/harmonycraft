package io.github.stickacupcakeinmyeye.harmonycraft.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class HarmonyMaterials {
	public static final Material CLOUD_MATERIAL = new FabricMaterialBuilder(MapColor.OFF_WHITE).destroyedByPiston().build();
}
