package convenientadditions.block.machine.setProvider;

import convenientadditions.api.util.ItemHelper;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemStackHandlerSPFiltered extends ItemStackHandlerAutoSave {

    public ItemStackHandlerSPFiltered(TileEntitySetProvider tile, int slots) {
        super(tile, slots);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (!((TileEntitySetProvider) te).filteredInput)
            return super.insertItem(slot, stack, simulate);
        if (containsValidStack(((TileEntitySetProvider) te).filter.getStacks(), stack, ((TileEntitySetProvider) te).ignoreDV, ((TileEntitySetProvider) te).ignoreNBT))
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
