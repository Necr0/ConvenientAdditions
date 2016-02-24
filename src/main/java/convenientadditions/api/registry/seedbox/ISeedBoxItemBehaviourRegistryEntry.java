package convenientadditions.api.registry.seedbox;

import convenientadditions.api.entity.IEntitySpecialItemBehaviour;
import net.minecraft.item.ItemStack;

public interface ISeedBoxItemBehaviourRegistryEntry {
	public boolean hasSpecialBehaviour(ItemStack stack);
	public IEntitySpecialItemBehaviour getSpecialBehaviour(ItemStack stack);
}
