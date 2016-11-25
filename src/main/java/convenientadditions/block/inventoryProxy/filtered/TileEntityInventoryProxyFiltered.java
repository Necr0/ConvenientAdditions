package convenientadditions.block.inventoryProxy.filtered;

import convenientadditions.api.block.tileentity.IItemProxy;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.block.inventoryProxy.TileEntityInventoryProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

public class TileEntityInventoryProxyFiltered extends TileEntityInventoryProxy {

    public ItemStackHandlerAutoSave filter;
    public boolean ignoreDV = false;
    public boolean ignoreNBT = false;

    public TileEntityInventoryProxyFiltered() {
        filter = new ItemStackHandlerAutoSave(this, 3);
        new ArrayList<ItemStack>();

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        TileEntity te = getWorld().getTileEntity(getTarget());
        if (te != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (!(te instanceof IItemProxy))
                return (T) new ItemHandlerFilteredProxy(this, (IItemHandler) te.getCapability(capability, getFacing().getOpposite()));
            else
                return (T) new ItemHandlerFilteredProxy(this, ((IItemProxy) te).tryFetchItemHandler(sided ? facing : getFacing().getOpposite(), 1));
        } else
            return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("FILTER") && nbt.getTag("FILTER") instanceof NBTTagCompound)
            filter.deserializeNBT((NBTTagCompound) nbt.getTag("FILTER"));
        if (nbt.hasKey("IGNOREDV"))
            ignoreDV = nbt.getBoolean("IGNOREDV");
        if (nbt.hasKey("IGNORENBT"))
            ignoreNBT = nbt.getBoolean("IGNORENBT");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("FILTER", filter.serializeNBT());
        nbt.setBoolean("IGNOREDV", ignoreDV);
        nbt.setBoolean("IGNORENBT", ignoreNBT);
        return nbt;
    }

    public void setIgnoreDV(boolean ignoreDV) {
        this.ignoreDV = ignoreDV;
        markDirty();
        this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos), 0);
    }

    public void setIgnoreNBT(boolean ignoreNBT) {
        this.ignoreNBT = ignoreNBT;
        markDirty();
        this.worldObj.notifyBlockUpdate(pos, worldObj.getBlockState(pos), worldObj.getBlockState(pos), 0);
    }
}
