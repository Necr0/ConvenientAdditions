package convenientadditions.block.inventoryProxy.filtered;

import convenientadditions.api.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ItemHandlerFilteredProxy implements IItemHandler {
    TileEntityInventoryProxyFiltered te;
    IItemHandler target;

    public ItemHandlerFilteredProxy(TileEntityInventoryProxyFiltered te, IItemHandler target) {
        this.te = te;
        this.target = target;
    }

    @Override
    public int getSlots() {
        return target.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return target.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (ItemHelper.match(te.filter.getStacks(), stack, te.ignoreDV, te.ignoreNBT)!=te.blacklist)
            return target.insertItem(slot, stack, simulate);
        else
            return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (ItemHelper.match(te.filter.getStacks(), target.getStackInSlot(slot), te.ignoreDV, te.ignoreNBT)!=te.blacklist)
            return target.extractItem(slot, amount, simulate);
        else
            return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int slot) {
        return target.getSlotLimit(slot);
    }

}
