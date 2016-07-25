package convenientadditions.block.setProvider;

import conveniencecore.gui.container.CCContainerBase;
import conveniencecore.gui.container.SlotFake;
import conveniencecore.gui.container.SlotOutputOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSetProvider extends CCContainerBase {
	
	public TileEntitySetProvider te;
	
	public ContainerSetProvider(TileEntitySetProvider ent,EntityPlayer p) {
		te=ent;
		//input
		for(int i=0;i<2;i++){
			for(int j=0;j<9;j++){
				addSlotToContainer(new SlotItemHandler(ent.input, i*9+j, j*18+8, i*18+8));
			}
		}
		//filter
		for(int j=0;j<9;j++){
			addSlotToContainer(new SlotFake(ent.filter, j, j*18+8, 50));
		}
		//output
		for(int i=0;i<2;i++){
			for(int j=0;j<9;j++){
				//System.out.println(i*9+j);
				addSlotToContainer(new SlotOutputOnly(ent.output, i*9+j, j*18+8, i*18+74));
			}
		}
		//player inventory
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p.inventory, j + i * 9 + 9, 8 + j * 18, 116 + i * 18));
            }
        }
		//player hotbar
        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(p.inventory, k, 8 + k * 18, 174));
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

	        if (fromSlot < 36) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 36, 72, true))
	                return null;
	        } else {
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 18, false))
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
