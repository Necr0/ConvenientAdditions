package convenientadditions.api.registry.compost;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CompostRegistry {
	public static final List<ICompostRegistryEntry> REGISTRY=new ArrayList<ICompostRegistryEntry>();
	private static boolean registered=false;
	
	public static void addEntry(ICompostRegistryEntry entry){
		REGISTRY.add(entry);
	}
	
	public static void addCompostingItem(ItemStack stack,int mass){
		addEntry(new CompostRegistryEntry(stack, mass));
	}
	
	public static void addCompostingItem(ItemStack stack,int mass,boolean ignoreDamage,boolean ignoreNBT){
		addEntry(new CompostRegistryEntry(stack, mass, ignoreDamage, ignoreNBT));
	}
	
	public static void addCompostingOre(String name,int mass){
		addEntry(new CompostRegistryEntryOre(name, mass, false));
	}
	
	public static void addCompostingOre(String name,int mass,boolean startsWith){
		addEntry(new CompostRegistryEntryOre(name, mass, startsWith));
	}
	
	public static int getCompostingMass(ItemStack stack){
		for(ICompostRegistryEntry e:REGISTRY){
			if(e.doesMatch(stack))
				return e.getCompostingMass(stack);
		}
		return 0;
	}
	
	public static void init(){
		if(!registered){
			initItems();
			registered=true;
		}
	}
	
	private static void initItems(){
		addEntry(new CompostRegistryEntryCompostable());
		addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		addCompostingItem(new ItemStack(Blocks.cactus), 500);
		addCompostingItem(new ItemStack(Blocks.hay_block), 10700);
		addCompostingItem(new ItemStack(Items.reeds), 250);
		addCompostingItem(new ItemStack(Items.cake), 2400);
		addCompostingItem(new ItemStack(Blocks.vine), 300);
		addCompostingItem(new ItemStack(Blocks.red_flower), 250);
		addCompostingItem(new ItemStack(Blocks.yellow_flower), 250);
		addCompostingItem(new ItemStack(Items.feather), 110);
		addCompostingItem(new ItemStack(Blocks.pumpkin), 1400);
		addCompostingItem(new ItemStack(Items.egg), 230);
		addCompostingItem(new ItemStack(Items.slime_ball), 130);
		addCompostingItem(new ItemStack(Items.wheat_seeds), 170);
		addCompostingItem(new ItemStack(Items.melon_seeds), 100);
		addCompostingItem(new ItemStack(Items.pumpkin_seeds), 100);
		addCompostingItem(new ItemStack(Blocks.red_mushroom), 400);
		addCompostingItem(new ItemStack(Blocks.brown_mushroom), 400);
		addCompostingItem(new ItemStack(Blocks.waterlily), 400);
		addCompostingItem(new ItemStack(Items.nether_wart), 1100);
		addCompostingItem(new ItemStack(Items.dye), 227);
		addCompostingItem(new ItemStack(Items.bone), 230);
		addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		addCompostingItem(new ItemStack(Blocks.tallgrass), 230);
		addEntry(new CompostRegistryEntryDoublePlant());;
		addEntry(new CompostRegistryEntryFood());
		addCompostingOre("crop", 1200, true);
		addCompostingOre("treeLeaves", 200, true);
		addCompostingOre("treeSapling", 800, true);
		addCompostingOre("seed", 130, true);
		addCompostingOre("crop", 1200, true);
	}
}
