package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemStack;
import convenientadditions.api.item.ICompostable;

public class CompostRegistryEntryCompostable implements ICompostRegistryEntry{
	@Override
	public boolean doesMatch(ItemStack stack) {
		return stack.getItem() instanceof ICompostable&&((ICompostable)stack.getItem()).isCompostable(stack);
	}

	@Override
	public int getCompostingMass(ItemStack stack) {
		return ((ICompostable)stack.getItem()).getCompostingMass(stack);
	}
}
