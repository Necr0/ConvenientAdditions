package convenientadditions.init;

import conveniencecore.entity.behaviour.BehaviourRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.entity.behaviour.BehaviourCompost;
import net.minecraft.item.ItemStack;

public class ModCAAPI {
	public static Long compostDiscriminator;
	
	public static void init(){
		//register behaviours
		compostDiscriminator=BehaviourRegistry.addBehaviour(new BehaviourCompost());
		//register seed box
		SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), compostDiscriminator);
	}
}
