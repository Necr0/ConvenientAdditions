package convenientadditions.api.registry.seedbox;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ISeedBoxItemBehaviourRegistryEntry {
	public boolean hasSpecialBehaviour(ItemStack stack);
	public void getDiscriminators(ItemStack stack,List<Long> behaviours);
}
