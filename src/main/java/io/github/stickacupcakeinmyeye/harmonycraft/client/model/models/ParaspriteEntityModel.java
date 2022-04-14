package io.github.stickacupcakeinmyeye.harmonycraft.client.model.models;

import io.github.stickacupcakeinmyeye.harmonycraft.entity.entities.ParaspriteEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

/**
 * Based on the parasprite (strider) model from MineLP
 */
public class ParaspriteEntityModel extends EntityModel<ParaspriteEntity> {
	private final ModelPart body;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
//	private final ModelPart leftFeet;
//	private final ModelPart rightFeet;

	public ParaspriteEntityModel(ModelPart tree) {
		super(RenderLayer::getEntityTranslucent);
		body = tree;
		leftWing = tree.getChild("leftWing");
		rightWing = tree.getChild("rightWing");
		body.setPivot(0.0f, 21.5f, 0.0f);
//		leftFeet = tree.getChild("leftFeet");
//		rightFeet = tree.getChild("rightFeet");
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(ParaspriteEntity entity, float move, float swing, float ticks, float headYaw, float headPitch) {
		body.yaw = headYaw * 0.017453292f;
		body.pitch = headPitch * 0.017453292f;

		// TODO it might be a good idea to have them flap in the direction of their travel
		float pitch, yaw, roll;
		if(entity.deathTime > 0) {
			pitch = 0.0f;
			roll = 0.0f;
			yaw = 0.0f;
		} else {
			pitch = 0.5f;
			roll = (float)Math.sin(ticks*1.5f) / 1.25f;
			yaw = -roll * pitch + (float)Math.cos(ticks*1.5f) / 8.0f - 0.25f;
		}

		leftWing.visible = true;
		leftWing.yaw = yaw;
		leftWing.roll = roll;
		leftWing.pitch = pitch;

		rightWing.visible = true;
		rightWing.yaw = -yaw;
		rightWing.roll = -roll;
		rightWing.pitch = pitch;
	}
}