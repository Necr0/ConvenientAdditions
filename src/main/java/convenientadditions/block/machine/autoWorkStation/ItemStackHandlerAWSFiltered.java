package convenientadditions.block.machine.autoWorkStation;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.api.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemStackHandlerAWSFiltered extends ItemStackHandlerAutoSave {

    public ItemStackHandlerAWSFiltered(TileEntityAutoWorkStation tile, int slots) {
        super(tile, slots);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (!((TileEntityAutoWorkStation) te).filteredInput)
            return super.insertItem(slot, stack, simulate);
        if(ItemHelper.match(stack,getStackInSlot(slot),false,false))
            return super.insertItem(slot, stack, simulate);
        return stack;
    }

    public boolean containsValidStack(NonNullList<ItemStack> filter, ItemStack test, boolean ignoreDamage, boolean ignoreNBT) {
        for (ItemStack f : filter) {
            if (ItemHelper.match(f, test, ignoreDamage, ignoreNBT))
                return true;
        }
        return false;
    }
}
