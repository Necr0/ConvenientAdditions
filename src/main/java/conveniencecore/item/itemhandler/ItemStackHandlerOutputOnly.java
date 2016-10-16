package conveniencecore.item.itemhandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerOutputOnly extends ItemStackHandler implements IItemHandlerModifiable {
	
    public ItemStackHandlerOutputOnly(int slots)
    {
        super(slots);
    }
	
	
    public ItemStackHandlerOutputOnly()
    {
        super(1);
    }
    
    public ItemStack[] getStacks(){
    	return this.stacks;
    }
    
    public void setStacks(ItemStack[] stacks){
    	this.stacks=stacks;
    	for(int i=0;i<getSlots();i++)
    		onContentsChanged(i);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate){return stack;}
}
