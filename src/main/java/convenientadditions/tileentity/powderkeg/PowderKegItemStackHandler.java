package convenientadditions.tileentity.powderkeg;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class PowderKegItemStackHandler extends ItemStackHandler {

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
