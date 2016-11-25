package convenientadditions.block.seedbox.entries;

import convenientadditions.api.item.IBehaviourProvider;
import convenientadditions.api.registry.seedbox.ISeedBoxItemBehaviourRegistryEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SeedBoxBehaviourProviderEntry implements ISeedBoxItemBehaviourRegistryEntry {

    @Override
    public boolean hasSpecialBehaviour(ItemStack stack) {
        Item i = stack.getItem();
        if (i != null && i instanceof IBehaviourProvider)
            return true;
        return false;
    }

    @Override
    public void getDiscriminators(ItemStack stack, List<Long> behaviours) {
        Item i = stack.getItem();
        if (i != null && i instanceof IBehaviourProvider)
            ((IBehaviourProvider) i).getBehaviours(stack, behaviours);
    }
}
