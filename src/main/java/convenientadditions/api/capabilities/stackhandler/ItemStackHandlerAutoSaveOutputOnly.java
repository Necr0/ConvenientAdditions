package convenientadditions.api.capabilities.stackhandler;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ItemStackHandlerAutoSaveOutputOnly extends ItemStackHandlerAutoSave {

    public ItemStackHandlerAutoSaveOutputOnly(TileEntity tile, int slots) {
        super(tile, slots);
    }

    public ItemStackHandlerAutoSaveOutputOnly(TileEntity tile) {
        super(tile);
        this.te = tile;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }
}
