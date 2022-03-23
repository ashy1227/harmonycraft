package io.github.stickacupcakeinmyeye.harmonycraft.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class HarmonyFoodComponents {
	public static final FoodComponent OATS = new FoodComponent.Builder().hunger(3).saturationModifier(1.5f).snack().build();
	public static final FoodComponent IMPORTED_OATS = new FoodComponent.Builder().hunger(5).saturationModifier(1.5f).snack().build();

	public static final FoodComponent HAYBURGER = new FoodComponent.Builder().hunger(8).saturationModifier(0.5f).build();
	public static final FoodComponent OATBURGER = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).build();
	public static final FoodComponent CARROT_DOG = new FoodComponent.Builder().hunger(6).saturationModifier(0.5f).build();
	public static final FoodComponent FLOWER_SANDWICH = new FoodComponent.Builder().hunger(2).saturationModifier(0.6f).build();
	public static final FoodComponent MUFFIN = new FoodComponent.Builder().hunger(4).saturationModifier(0.6f).build();
	public static final FoodComponent CUPCAKE = new FoodComponent.Builder().hunger(3).saturationModifier(0.2f).build();
	public static final FoodComponent DISGUSTING_CUPCAKE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f)
		.statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 3600, 0), 1.00f)
		.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 0), 0.50f)
		.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0), 0.50f)
		.statusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2400, 0), 0.40f)
		.statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2400, 0), 0.25f)
		.statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 1200, 0), 0.25f)
		.statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 0), 0.10f)
	.build();

	public static final FoodComponent APPLE_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.4f).build();
	public static final FoodComponent ZAP_APPLE_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(1.0f).build();
	public static final FoodComponent APPLESAUCE = new FoodComponent.Builder().hunger(6).saturationModifier(0.6f).build();
	public static final FoodComponent CARAMEL_APPLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.4f).snack().build();
	public static final FoodComponent CANDIED_APPLE_ON_A_STICK = new FoodComponent.Builder().hunger(6).saturationModifier(0.3f).snack().build();
	public static final FoodComponent APPLE_FRITTER = new FoodComponent.Builder().hunger(6).saturationModifier(0.2f).snack().build();
	public static final FoodComponent CIDER = new FoodComponent.Builder().hunger(6).saturationModifier(0.3f)
		.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 0.1f)
	.build();

	public static final FoodComponent CHIMMY_CHERRY_CHONGA = new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).build();
}
