package convenientadditions.api.block.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ItemStackHandlerAutoSaveRestricted extends ItemStackHandlerAutoSave {
	public Class<?> c;

	public ItemStackHandlerAutoSaveRestricted(TileEntity tile, int slots, Class<?> c) {
		super(tile, slots);
		this.c=c;
	}

	public ItemStackHandlerAutoSaveRestricted(TileEntity tile, Class<?> c) {
		super(tile);
		this.c=c;
	}

    @Override
    public void setStackInSlot(int slot, ItemStack stack)
    {
    	if(stack==null){
    		super.setStackInSlot(slot, stack);
    		return;
    	}
    	if(c.isInstance(stack.getItem()))
    		super.setStackInSlot(slot, stack);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
    	if(c.isInstance(stack.getItem()))
    		return super.insertItem(slot, stack, simulate);
    	else
    		return stack;
    }

}
