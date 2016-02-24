package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemDoublePlant;
import net.minecraft.item.ItemStack;

public class CompostRegistryEntryDoublePlant implements ICompostRegistryEntry{

	@Override
	public boolean doesMatch(ItemStack stack) {
		return stack.getItem() instanceof ItemDoublePlant;
	}

	@Override
	public int getCompostingMass(ItemStack stack) {
		return 350;
	}
}
