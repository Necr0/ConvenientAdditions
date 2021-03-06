package convenientadditions.block.misc.composter.entries;

import convenientadditions.api.registry.compost.ICompostRegistryEntry;
import convenientadditions.config.ModConfigMisc;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class CompostRegistryEntryFood implements ICompostRegistryEntry {

    @Override
    public boolean doesMatch(ItemStack stack) {
        return stack.getItem() instanceof ItemFood;
    }

    @Override
    public int getCompostingMass(ItemStack stack) {
        return ((ItemFood) stack.getItem()).getHealAmount(stack) * ModConfigMisc.composter_foodMultiplier;
    }
}
