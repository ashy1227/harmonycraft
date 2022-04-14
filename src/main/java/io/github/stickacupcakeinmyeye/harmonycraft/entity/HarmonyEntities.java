package io.github.stickacupcakeinmyeye.harmonycraft.entity;

import com.minelittlepony.mson.api.Mson;
import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.client.model.HarmonyModels;
import io.github.stickacupcakeinmyeye.harmonycraft.client.renderer.renderers.ParaspriteEntityRenderer;
import io.github.stickacupcakeinmyeye.harmonycraft.entity.entities.ParaspriteEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HarmonyEntities {
	public static final EntityType<ParaspriteEntity> PARASPRITE = register(
		"parasprite",
		FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ParaspriteEntity::new).dimensions(EntityDimensions.fixed(0.3125f, 0.3125f)).build()
	);

	public static void registerAll() {
		FabricDefaultAttributeRegistry.register(PARASPRITE, ParaspriteEntity.createParaspriteAttributes());
	}
	@Environment(EnvType.CLIENT)
	public static void registerClient() {
		Mson.getInstance().getEntityRendererRegistry().registerEntityRenderer(HarmonyEntities.PARASPRITE, r -> new ParaspriteEntityRenderer(r) {{
			this.model = HarmonyModels.PARASPRITE.createModel();
		}});
	}

	private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType) {
		return Registry.register(Registry.ENTITY_TYPE, new Identifier(HarmonyCraft.MODID, id), entityType);
	}
}
