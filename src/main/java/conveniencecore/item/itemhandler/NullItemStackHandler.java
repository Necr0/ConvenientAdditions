package conveniencecore.item.itemhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class NullItemStackHandler implements IItemHandler {

	@Override
	public int getSlots() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return null;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return null;
	}

}
