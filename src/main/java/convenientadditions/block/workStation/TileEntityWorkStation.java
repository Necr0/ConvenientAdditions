package convenientadditions.block.workStation;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Necro on 5/7/2017.
 */
public class TileEntityWorkStation extends CATileEntity {
    public ItemStackHandlerAutoSave inv=new ItemStackHandlerAutoSave(this,9);
    public ItemStackHandlerAutoSave grid=new ItemStackHandlerAutoSave(this,9);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("INV") && nbt.getTag("INV") instanceof NBTTagCompound)
            inv.deserializeNBT((NBTTagCompound) nbt.getTag("INV"));
        if (nbt.hasKey("GRID") && nbt.getTag("GRID") instanceof NBTTagCompound)
            grid.deserializeNBT((NBTTagCompound) nbt.getTag("GRID"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("INV", inv.serializeNBT());
        nbt.setTag("GRID", grid.serializeNBT());
        return nbt;
    }
}
