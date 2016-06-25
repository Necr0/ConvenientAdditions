package convenientadditions.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOredict {
	public static void registerOres(){
		OreDictionary.registerOre("oreTitanium", ModBlocks.oreTitaniumBlock);
		OreDictionary.registerOre("ingotTitanium", ModItems.ingotTitanium);
		OreDictionary.registerOre("nuggetTitanium", ModItems.nuggetTitanium);

		OreDictionary.registerOre("soil", ModBlocks.compostSoilBlock);
		OreDictionary.registerOre("soil", Blocks.DIRT);
		OreDictionary.registerOre("soilCompost", ModBlocks.compostSoilBlock);
		OreDictionary.registerOre("soilDirt", Blocks.DIRT);

		OreDictionary.registerOre("chunkCompost", new ItemStack(ModItems.itemCompost,1,0));
		OreDictionary.registerOre("chunkCompost", new ItemStack(ModItems.itemCompost,1,1));
		
		OreDictionary.registerOre("chunkDirt", new ItemStack(ModItems.itemDirtChunk,1,0));
	}
}
