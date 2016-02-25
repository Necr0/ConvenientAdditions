package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;
import convenientadditions.api.entity.IEntitySpecialItemBehaviour;

public interface ISeedBoxItemBehaviourRegistryEntry {
	public boolean hasSpecialBehaviour(ItemStack stack);
	public IEntitySpecialItemBehaviour getSpecialBehaviour(ItemStack stack);
}
