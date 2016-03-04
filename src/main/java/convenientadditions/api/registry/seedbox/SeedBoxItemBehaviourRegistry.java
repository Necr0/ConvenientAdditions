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
	
	public static void addItemBehaviour(ItemStack stack,long discriminator){
		REGISTRY.add(new SeedBoxEntitySpecialItemEntry(stack,discriminator));
	}
	
	public static void addItemBehaviour(ItemStack stack,long discriminator,boolean ignoreDamage,boolean ignoreNBT){
		REGISTRY.add(new SeedBoxEntitySpecialItemEntry(stack,discriminator,ignoreDamage,ignoreNBT));
	}
	
	public static List<Long> getItemBehaviour(ItemStack stack){
		ArrayList<Long> ret=new ArrayList<Long>();
		for(ISeedBoxItemBehaviourRegistryEntry e:REGISTRY){
			if(e.hasSpecialBehaviour(stack))
				ret.add(e.getDiscriminator(stack));
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
