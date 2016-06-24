package convenientadditions.init;

import convenientadditions.item.charge.enderSlate.RecipeEnderSlateRecharge;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	private static String titaniumIngot;
	private static String titaniumNugget;
	
	public static void init(){
		boolean titanium=ModConfig.titanium_enabled;
		titaniumIngot=titanium?"ingotTitanium":"ingotIron";
		titaniumNugget=titanium?"nuggetTitanium":"nuggetGold";
		
		if(ModConfig.ironWrench)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumWrench,1),
				"i i",
				" i ",
				"i i",
				'i', "ingotIron"));
		
		if(titanium){
			GameRegistry.addSmelting(ModBlocks.oreTitaniumBlock, new ItemStack(ModItems.ingotTitanium), 1.0F);
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.nuggetTitanium,9), new ItemStack(ModItems.ingotTitanium)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTitanium,1),
					"ttt",
					"ttt",
					"ttt",
					't', new ItemStack(ModItems.nuggetTitanium)));
			if(ModConfig.titanium_wrench)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumWrench,1),
					"n n",
					" t ",
					"n n",
					't', "ingotTitanium", 'n', "nuggetTitanium"));
			if(ModConfig.titanium_tools)
				initTitaniumTools();
		}
		
		initCompost();
		initChargeItems();
		initBaubles();
	}
	
	private static void initCompost(){
		if(ModConfig.composter_recipe)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composterBlock),
				"s s",
			    "s s",
			    "ppp",
			    's', "slabWood",
			    'p', "plankWood"));
			
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostSoilBlock),
				"cc",
			    "cc",
			    'c', "chunkCompost"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.dirt),
				"dd",
			    "dd",
			    'd', new ItemStack(ModItems.itemDirtChunk)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), Blocks.dirt, "chunkCompost"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.dirt), ModBlocks.compostSoilBlock));
	}
	
	private static void initBlocks(){
		if(ModConfig.powderKeg)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.powderKegBlock),
				"psp",
			    "pgp",
			    "psp",
			    'p', "plankWood",
			    's', "slabWood",
			    'g', Items.gunpowder));

		if(ModConfig.seedBox_recipe)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.seedBoxBlock,
				"tpt",
			    "php",
			    "tpt",
			    'h', Blocks.hopper,
			    'p', "plankWood",
			    't', titaniumIngot));

		if(ModConfig.playerInterface)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
				"tpt",
			    "geg",
			    "srs",
			    't', titaniumIngot,
			    'e', Items.ender_pearl,
			    'p', Blocks.stone_pressure_plate,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));

		if(ModConfig.proximitySensor)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
				"sgs",
			    "tet",
			    "rgr",
			    't', titaniumIngot,
			    'e', Items.ender_eye,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));

		if(ModConfig.setProvider)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.setProviderBlock),
				"tht",
			    "bcb",
			    "tht",
			    't', titaniumIngot,
			    'h', Blocks.hopper,
			    'c', Items.comparator,
			    'b', Blocks.iron_bars));
	}

	private static void initChargeItems(){
		if(ModConfig.charge_sunstone)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunstone.FULLY_CHARGED.copy(),
				"grg",
			    "tdt",
			    "grg",
			    't', titaniumIngot,
			    'd', "gemDiamond",
			    'r', "dustRedstone",
			    'g', "dustGlowstone"));
		
		if(ModConfig.charge_blazingRock)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemBlazingRock.FULLY_CHARGED.copy(),
				"tlt",
			    "bdb",
			    "tgt",
			    't', titaniumNugget,
			    'b', Items.blaze_powder,
			    'd', "gemDiamond",
			    'l', Items.lava_bucket,
			    'g', "dustGlowstone"));
		
		if(ModConfig.enderSlate_recipe)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemEnderSlate.FULLY_CHARGED.copy(),
				"rer",
			    "bsb",
			    "rer",
			    'e', Items.ender_pearl,
			    'b', Items.dragon_breath,
			    'r', "dustRedstone",
			    's', "stone"));
		
		if(ModConfig.enderSlate_enderEyeRechargeRecipe)
			GameRegistry.addRecipe(new RecipeEnderSlateRecharge());
	}
	
	private static void initBaubles(){
		if(ModConfig.baubles_ring_of_sunlight)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunlightRing.FULLY_CHARGED.copy(),
				"ysy",
			    "t t",
			    "yty",
			    't', titaniumIngot,
			    's', ModItems.itemSunstone,
			    'y', Items.string));

		if(ModConfig.baubles_ring_of_saturation)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSaturationRing.FULLY_CHARGED.copy(),
				"ygy",
			    "t t",
			    "yty",
			    't', titaniumIngot,
			    'g', Items.golden_carrot,
			    'y', Items.string));

		if(ModConfig.baubles_amulet_of_breath)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemBreathAmulet.FULLY_CHARGED.copy(),
				"yty",
			    "t t",
			    "ypy",
			    't', titaniumIngot,
			    'p', new ItemStack(Items.potionitem,1,8269),
			    'y', Items.string));

		if(ModConfig.baubles_ring_of_charging)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemChargingRing.FULLY_CHARGED.copy(),
				"yry",
			    "gsg",
			    "ygy",
			    'r', "blockRedstone",
			    'g', "dustGlowstone",
			    's', ModItems.itemSunlightRing,
			    'y', Items.string));
	}
	
	private static void initTitaniumTools(){
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumPickaxe),
				"ttt",
			    " s ",
			    " s ",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumAxe),
				"tt",
			    "ts",
			    " s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumSpade),
				"t",
			    "s",
			    "s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumHoe),
				"tt",
			    " s",
			    " s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumSword),
				"t",
			    "t",
			    "s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemTitaniumSword),
				"t",
			    "t",
			    "s",
			    't', ModItems.ingotTitanium,
			    's', "stickWood"));
	}
}
