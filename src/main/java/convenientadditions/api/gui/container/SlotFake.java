package convenientadditions.api.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFake extends SlotItemHandler implements IFakeSlot {

	public SlotFake(IItemHandler handler, int index, int xPosition, int yPosition) {
		super(handler, index, xPosition, yPosition);
	}

	@Override
	public ItemStack slotClick(Container container, int button, ClickType mode, EntityPlayer player) {
		InventoryPlayer inventoryplayer = player.inventory;
		ItemStack held=inventoryplayer.getItemStack().copy();
		if(!held.isEmpty())
			held.setCount(1);

		if(mode==ClickType.THROW){
			this.putStack(ItemStack.EMPTY);

		}else if(mode==ClickType.SWAP){
			return ItemStack.EMPTY;

		}else if((mode==ClickType.PICKUP||mode==ClickType.PICKUP_ALL)&&(button==0||button==1)){
			if(!held.isEmpty()){
				putStack(held);
			}else{
				putStack(ItemStack.EMPTY);
			}
		}else if((mode==ClickType.QUICK_MOVE)&&(button==0||button==1)){
			this.putStack(ItemStack.EMPTY);
		}
		container.detectAndSendChanges();
		return ItemStack.EMPTY;
	}
}
