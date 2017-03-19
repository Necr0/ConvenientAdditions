package convenientadditions.block.displayCase;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * Created by Necro on 3/10/2017.
 */
public class TileEntityDisplayCase extends CATileEntity {
    public ItemStackHandlerAutoSave inventory=new ItemStackHandlerDisplayCase(this).setCauseUpdate(true).setUpdateFlag(0);

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
