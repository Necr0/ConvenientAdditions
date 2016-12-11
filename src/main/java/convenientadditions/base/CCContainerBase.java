package convenientadditions.base;

import convenientadditions.api.gui.container.IFakeSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class CCContainerBase extends Container {
    @Override
    public ItemStack slotClick(int index, int button, ClickType mode, EntityPlayer player) {
        InventoryPlayer inventoryplayer = player.inventory;
        ItemStack held=inventoryplayer.getItemStack();
        if(index>=0){
	        Slot s=getSlot(index);
	        
	        if(s instanceof IFakeSlot){
				return ((IFakeSlot) s).slotClick(this, button, mode, player);
	        }else if (mode == ClickType.PICKUP_ALL && index >= 0){
	            Slot slot2 = this.inventorySlots.get(index);
	            ItemStack itemstack4 = inventoryplayer.getItemStack();

	            if (!itemstack4.isEmpty() && (slot2 == null || !slot2.getHasStack() || !slot2.canTakeStack(player)))
	            {
	                int i1 = button == 0 ? 0 : this.inventorySlots.size() - 1;
	                int j1 = button == 0 ? 1 : -1;

	                for (int i3 = 0; i3 < 2; ++i3)
	                {
	                    for (int j3 = i1; j3 >= 0 && j3 < this.inventorySlots.size() && itemstack4.getCount() < itemstack4.getMaxStackSize(); j3 += j1)
	                    {
	                        Slot slot8 = this.inventorySlots.get(j3);

	                        if (!(slot8 instanceof IFakeSlot)&&slot8.getHasStack() && canAddItemToSlot(slot8, itemstack4, true) && slot8.canTakeStack(player) && this.canMergeSlot(itemstack4, slot8) && (i3 != 0 || slot8.getStack().getCount() != slot8.getStack().getMaxStackSize()))
	                        {
	                            int l = Math.min(itemstack4.getMaxStackSize() - itemstack4.getCount(), slot8.getStack().getCount());
	                            ItemStack itemstack2 = slot8.decrStackSize(l);
	                            itemstack4.grow(1);

	                            if (itemstack2.getCount() <= 0)
	                            {
	                                slot8.putStack(ItemStack.EMPTY);
	                            }

	                            slot8.onTake(player, itemstack2);
	                        }
	                    }
	                }
	            }
	        	this.detectAndSendChanges();
	    		return ItemStack.EMPTY;
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
}
