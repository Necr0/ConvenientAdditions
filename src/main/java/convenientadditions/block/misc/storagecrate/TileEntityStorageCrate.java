package convenientadditions.block.misc.storagecrate;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * Created by Necro on 5/7/2017.
 */
public class TileEntityStorageCrate extends CATileEntity {
    public ItemStackHandlerAutoSave inv=new ItemStackHandlerAutoSave(this,72);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("INV") && nbt.getTag("INV") instanceof NBTTagCompound)
            inv.deserializeNBT((NBTTagCompound) nbt.getTag("INV"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("INV", inv.serializeNBT());
        return nbt;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inv : super.getCapability(capability, facing);
    }
}
