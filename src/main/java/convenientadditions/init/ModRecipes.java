package convenientadditions.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cub3d.api.Cub3dCraftingManager;
import cub3d.api.matchers.IItemMatcher;
import cub3d.api.matchers.TypeMatcher;
import cub3d.api.recipes.ShapedCub3dRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	public static void init(){
		IItemMatcher n=null;
		IItemMatcher g=new TypeMatcher(Items.gold_ingot);
		IItemMatcher s=new TypeMatcher(ItemBlock.getItemFromBlock(Blocks.stone));
		IItemMatcher p=new TypeMatcher(ItemBlock.getItemFromBlock(Blocks.stone_pressure_plate));
		Cub3dCraftingManager.getInstance().addRecipe(
				new ShapedCub3dRecipe(
					new IItemMatcher[][][]{
						new IItemMatcher[][]{
								new IItemMatcher[]{
									n,n,n
								},
								new IItemMatcher[]{
									n,p,n
								},
								new IItemMatcher[]{
									n,n,n
								}
						},
						new IItemMatcher[][]{
								new IItemMatcher[]{
									n,g,n
								},
								new IItemMatcher[]{
									g,new TypeMatcher(Items.skull),g
								},
								new IItemMatcher[]{
									n,g,n
								}
						},
						new IItemMatcher[][]{
								new IItemMatcher[]{
									s,s,s
								},
								new IItemMatcher[]{
									s,new TypeMatcher(ItemBlock.getItemFromBlock(Blocks.redstone_block)),s
								},
								new IItemMatcher[]{
									s,s,s
								}
						}
				}
				, new ItemStack(ModBlocks.playerInterfaceBlock)
			)
		);
		
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
			    'c', new ItemStack(ModItems.itemCompost)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostSoilBlock),
				"cc",
			    "cc",
			    'c', new ItemStack(ModItems.itemCompost,1,1)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.dirt),
				"dd",
			    "dd",
			    'd', new ItemStack(ModItems.itemDirtChunk)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), new ItemStack(Blocks.dirt), new ItemStack(ModItems.itemCompost)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.compostSoilBlock), new ItemStack(Blocks.dirt), new ItemStack(ModItems.itemCompost,1,1)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.dirt), new ItemStack(ModBlocks.compostSoilBlock)));
	}
}
