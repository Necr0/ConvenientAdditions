package convenientadditions.init;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ModThaumcraftAspects {
	public static void init(){
		ThaumcraftApi.registerObjectTag("oreTitanium", new AspectList().add(Aspect.METAL,4).add(Aspect.EARTH,1));
		ThaumcraftApi.registerObjectTag("ingotTitanium", new AspectList().add(Aspect.METAL,4));
		ThaumcraftApi.registerObjectTag("nuggetTitanium", new AspectList().add(Aspect.METAL,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.composterBlock), new AspectList().add(Aspect.TREE,5).add(Aspect.EXCHANGE,2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.playerInterfaceBlock), new AspectList().add(Aspect.MAN,1).add(Aspect.MECHANISM,2).add(Aspect.VOID,2).add(Aspect.TRAVEL,4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.proximitySensorBlock), new AspectList().add(Aspect.MAN,1).add(Aspect.MECHANISM,2).add(Aspect.SENSES,2).add(Aspect.ENERGY,4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.compostSoilBlock), new AspectList().add(Aspect.EARTH,1).add(Aspect.CROP,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.compostSoilTilledBlock), new AspectList().add(Aspect.EARTH,1).add(Aspect.CROP,1).add(Aspect.HARVEST,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.powderKegBlock), new AspectList().add(Aspect.TREE,6).add(Aspect.FIRE,6).add(Aspect.ENTROPY,6).add(Aspect.VOID,2));
		ThaumcraftApi.registerObjectTag("chunkCompost", new AspectList().add(Aspect.EARTH,1).add(Aspect.CROP,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemDirtChunk), new AspectList().add(Aspect.EARTH,1));
		//ttools
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemTitaniumAxe), new AspectList().add(Aspect.METAL,3).add(Aspect.TOOL,2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemTitaniumPickaxe), new AspectList().add(Aspect.METAL,10).add(Aspect.MINE,3).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemTitaniumSpade), new AspectList().add(Aspect.METAL,4).add(Aspect.TOOL,3).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemTitaniumAxe), new AspectList().add(Aspect.METAL,10).add(Aspect.TOOL,3).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemTitaniumHoe), new AspectList().add(Aspect.METAL,7).add(Aspect.HARVEST,3).add(Aspect.HARVEST,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemTitaniumSword), new AspectList().add(Aspect.METAL,7).add(Aspect.WEAPON,3));
		//chargable
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemSunstone), new AspectList().add(Aspect.LIGHT,12).add(Aspect.FIRE,8).add(Aspect.CRYSTAL,3).add(Aspect.GREED,3).add(Aspect.ENERGY, 6).add(Aspect.SENSES,2).add(Aspect.METAL,6).add(Aspect.AURA, 2));
		//baubles
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemSunlightRing), new AspectList().add(Aspect.LIGHT,12).add(Aspect.FIRE,8).add(Aspect.CRYSTAL,3).add(Aspect.GREED,3).add(Aspect.ENERGY, 6).add(Aspect.SENSES,2).add(Aspect.METAL,9).add(Aspect.AURA, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemSaturationRing), new AspectList().add(Aspect.GREED,2).add(Aspect.HUNGER,8).add(Aspect.METAL,6));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemBreathAmulet), new AspectList().add(Aspect.GREED,1).add(Aspect.WATER,4).add(Aspect.AIR,8).add(Aspect.METAL,2));
		//
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemCompost), new AspectList().add(Aspect.EARTH,1).add(Aspect.CROP,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemFertilizer), new AspectList().add(Aspect.PLANT,1).add(Aspect.CROP,1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.itemRedstonePulseEmitter), new AspectList().add(Aspect.ENERGY,4).add(Aspect.METAL,2).add(Aspect.MECHANISM,2).add(Aspect.AURA, 2));
	}
}
