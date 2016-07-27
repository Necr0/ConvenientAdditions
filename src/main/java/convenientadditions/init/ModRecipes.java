package convenientadditions.init;

import convenientadditions.item.charge.ItemBlazingRock;
import convenientadditions.item.charge.ItemSunstone;
import convenientadditions.item.charge.baubles.ItemBreathAmulet;
import convenientadditions.item.charge.baubles.ItemChargingRing;
import convenientadditions.item.charge.baubles.ItemSaturationRing;
import convenientadditions.item.charge.baubles.ItemSunlightRing;
import convenientadditions.item.charge.enderSlate.ItemEnderSlate;
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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemIronWrench,1),
				"i i",
				" i ",
				"i i",
				'i', "ingotIron"));
		
		if(ModConfig.transmutationTome_recipe)
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemTransmutationTome,1), Items.BOOK, Items.BLAZE_ROD, Items.ENDER_EYE, Items.WHEAT_SEEDS));
		
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
		initInventoryProxies();
		initChannelModules();
		initTreeTap();
		initBlocks();
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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.DIRT),
				"dd",
			    "dd",
			    'd', "chunkDirt"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), Blocks.DIRT, "chunkCompost"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.DIRT), ModBlocks.compostSoilBlock));
	}
	
	private static void initBlocks(){
		if(ModConfig.powderKeg)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.powderKegBlock),
				"psp",
			    "pgp",
			    "psp",
			    'p', "plankWood",
			    's', "slabWood",
			    'g', Items.GUNPOWDER));

		if(ModConfig.seedBox_recipe)
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.seedBoxBlock,
				"tpt",
			    "php",
			    "tpt",
			    'h', Blocks.HOPPER,
			    'p', "plankWood",
			    't', titaniumIngot));

		if(ModConfig.playerInterface)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
				"tpt",
			    "geg",
			    "srs",
			    't', titaniumIngot,
			    'e', Items.ENDER_PEARL,
			    'p', Blocks.STONE_PRESSURE_PLATE,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));

		if(ModConfig.proximitySensor)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
				"sgs",
			    "tet",
			    "rgr",
			    't', titaniumIngot,
			    'e', Items.ENDER_EYE,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));

		if(ModConfig.setProvider)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.setProviderBlock),
				"tht",
			    "bcb",
			    "tht",
			    't', titaniumIngot,
			    'h', Blocks.HOPPER,
			    'c', Items.COMPARATOR,
			    'b', Blocks.IRON_BARS));
	}

	private static void initChargeItems(){
		if(ModConfig.charge_sunstone)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemSunstone.FULLY_CHARGED.copy(),
				"grg",
			    "tdt",
			    "grg",
			    't', titaniumIngot,
			    'd', "gemDiamond",
			    'r', "dustRedstone",
			    'g', "dustGlowstone"));
		
		if(ModConfig.charge_blazingRock)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBlazingRock.FULLY_CHARGED.copy(),
				"tlt",
			    "bdb",
			    "tgt",
			    't', titaniumNugget,
			    'b', Items.BLAZE_POWDER,
			    'd', "gemDiamond",
			    'l', Items.LAVA_BUCKET,
			    'g', "dustGlowstone"));
		
		if(ModConfig.enderSlate_recipe)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemEnderSlate.FULLY_CHARGED.copy(),
				"rer",
			    "bsb",
			    "rer",
			    'e', Items.ENDER_PEARL,
			    'b', Items.DRAGON_BREATH,
			    'r', "dustRedstone",
			    's', "stone"));
		
		if(ModConfig.enderSlate_enderEyeRechargeRecipe)
			GameRegistry.addRecipe(new RecipeEnderSlateRecharge());
	}
	
	private static void initBaubles(){
		if(ModConfig.baubles_ring_of_sunlight)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemSunlightRing.FULLY_CHARGED.copy(),
				"ysy",
			    "t t",
			    "yty",
			    't', titaniumIngot,
			    's', ModItems.itemSunstone,
			    'y', Items.STRING));

		if(ModConfig.baubles_ring_of_saturation)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemSaturationRing.FULLY_CHARGED.copy(),
				"ygy",
			    "t t",
			    "yty",
			    't', titaniumIngot,
			    'g', Items.GOLDEN_CARROT,
			    'y', Items.STRING));

		if(ModConfig.baubles_amulet_of_breath)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemBreathAmulet.FULLY_CHARGED.copy(),
				"yty",
			    "t t",
			    "ypy",
			    't', titaniumIngot,
			    'p', new ItemStack(Items.POTIONITEM,1,0),
			    'y', Items.STRING));

		if(ModConfig.baubles_ring_of_charging)
		GameRegistry.addRecipe(new ShapedOreRecipe(ItemChargingRing.FULLY_CHARGED.copy(),
				"yry",
			    "gsg",
			    "ygy",
			    'r', "blockRedstone",
			    'g', "dustGlowstone",
			    's', ModItems.itemSunlightRing,
			    'y', Items.STRING));
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
	
	private static void initInventoryProxies(){
		if(ModConfig.inventoryProxies_regular)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.inventoryProxyBlock,4),
				"shs",
			    "pcp",
			    "shs",
			    's', "stickWood",
			    'p', "plankWood",
			    'h', Blocks.HOPPER,
			    'c', Blocks.CHEST));
		
		if(ModConfig.inventoryProxies_sided)
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxySidedBlock),ModBlocks.inventoryProxyBlock));
		if(ModConfig.inventoryProxies_regular)
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxyBlock),ModBlocks.inventoryProxySidedBlock));
		if(ModConfig.inventoryProxies_filtered)
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.inventoryProxyFilteredBlock),ModBlocks.inventoryProxyBlock,Items.COMPARATOR));
		
		if(ModConfig.inventoryProxies_transmitter)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemTransmitterBlock,1),
				"tot",
			    "epe",
			    "tht",
			    't', titaniumIngot,
			    'e', Items.ENDER_EYE,
			    'h', Blocks.HOPPER,
			    'p', ModBlocks.inventoryProxyBlock,
			    'o', ModItems.itemObsidianSlate));
		
		if(ModConfig.inventoryProxies_receiver)
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.itemReceiverBlock,1),
				"gog",
			    "epe",
			    "ghg",
			    'g', "ingotGold",
			    'e', Items.ENDER_EYE,
			    'h', Blocks.HOPPER,
			    'p', ModBlocks.inventoryProxyBlock,
			    'o', ModItems.itemObsidianSlate));
	}
	
	private static void initChannelModules(){
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemObsidianSlate,4),Blocks.OBSIDIAN,Blocks.STONE));
		if(ModConfig.channelModules_player)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModulePlayer),ModItems.itemObsidianSlate,Items.ENDER_EYE,new ItemStack(Items.SKULL,1,1)));
		if(ModConfig.channelModules_color)
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemModuleColor),ModItems.itemObsidianSlate,Items.ENDER_EYE,"dye","dye","dye"));
	}

	private static void initTreeTap(){
		if(ModConfig.treetap)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemSapBottle),Items.GLASS_BOTTLE,new ItemStack(Items.DYE,1,2)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.treetapBlock),"ingotIron","slimeball","stickWood"));
		if(ModConfig.antidote)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.itemAntidote),new ItemStack(Items.POTIONITEM,1,0),"sap",Blocks.RED_MUSHROOM,Items.BEETROOT));
	}
}
