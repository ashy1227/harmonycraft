package io.github.stickacupcakeinmyeye.harmonycraft.client.renderer.renderers;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.client.model.HarmonyModels;
import io.github.stickacupcakeinmyeye.harmonycraft.client.model.models.ParaspriteEntityModel;
import io.github.stickacupcakeinmyeye.harmonycraft.entity.entities.ParaspriteEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ParaspriteEntityRenderer extends MobEntityRenderer<ParaspriteEntity, ParaspriteEntityModel> {
	public ParaspriteEntityRenderer(EntityRendererFactory.Context context) {
		super(context, HarmonyModels.PARASPRITE.createModel(), 0.3125f);
	}

	@Override
	public Identifier getTexture(ParaspriteEntity entity) {
		return new Identifier(HarmonyCraft.MODID, "textures/entity/parasprite.png");
	}

}