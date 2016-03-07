package convenientadditions.init;

import net.minecraft.item.ItemStack;
import convenientadditions.api.registry.behaviour.BehaviourRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.entity.behaviour.BehaviourCompost;

public class ModCAAPI {
	public static Long compostDiscriminator;
	
	public static void init(){
		//register behaviours
		compostDiscriminator=BehaviourRegistry.addBehaviour(new BehaviourCompost());
		//register seed box
		SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), compostDiscriminator);
		//SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(Items.feather), BehaviourRegistry.API_DISCRIMINATORS.get("floaty"));
	}
}
