package convenientadditions.api.registry.seedbox;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SeedBoxItemBehaviourRegistry {
    public static final List<ISeedBoxItemBehaviourRegistryEntry> REGISTRY = new ArrayList<ISeedBoxItemBehaviourRegistryEntry>();

    public static void addEntry(ISeedBoxItemBehaviourRegistryEntry entry) {
        REGISTRY.add(entry);
    }

    public static void addItemBehaviour(ItemStack stack, String discriminator) {
        REGISTRY.add(new SeedBoxEntitySpecialItemEntry(stack, discriminator));
    }

    public static void addItemBehaviour(ItemStack stack, String discriminator, boolean ignoreDamage, boolean ignoreNBT) {
        REGISTRY.add(new SeedBoxEntitySpecialItemEntry(stack, discriminator, ignoreDamage, ignoreNBT));
    }

    public static List<String> getItemBehaviour(ItemStack stack) {
        ArrayList<String> ret = new ArrayList<>();
        for (ISeedBoxItemBehaviourRegistryEntry e : REGISTRY) {
            if (e.hasSpecialBehaviour(stack))
                e.getDiscriminators(stack, ret);
        }
        return ret;
    }
}
