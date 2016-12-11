package convenientadditions.api.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFakeWithAmount extends SlotItemHandler implements IFakeSlot {

    public SlotFakeWithAmount(IItemHandler handler, int index, int xPosition, int yPosition) {
        super(handler, index, xPosition, yPosition);
    }

    @Override
    public ItemStack slotClick(Container container, int button, ClickType mode, EntityPlayer player) {
        int adder=0;
        InventoryPlayer inventoryplayer = player.inventory;
        ItemStack held=inventoryplayer.getItemStack();

        if(mode==ClickType.THROW){
            if(button==0)
                slotAdd(-1);
            else
                slotAdd(-64);

        }else if(mode==ClickType.SWAP){
            return ItemStack.EMPTY;

        }else if((mode==ClickType.PICKUP||mode==ClickType.PICKUP_ALL)&&(button==0||button==1)){
            ItemStack stack=getStack();
            if(!stack.isEmpty()&&isItemValid(held)){
                if(button==0){
                    adder=held.getCount();
                }else if(button==1){
                    adder=1;
                }
                slotAdd(adder);
            }else if(!held.isEmpty()){
                this.putStack(held.copy());
            }else if(!stack.isEmpty()&&held.isEmpty()){
                adder=button==0?1:-1;
                slotAdd(adder);
            }

        }else if((mode==ClickType.QUICK_MOVE)&&(button==0||button==1)){
            adder=button==0?8:-8;
            slotAdd(adder);
        }
        container.detectAndSendChanges();
        return ItemStack.EMPTY;
    }

    public void slotAdd(int amount){
        ItemStack stack=this.getStack();
        if(stack.isEmpty())
            return;
        stack.grow(amount);
        if(stack.getCount()>this.getSlotStackLimit())
            stack.setCount(this.getSlotStackLimit());
        if(stack.getCount()>stack.getMaxStackSize())
            stack.setCount(stack.getMaxStackSize());
        if(stack.getCount()<1)
            this.putStack(ItemStack.EMPTY);
        else
            this.putStack(stack.copy());
    }
}