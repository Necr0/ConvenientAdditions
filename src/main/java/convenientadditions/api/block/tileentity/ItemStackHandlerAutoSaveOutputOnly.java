package convenientadditions.api.block.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerAutoSaveOutputOnly extends ItemStackHandler implements IItemHandlerModifiable {
    public TileEntity te;

    public ItemStackHandlerAutoSaveOutputOnly(TileEntity tile, int slots) {
        super(slots);
        this.te = tile;
    }


    public ItemStackHandlerAutoSaveOutputOnly(TileEntity tile) {
        super(1);
        this.te = tile;
    }

    @Override
    protected void onContentsChanged(int slot) {
        te.markDirty();
    }

    public ItemStack[] getStacks() {
        return this.stacks;
    }

    public void setStacks(ItemStack[] stacks) {
        this.stacks = stacks;
        for (int i = 0; i < getSlots(); i++)
            onContentsChanged(i);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }
}
