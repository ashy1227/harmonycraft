package io.github.stickacupcakeinmyeye.harmonycraft.item;

import io.github.stickacupcakeinmyeye.harmonycraft.HarmonyCraft;
import io.github.stickacupcakeinmyeye.harmonycraft.armor.MareDoWellArmorItem;
import io.github.stickacupcakeinmyeye.harmonycraft.item.items.CiderItem;
import io.github.stickacupcakeinmyeye.harmonycraft.item.items.MiracleTonicItem;
import io.github.stickacupcakeinmyeye.harmonycraft.item.items.TwicaneItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class HarmonyItems {
	public static MiracleTonicItem MIRACLE_TONIC = new MiracleTonicItem(itemSettings().maxCount(16));
	public static TwicaneItem TWICANE = new TwicaneItem(9, -1.0f,itemSettings().rarity(Rarity.EPIC).maxCount(1));

	public static MareDoWellArmorItem MARE_DO_WELL_HELMET = new MareDoWellArmorItem(HarmonyArmorMaterials.MARE_DO_WELL, EquipmentSlot.HEAD, itemSettings().maxCount(1));
	public static MareDoWellArmorItem MARE_DO_WELL_CHESTPLATE = new MareDoWellArmorItem(HarmonyArmorMaterials.MARE_DO_WELL, EquipmentSlot.CHEST, itemSettings().maxCount(1));
	public static MareDoWellArmorItem MARE_DO_WELL_LEGGINGS = new MareDoWellArmorItem(HarmonyArmorMaterials.MARE_DO_WELL, EquipmentSlot.LEGS, itemSettings().maxCount(1));
	public static MareDoWellArmorItem MARE_DO_WELL_BOOTS = new MareDoWellArmorItem(HarmonyArmorMaterials.MARE_DO_WELL, EquipmentSlot.FEET, itemSettings().maxCount(1));

	public static Item RED_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item ORANGE_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item YELLOW_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item GREEN_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item BLUE_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item PURPLE_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item LIGHT_BLUE_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));
	public static Item TURQUOISE_GEM = new Item(itemSettings().rarity(Rarity.UNCOMMON));

	public static Item IMPORTED_OATS = new Item(itemSettings().rarity(Rarity.RARE).food(HarmonyFoodComponents.IMPORTED_OATS));
	public static Item OATS = new Item(itemSettings().food(HarmonyFoodComponents.OATS));

	public static Item HAYBURGER = new Item(itemSettings().food(HarmonyFoodComponents.HAYBURGER));
	public static Item OATBURGER = new Item(itemSettings().food(HarmonyFoodComponents.OATBURGER));
	public static Item CARROT_DOG = new Item(itemSettings().food(HarmonyFoodComponents.CARROT_DOG));
	public static Item FLOWER_SANDWICH = new Item(itemSettings().food(HarmonyFoodComponents.FLOWER_SANDWICH));
	public static Item MUFFIN = new Item(itemSettings().food(HarmonyFoodComponents.MUFFIN));
	public static Item CUPCAKE = new Item(itemSettings().food(HarmonyFoodComponents.CUPCAKE));
	public static Item DISGUSTING_CUPCAKE = new Item(itemSettings().food(HarmonyFoodComponents.DISGUSTING_CUPCAKE));

	public static Item APPLE_PIE = new Item(itemSettings().food(HarmonyFoodComponents.APPLE_PIE));
	public static Item APPLESAUCE = new Item(itemSettings().food(HarmonyFoodComponents.APPLESAUCE));
	public static Item ZAP_APPLE_JAM = new Item(itemSettings().food(HarmonyFoodComponents.ZAP_APPLE_JAM));
	public static Item CARAMEL_APPLE = new Item(itemSettings().food(HarmonyFoodComponents.CARAMEL_APPLE));
	public static Item CANDIED_APPLE_ON_A_STICK = new Item(itemSettings().food(HarmonyFoodComponents.CANDIED_APPLE_ON_A_STICK));
	public static Item APPLE_FRITTER = new Item(itemSettings().food(HarmonyFoodComponents.APPLE_FRITTER));
	public static Item CIDER = new CiderItem(itemSettings().food(HarmonyFoodComponents.CIDER));

	public static Item CHIMMY_CHERRY_CHONGA = new Item(itemSettings().food(HarmonyFoodComponents.CHIMMY_CHERRY_CHONGA));

	public static void registerAll() {
		registerItem("miracle_tonic", MIRACLE_TONIC);
		registerItem("twicane", TWICANE);

		registerItem("mare_do_well_helmet", MARE_DO_WELL_HELMET);
		registerItem("mare_do_well_chestplate", MARE_DO_WELL_CHESTPLATE);
		registerItem("mare_do_well_leggings", MARE_DO_WELL_LEGGINGS);
		registerItem("mare_do_well_boots", MARE_DO_WELL_BOOTS);

		registerItem("red_gem", RED_GEM);
		registerItem("orange_gem", ORANGE_GEM);
		registerItem("yellow_gem", YELLOW_GEM);
		registerItem("green_gem", GREEN_GEM);
		registerItem("blue_gem", BLUE_GEM);
		registerItem("purple_gem", PURPLE_GEM);
		registerItem("light_blue_gem", LIGHT_BLUE_GEM);
		registerItem("turquoise_gem", TURQUOISE_GEM);

		registerItem("imported_oats", IMPORTED_OATS);
		registerItem("oats", OATS);

		registerItem("hayburger", HAYBURGER);
		registerItem("oatburger", OATBURGER);
		registerItem("carrot_dog", CARROT_DOG);
		registerItem("flower_sandwich", FLOWER_SANDWICH);
		registerItem("muffin", MUFFIN);
		registerItem("cupcake", CUPCAKE);
		registerItem("disgusting_cupcake", DISGUSTING_CUPCAKE);

		registerItem("apple_pie", APPLE_PIE);
		registerItem("applesauce", APPLESAUCE);
		registerItem("zap_apple_jam", ZAP_APPLE_JAM);
		registerItem("caramel_apple", CARAMEL_APPLE);
		registerItem("candied_apple_on_a_stick", CANDIED_APPLE_ON_A_STICK);
		registerItem("apple_fritter", APPLE_FRITTER);
		registerItem("cider", CIDER);

		registerItem("chimmy_cherry_chonga", CHIMMY_CHERRY_CHONGA);
	}

	protected static void registerItem(String ID, Item item) {
		Registry.register(Registry.ITEM, new Identifier(HarmonyCraft.MODID, ID), item);
	}
	
	protected static FabricItemSettings itemSettings() {
		return new FabricItemSettings().group(HarmonyItemGroups.HARMONYCRAFT);
	}
}
