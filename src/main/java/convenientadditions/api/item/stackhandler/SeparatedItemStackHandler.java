package convenientadditions.api.item.stackhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SeparatedItemStackHandler implements IItemHandler {
	IItemHandler in;
	IItemHandler out;
	
	public SeparatedItemStackHandler(IItemHandler input,IItemHandler output) {
		this.in=input;
		this.out=output;
	}
	
	
	@Override
	public int getSlots() {
		return in.getSlots()+out.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return !isInput(slot)?out.getStackInSlot(slot-in.getSlots()):in.getStackInSlot(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return isInput(slot)?in.insertItem(slot,stack,simulate):stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return !isInput(slot)?out.extractItem(slot-in.getSlots(),amount,simulate):null;
	}

	public boolean isInput(int slot){
		return slot<in.getSlots();
	}
}
