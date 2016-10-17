package convenientadditions.block.powderkeg;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class PowderKegItemStackHandler extends ItemStackHandlerAutoSave {

    public PowderKegItemStackHandler(TileEntity tile) {
		super(tile);
	}

	@Override
    public void setStackInSlot(int slot, ItemStack stack)
    {
        if(stack.getItem()==Items.GUNPOWDER)
        	super.setStackInSlot(slot, stack);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        if(stack.getItem()==Items.GUNPOWDER)
        	return super.insertItem(slot, stack, simulate);
        else
        	return stack;
    }

}
