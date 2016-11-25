package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemStack;

public interface ICompostRegistryEntry {
    public boolean doesMatch(ItemStack stack);

    public int getCompostingMass(ItemStack stack);
}
