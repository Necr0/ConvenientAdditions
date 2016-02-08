package convenientadditions.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init(){
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
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.powderKegBlock),
				"psp",
			    "pgp",
			    "psp",
			    'p', "plankWood",
			    's', "slabWood",
			    'g', Items.gunpowder));

		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunstone.FULLY_CHARGED.copy(),
				"grg",
			    "tdt",
			    "grg",
			    't', "ingotTitanium",
			    'd', "gemDiamond",
			    'r', "dustRedstone",
			    'g', "dustGlowstone"));

		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSunlightRing.FULLY_CHARGED.copy(),
				"ysy",
			    "t t",
			    "yty",
			    't', "ingotTitanium",
			    's', ModItems.itemSunstone,
			    'y', Items.string));

		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemSaturationRing.FULLY_CHARGED.copy(),
				"ygy",
			    "t t",
			    "yty",
			    't', "ingotTitanium",
			    'g', Items.golden_carrot,
			    'y', Items.string));

		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.itemBreathAmulet.FULLY_CHARGED.copy(),
				"yty",
			    "t t",
			    "ypy",
			    't', "ingotTitanium",
			    'p', new ItemStack(Items.potionitem,1,8269),
			    'y', Items.string));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.itemRedstonePulseEmitter),
				"e",
				"r",
			    "t",
			    't', "ingotTitanium",
			    'r', Blocks.redstone_torch,
			    'e', Items.ender_pearl));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
				"tpt",
			    "geg",
			    "srs",
			    't', "ingotTitanium",
			    'e', Items.ender_pearl,
			    'p', Blocks.stone_pressure_plate,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.proximitySensorBlock),
				"sgs",
			    "tet",
			    "rgr",
			    't', "ingotTitanium",
			    'e', Items.ender_eye,
			    'g', "ingotGold",
			    's', "dustGlowstone",
			    'r', "blockRedstone"));
		
		//TITANIUM TOOLS
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
		
		GameRegistry.addSmelting(ModBlocks.oreTitaniumBlock, new ItemStack(ModItems.ingotTitanium), 1.0F);
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.nuggetTitanium,9), new ItemStack(ModItems.ingotTitanium)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTitanium,1),
				"ttt",
				"ttt",
				"ttt",
				't', new ItemStack(ModItems.nuggetTitanium)));
	}
}
