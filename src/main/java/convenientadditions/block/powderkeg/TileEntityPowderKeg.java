package convenientadditions.block.powderkeg;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.base.TileEntityCABase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityPowderKeg extends TileEntityCABase {

    public ItemStackHandlerAutoSaveRestricted stackHandler = new ItemStackHandlerAutoSaveRestricted(this, Items.GUNPOWDER);

    public TileEntityPowderKeg() {
        this.stackHandler.setCauseUpdate(true).setUpdateFlag(6);
    }

    public int getAmount() {
        ItemStack stack = stackHandler.getStackInSlot(0);
        return stack != null ? stack.getCount() : 0;
    }

    public ItemStack removeStack(int amount) {
        ItemStack stack = stackHandler.extractItem(0, amount, false);
        return stack;
    }

    public ItemStack insertStack(ItemStack stackIn) {
        ItemStack stack = stackHandler.insertItem(0, stackIn, false);
        return stack;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) stackHandler : super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("INVENTORY") && nbt.getTag("INVENTORY") instanceof NBTTagCompound)
            stackHandler.deserializeNBT((NBTTagCompound) nbt.getTag("INVENTORY"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("INVENTORY", stackHandler.serializeNBT());
        return nbt;
    }
}
