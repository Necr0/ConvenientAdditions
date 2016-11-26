package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ISeedBoxItemBehaviourRegistryEntry {
    boolean hasSpecialBehaviour(ItemStack stack);

    void getDiscriminators(ItemStack stack, List<Long> behaviours);
}
