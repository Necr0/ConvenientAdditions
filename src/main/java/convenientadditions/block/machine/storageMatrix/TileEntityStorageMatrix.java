package convenientadditions.block.machine.storageMatrix;

import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityStorageMatrix extends CATileEntity {
    public ItemStackHandlerAutoSave inventory=addCapability(addAutoSavable(new ItemStackHandlerAutoSave(this,256).setName("INVENTORY")));

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt=super.getUpdateTag();
        nbt.removeTag("INVENTORY");
        return nbt;
    }
}
