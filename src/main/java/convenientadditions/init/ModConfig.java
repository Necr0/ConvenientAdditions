package convenientadditions.init;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModConfig {
	//#########COMPOSTER#############
	public static boolean composter_recipe;
	public static boolean composter_overflowSmell;
	public static int composter_foodMultiplier;
	//chances
	public static float composter_compostChance;
	public static float composter_extraCompostChance;
	public static float composter_dirtChunkChance;
	public static float composter_fertilizerChance;
	//stats
	public static int composter_capacity;
	public static int composter_progressPeriod;
	public static int composter_progressContent;
	
	//#########SEED BOX#############
	public static boolean seedBox_recipe;
	
	//#########CHARGE ITEMS#############
	public static boolean charge_sunstone;
	public static boolean charge_blazingRock;
	
	//#########BAUBLES#############
	public static boolean baubles_ring_of_charging;
	public static boolean baubles_amulet_of_breath;
	public static boolean baubles_amulet_of_wisdom;
	public static boolean baubles_ring_of_saturation;
	public static boolean baubles_ring_of_sunlight;
	public static boolean baubles_nether_charm;

	//#########TITANIUM#############
	public static boolean titanium_enabled;
	public static boolean titanium_oreGen;
	public static int titanium_oresPerCluster;
	public static int titanium_clusterFrequency;
	public static boolean titanium_tools;
	public static boolean titanium_wrench;
	public static boolean titanium_recipes;

	//#########ENDERSLATE#############
	public static boolean enderSlate_recipe;
	public static boolean enderSlate_crystalCharge;
	public static int enderSlate_crystalChargeRate;
	public static boolean enderSlate_enderEyeRechargeRecipe;
	public static float enderSlate_enderEyeRechargePercentage;
	
	//#########GENERAL#############
	public static boolean ironWrench;
	public static boolean launchingArrows;
	public static boolean playerInterface;
	public static boolean proximitySensor;
	public static boolean powderKeg;
	public static boolean setProvider;
	
	public static void init(){
		Configuration cfg=new Configuration(new File("config/ConvAdd.cfg"));
		cfg.load();
		
		String category=cfg.CATEGORY_GENERAL;
		cfg.setCategoryRequiresMcRestart(category, true);
		ironWrench=cfg.getBoolean("ironWrench", category, false, "");
		launchingArrows=cfg.getBoolean("launchingArrows", category, true, "");
		playerInterface=cfg.getBoolean("playerInterface", category, true, "");
		proximitySensor=cfg.getBoolean("proximitySensor", category, true, "");
		setProvider=cfg.getBoolean("setProvider", category, true, "");
		powderKeg=cfg.getBoolean("powderKeg", category, true, "");
		
		category="composter";
		cfg.setCategoryRequiresMcRestart(category, true);
		composter_recipe=cfg.getBoolean("recipe", category, true, "");
		composter_overflowSmell=cfg.getBoolean("overflowSmell", category, true, "Enables potion effects when overflowing");
		composter_compostChance=cfg.getFloat("compostChance", category, 1F, 0F, 1F, "");
		composter_extraCompostChance=cfg.getFloat("extraCompostChance", category, .25F, 0F, 1F, "");
		composter_dirtChunkChance=cfg.getFloat("dirtChunkChance", category, .4F, 0F, 1F, "");
		composter_fertilizerChance=cfg.getFloat("fertilizerChance", category, .1666F, 0F, 1F, "");
		composter_foodMultiplier=cfg.getInt("foodMultiplier", category, 400, 0, Integer.MAX_VALUE, "");
		composter_capacity=cfg.getInt("capacity", category, 25000, 1, Integer.MAX_VALUE, "");
		composter_progressPeriod=cfg.getInt("progressPeriod", category, 2100, 1, Integer.MAX_VALUE, "The amount of time needed for 1 operation.");
		composter_progressContent=cfg.getInt("progressContent", category, 2500, 1, Integer.MAX_VALUE, "The amount of composting mass needed for 1 operation.");
		
		category="charge items";
		cfg.setCategoryRequiresMcRestart(category, true);
		charge_sunstone=cfg.getBoolean("sunstone", category, true, "");
		charge_blazingRock=cfg.getBoolean("blazingRock", category, true, "");
		
		category="baubles";
		cfg.setCategoryRequiresMcRestart(category, true);
		baubles_ring_of_charging=cfg.getBoolean("ring_of_charging", category, true, "");
		baubles_amulet_of_breath=cfg.getBoolean("amulet_of_breath", category, true, "");
		baubles_amulet_of_wisdom=cfg.getBoolean("amulet_of_wisdom", category, true, "");
		baubles_ring_of_saturation=cfg.getBoolean("ring_of_saturation", category, true, "");
		baubles_ring_of_sunlight=cfg.getBoolean("ring_of_sunlight", category, true, "");
		baubles_nether_charm=cfg.getBoolean("nether_charm", category, true, "");
		
		category="titanium";
		cfg.setCategoryRequiresMcRestart(category, true);
		titanium_enabled=cfg.getBoolean("enabled", category, true, "Enables Titanium");
		titanium_oreGen=cfg.getBoolean("oreGen", category, true, "");
		titanium_oresPerCluster=cfg.getInt("oresPerCluster", category, 5, 1, Integer.MAX_VALUE, "");
		titanium_clusterFrequency=cfg.getInt("clusterFrequency", category, 13, 1, Integer.MAX_VALUE, "");
		titanium_tools=cfg.getBoolean("tools", category, true, "");
		titanium_wrench=cfg.getBoolean("wrench", category, true, "");
		titanium_recipes=cfg.getBoolean("recipes", category, true, "");
		
		category="enderSlate";
		cfg.setCategoryRequiresMcRestart(category, true);
		enderSlate_recipe=cfg.getBoolean("recipe", category, true, "");
		enderSlate_crystalCharge=cfg.getBoolean("crystalCharge", category, true, "");
		enderSlate_crystalChargeRate=cfg.getInt("crystalChargeRate", category, 120, 0, Integer.MAX_VALUE, "in charges/second; rate=log_2(crystalCount+1)");
		enderSlate_enderEyeRechargeRecipe=cfg.getBoolean("enderEyeRechargeRecipe", category, true, "");
		enderSlate_enderEyeRechargePercentage=cfg.getFloat("enderEyeRechargePercentage", category, 0.45F, 0, 1, "");
		
		category="seedbox";
		cfg.setCategoryRequiresMcRestart(category, true);
		seedBox_recipe=cfg.getBoolean("recipe", category, true, "");
		
		if(cfg.hasChanged())
			cfg.save();
	}
}
