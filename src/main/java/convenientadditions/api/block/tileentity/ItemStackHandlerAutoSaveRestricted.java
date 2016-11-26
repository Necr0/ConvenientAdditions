package convenientadditions.api.block.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ItemStackHandlerAutoSaveRestricted extends ItemStackHandlerAutoSave {
    public Object i;

    public ItemStackHandlerAutoSaveRestricted(TileEntity tile, int slots, Object i) {
        super(tile, slots);
        this.i = i;
    }

    public ItemStackHandlerAutoSaveRestricted(TileEntity tile, Object i) {
        super(tile);
        this.i = i;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        if (stack == ItemStack.EMPTY) {
            super.setStackInSlot(slot, stack);
            return;
        }
        if ( (i instanceof Item && i == stack.getItem()) || (i instanceof Class && ((Class) i).isInstance(stack.getItem())) )
            super.setStackInSlot(slot, stack);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if ( (i instanceof Item && i == stack.getItem()) || (i instanceof Class && ((Class) i).isInstance(stack.getItem())) )
            return super.insertItem(slot, stack, simulate);
        else
            return stack;
    }

}
