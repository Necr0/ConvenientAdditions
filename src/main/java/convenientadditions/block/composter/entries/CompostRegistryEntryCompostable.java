package convenientadditions.block.composter.entries;

import convenientadditions.api.item.ICompostable;
import convenientadditions.api.registry.compost.ICompostRegistryEntry;
import net.minecraft.item.ItemStack;

public class CompostRegistryEntryCompostable implements ICompostRegistryEntry {
    @Override
    public boolean doesMatch(ItemStack stack) {
        return stack.getItem() instanceof ICompostable && ((ICompostable) stack.getItem()).isCompostable(stack);
    }

    @Override
    public int getCompostingMass(ItemStack stack) {
        return ((ICompostable) stack.getItem()).getCompostingMass(stack);
    }
}
