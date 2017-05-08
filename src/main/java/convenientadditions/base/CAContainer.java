package convenientadditions.base;

import convenientadditions.api.gui.container.IFakeSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class CAContainer extends Container {
    @Override
    public ItemStack slotClick(int index, int button, ClickType mode, EntityPlayer player) {
        InventoryPlayer inventoryplayer = player.inventory;
        ItemStack held=inventoryplayer.getItemStack();
        if(index>=0){
	        Slot s=getSlot(index);
	        if(s instanceof IFakeSlot){
				return ((IFakeSlot) s).slotClick(this, button, mode, player);
	        }
        }
    	return super.slotClick(index, button, mode, player);
    }
    
    public void slotAdd(Slot s, int amount){
        ItemStack stack=s.getStack();
        if(stack.isEmpty())
        	return;
        stack.grow(amount);
        if(stack.getCount()>s.getSlotStackLimit())
        	stack.setCount(s.getSlotStackLimit());
        if(stack.getCount()>stack.getMaxStackSize())
			stack.setCount(stack.getMaxStackSize());
        if(stack.getCount()<1)
        	s.putStack(ItemStack.EMPTY);
        else
        	s.putStack(stack.copy());
    }

	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
	 * is null for the initial slot that was double-clicked.
	 */
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
	{
		return !(slotIn instanceof IFakeSlot);
	}
}
