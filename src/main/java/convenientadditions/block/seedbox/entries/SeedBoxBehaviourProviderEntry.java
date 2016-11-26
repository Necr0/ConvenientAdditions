package convenientadditions.block.seedbox.entries;

import convenientadditions.api.item.IBehaviourProvider;
import convenientadditions.api.registry.seedbox.ISeedBoxItemBehaviourRegistryEntry;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SeedBoxBehaviourProviderEntry implements ISeedBoxItemBehaviourRegistryEntry {

    @Override
    public boolean hasSpecialBehaviour(ItemStack stack) {
        if (stack.getItem() instanceof IBehaviourProvider)
            return true;
        return false;
    }

    @Override
    public void getDiscriminators(ItemStack stack, List<Long> behaviours) {
        if (stack.getItem() instanceof IBehaviourProvider)
            ((IBehaviourProvider) stack.getItem()).getBehaviours(stack, behaviours);
    }
}
