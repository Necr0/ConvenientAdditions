package convenientadditions.block.itemReceiver;

import conveniencecore.gui.container.CCContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerItemReceiver extends CCContainerBase {

	public TileEntityItemReceiver te;
	
	public ContainerItemReceiver(TileEntityItemReceiver ent,EntityPlayer p) {
		te=ent;
		//input
		for(int i=0;i<3;i++){
			addSlotToContainer(new SlotItemHandler(ent.channels, i, i*18+62, 8));
		}
		//player inventory
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p.inventory, j + i * 9 + 9, 8 + j * 18, 32 + i * 18));
            }
        }
		//player hotbar
        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(p.inventory, k, 8 + k * 18, 90));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < 3) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 3, 39, true))
	                return null;
	        } else {
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 3, false))
	                return null;
	        }

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}

}
