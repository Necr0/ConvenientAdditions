package convenientadditions.api.inventory.stackhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

public class CombinedItemStackHandler implements IItemHandler {
	public ArrayList<IItemHandler> handlers;
	
	public CombinedItemStackHandler(ArrayList<IItemHandler> handlers) {
		this.handlers=handlers;
	}

	@Override
	public int getSlots(){
		int tmp=0;
		for(IItemHandler h:handlers){
			tmp+=h.getSlots();
		}
		return tmp;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		int currentBase=0;
		//Map slots
		for(IItemHandler h:handlers){
			if(currentBase+h.getSlots()<=slot)
				currentBase+=h.getSlots();
			else{
				return h.getStackInSlot(slot-currentBase);
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		int currentBase=0;
		//Map slots
		for(IItemHandler h:handlers){
			if(currentBase+h.getSlots()<=slot)
				currentBase+=h.getSlots();
			else{
				return h.insertItem(slot-currentBase, stack, simulate);
			}
		}
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		int currentBase=0;
		//Map slots
		for(IItemHandler h:handlers){
			if(currentBase+h.getSlots()<=slot)
				currentBase+=h.getSlots();
			else{
				return h.extractItem(slot-currentBase, amount, simulate);
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int getSlotLimit(int slot) {

		int currentBase=0;
		//Map slots
		for(IItemHandler h:handlers){
			if(currentBase+h.getSlots()<=slot)
				currentBase+=h.getSlots();
			else{
				return h.getSlotLimit(slot);
			}
		}
		return 64;
	}
}
