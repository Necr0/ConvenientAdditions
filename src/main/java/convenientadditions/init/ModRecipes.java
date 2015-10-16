package convenientadditions.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cub3d.api.matchers.IItemMatcher;
import cub3d.api.matchers.TypeMatcher;

public class ModRecipes {
	public static void init(){
		IItemMatcher n=null;
		IItemMatcher g=new TypeMatcher(Items.gold_ingot);
		IItemMatcher s=new TypeMatcher(ItemBlock.getItemFromBlock(Blocks.stone));
		IItemMatcher p=new TypeMatcher(ItemBlock.getItemFromBlock(Blocks.stone_pressure_plate));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.frameBlock,3),
				"lbl",
			    "b b",
			    "lbl",
			    'l', "dyeBlue",
			    'b', new ItemStack(Blocks.iron_bars)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.assemblerBlock),
				"lbl",
			    "brb",
			    "lbl",
			    'l', "dyeBlue",
			    'b', new ItemStack(Blocks.iron_bars),
			    'r', new ItemStack(Blocks.redstone_block)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.composterBlock),
				"s s",
			    "s s",
			    "ppp",
			    's', "slabWood",
			    'p', "plankWood"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostSoilBlock),
				"cc",
			    "cc",
			    'c', ModItems.itemCompost));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostSoilBlock),
				"cc",
			    "cc",
			    'c', new ItemStack(ModItems.itemCompost,1,1)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.dirt),
				"dd",
			    "dd",
			    'd', new ItemStack(ModItems.itemDirtChunk)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), Blocks.dirt, ModItems.itemCompost));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), Blocks.dirt, new ItemStack(ModItems.itemCompost,1,1)));
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
			    "dld",
			    "grg",
			    'l', Blocks.redstone_lamp,
			    'd', Items.diamond,
			    'd', Items.redstone,
			    'g', Items.glowstone_dust));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.playerInterfaceBlock),
				"epe",
			    "gsg",
			    "srs",
			    'e', Items.ender_pearl,
			    'p', Blocks.stone_pressure_plate,
			    'g', "ingotGold",
			    's', Items.glowstone_dust,
			    'r', Blocks.redstone_block));

	}
}
