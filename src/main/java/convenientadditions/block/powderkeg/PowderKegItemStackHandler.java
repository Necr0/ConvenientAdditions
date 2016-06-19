package convenientadditions.block.powderkeg;

import conveniencecore.block.tileentity.ItemStackHandlerAutoSave;
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
        if(stack.getItem()==Items.gunpowder)
        	super.setStackInSlot(slot, stack);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        if(stack.getItem()==Items.gunpowder)
        	return super.insertItem(slot, stack, simulate);
        else
        	return stack;
    }

}
