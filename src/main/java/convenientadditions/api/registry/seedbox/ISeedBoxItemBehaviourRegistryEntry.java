package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;

public interface ISeedBoxItemBehaviourRegistryEntry {
	public boolean hasSpecialBehaviour(ItemStack stack);
	public long getDiscriminator(ItemStack stack);
}
