package convenientadditions.api.block.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerAutoSave extends ItemStackHandler implements IItemHandlerModifiable {
	public TileEntity te;
	
    public ItemStackHandlerAutoSave(TileEntity tile,int slots)
    {
        super(slots);
        this.te=tile;
    }
	
	
    public ItemStackHandlerAutoSave(TileEntity tile)
    {
        super(1);
        this.te=tile;
    }
    
    @Override
    protected void onContentsChanged(int slot)
    {
    	te.markDirty();
    }
    
    public ItemStack[] getStacks(){
    	return this.stacks;
    }
    
    public void setStacks(ItemStack[] stacks){
    	this.stacks=stacks;
    	for(int i=0;i<getSlots();i++)
    		onContentsChanged(i);
    }
}
