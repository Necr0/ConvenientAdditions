package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ISeedBoxItemBehaviourRegistryEntry {
    public boolean hasSpecialBehaviour(ItemStack stack);

    public void getDiscriminators(ItemStack stack, List<Long> behaviours);
}
