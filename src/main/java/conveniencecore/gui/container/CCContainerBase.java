package conveniencecore.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class CCContainerBase extends Container {
    @Override
    public ItemStack slotClick(int index, int button, ClickType mode, EntityPlayer player) {
        int adder=0;
        InventoryPlayer inventoryplayer = player.inventory;
        ItemStack held=inventoryplayer.getItemStack();
        if(index>=0){
	        Slot s=getSlot(index);
	        
	        if(s instanceof SlotFake){
	        	if(mode==ClickType.THROW){
	        		if(button==0)
	        			slotAdd(s,-1);
	        		else
	        			slotAdd(s,-64);
	        	}else if(mode==ClickType.SWAP){
	        		return null;
	        	}else if((mode==ClickType.PICKUP||mode==ClickType.PICKUP_ALL)&&(button==0||button==1)){
		    		ItemStack stack=s.getStack();
		    		if(stack!=null&&held!=null&&s.isItemValid(held)){
		    			if(button==0){
		    				adder=held.stackSize;
		    			}else if(button==1){
		    				adder=1;
		    			}
		    			slotAdd(s, adder);
		    		}else if(held!=null){
		    			s.putStack(held.copy());
		    		}else if(stack!=null&&held==null){
		    			adder=button==0?1:-1;
		    	        slotAdd(s,adder);
		    		}
	    		}else if((mode==ClickType.QUICK_MOVE)&&(button==0||button==1)){
	    			adder=button==0?8:-8;
	    	        slotAdd(s,adder);
	    		}
	        	this.detectAndSendChanges();
	    		return null;
	        }else if (mode == ClickType.PICKUP_ALL && index >= 0){
	            Slot slot2 = (Slot)this.inventorySlots.get(index);
	            ItemStack itemstack4 = inventoryplayer.getItemStack();

	            if (itemstack4 != null && (slot2 == null || !slot2.getHasStack() || !slot2.canTakeStack(player)))
	            {
	                int i1 = button == 0 ? 0 : this.inventorySlots.size() - 1;
	                int j1 = button == 0 ? 1 : -1;

	                for (int i3 = 0; i3 < 2; ++i3)
	                {
	                    for (int j3 = i1; j3 >= 0 && j3 < this.inventorySlots.size() && itemstack4.stackSize < itemstack4.getMaxStackSize(); j3 += j1)
	                    {
	                        Slot slot8 = (Slot)this.inventorySlots.get(j3);

	                        if (!(slot8 instanceof SlotFake)&&slot8.getHasStack() && canAddItemToSlot(slot8, itemstack4, true) && slot8.canTakeStack(player) && this.canMergeSlot(itemstack4, slot8) && (i3 != 0 || slot8.getStack().stackSize != slot8.getStack().getMaxStackSize()))
	                        {
	                            int l = Math.min(itemstack4.getMaxStackSize() - itemstack4.stackSize, slot8.getStack().stackSize);
	                            ItemStack itemstack2 = slot8.decrStackSize(l);
	                            itemstack4.stackSize += l;

	                            if (itemstack2.stackSize <= 0)
	                            {
	                                slot8.putStack((ItemStack)null);
	                            }

	                            slot8.onPickupFromSlot(player, itemstack2);
	                        }
	                    }
	                }
	            }
	        	this.detectAndSendChanges();
	    		return null;
	        }
        }
    	return super.slotClick(index, button, mode, player);
    }
    
    public void slotAdd(Slot s, int amount){
        ItemStack stack=s.getStack();
        if(stack==null)
        	return;
        stack.stackSize+=amount;
        if(stack.stackSize>s.getSlotStackLimit())
        	stack.stackSize=s.getSlotStackLimit();
        if(stack.stackSize>stack.getMaxStackSize())
        	stack.stackSize=stack.getMaxStackSize();
        if(stack.stackSize<1)
        	s.putStack(null);
        else
        	s.putStack(stack.copy());
    }
}
