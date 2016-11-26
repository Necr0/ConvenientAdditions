package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemStack;

public interface ICompostRegistryEntry {
    boolean doesMatch(ItemStack stack);

    int getCompostingMass(ItemStack stack);
}
