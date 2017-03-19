package convenientadditions.block.machine.storageMatrix;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityStorageMatrix extends CATileEntity {
    public ItemStackHandlerAutoSave inventory=new ItemStackHandlerAutoSave(this,256);

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt=super.getUpdateTag();
        nbt.removeTag("INVENTORY");
        return nbt;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("INVENTORY") && nbt.getTag("INVENTORY") instanceof NBTTagCompound)
            inventory.deserializeNBT((NBTTagCompound) nbt.getTag("INVENTORY"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("INVENTORY", inventory.serializeNBT());
        return nbt;
    }
}
