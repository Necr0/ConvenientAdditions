package convenientadditions.init;

import net.minecraft.item.ItemStack;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.api.registry.BehaviourRegistry;
import convenientadditions.api.registry.seedbox.SeedBoxItemBehaviourRegistry;
import convenientadditions.entity.behaviour.BehaviourCompost;

public class ModCAAPI {
	public static void init(){
		//register behaviours
		BehaviourRegistry.addBehaviour(ConvenientAdditionsMod.MODID+":"+"compost", new BehaviourCompost());
		//register seed box
		SeedBoxItemBehaviourRegistry.addItemBehaviour(new ItemStack(ModItems.itemCompost), BehaviourRegistry.getBehaviour(ConvenientAdditionsMod.MODID+":"+"compost"));
	}
}
