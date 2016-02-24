package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class CompostRegistryEntryFood implements ICompostRegistryEntry{

	@Override
	public boolean doesMatch(ItemStack stack) {
		return stack.getItem() instanceof ItemFood;
	}

	@Override
	public int getCompostingMass(ItemStack stack) {
		return ((ItemFood)stack.getItem()).func_150905_g(stack)*400;
	}
}
