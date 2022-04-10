package io.github.stickacupcakeinmyeye.harmonycraft.client.model;

import com.minelittlepony.mson.api.Mson;
import com.minelittlepony.mson.api.ModelKey;
import com.minelittlepony.mson.api.MsonModel;
import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.client.model.models.ParaspriteEntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.util.Identifier;

public class HarmonyModels {
	public static final ModelKey<ParaspriteEntityModel> PARASPRITE = register("parasprite", ParaspriteEntityModel::new);

	public static void bootstrap() {}

	static <T extends Model> ModelKey<T> register(String id, MsonModel.Factory<T> constructor) {
		return Mson.getInstance().registerModel(new Identifier(HarmonyCraft.MODID, id), constructor);
	}
}
