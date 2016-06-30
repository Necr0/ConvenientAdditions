package convenientadditions.api.registry.compost;

import java.util.ArrayList;
import java.util.List;

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
}
