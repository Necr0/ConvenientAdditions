package convenientadditions.api.registry.seedbox;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;

public class SeedBoxItemBehaviourRegistry {
	public static final List<ISeedBoxItemBehaviourRegistryEntry> REGISTRY=new ArrayList<ISeedBoxItemBehaviourRegistryEntry>();
	private static boolean registered=false;
	
	public static void addEntry(ISeedBoxItemBehaviourRegistryEntry entry){
		REGISTRY.add(entry);
	}
	
	public static void addItemBehaviour(ItemStack stack,IEntitySpecialItemBehaviour behaviour){
		REGISTRY.add(new SeedBoxEntitySpecialItemEntry(stack,behaviour));
	}
	
	public static void addItemBehaviour(ItemStack stack,IEntitySpecialItemBehaviour behaviour,boolean ignoreDamage,boolean ignoreNBT){
		REGISTRY.add(new SeedBoxEntitySpecialItemEntry(stack,behaviour,ignoreDamage,ignoreNBT));
	}
	
	public static List<IEntitySpecialItemBehaviour> getItemBehaviour(ItemStack stack){
		ArrayList<IEntitySpecialItemBehaviour> ret=new ArrayList<IEntitySpecialItemBehaviour>();
		for(ISeedBoxItemBehaviourRegistryEntry e:REGISTRY){
			if(e.hasSpecialBehaviour(stack))
				e.getSpecialBehaviour(stack);
		}
		return ret;
	}
	
	public static void init(){
		if(!registered){
			addEntry(new SeedBoxCropsEntry());
			registered=true;
		}
	}
}
