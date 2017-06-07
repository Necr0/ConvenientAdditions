package convenientadditions.block.misc.inventoryProxy.filtered;

import convenientadditions.api.block.tileentity.IItemProxy;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.block.misc.inventoryProxy.TileEntityInventoryProxy;
import convenientadditions.config.ModConfigMisc;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;

import java.util.Arrays;

public class TileEntityInventoryProxyFiltered extends TileEntityInventoryProxy {

    public ItemStackHandlerAutoSave filter = addAutoSavable(new ItemStackHandlerAutoSave(this, 3).setName("FILTER"));
    public boolean ignoreDV = false;
    public boolean ignoreNBT = false;
    public boolean blacklist = false;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (Arrays.asList(ModConfigMisc.inventoryProxies_blacklist).contains(getWorld().getBlockState(getTarget()).getBlock().getRegistryName().toString()))
            return null;
        TileEntity te = getWorld().getTileEntity(getTarget());
        if (te != null && capability == ITEM_HANDLER_CAPABILITY) {
            if (!(te instanceof IItemProxy))
                return (T) new ItemHandlerFilteredProxy(this, (IItemHandler) te.getCapability(capability, getFacing().getOpposite()));
            else
                return (T) new ItemHandlerFilteredProxy(this, ((IItemProxy) te).tryFetchItemHandler(sided ? facing : getFacing().getOpposite(), 1));
        } else
            return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("IGNOREDV"))
            ignoreDV = nbt.getBoolean("IGNOREDV");
        if (nbt.hasKey("IGNORENBT"))
            ignoreNBT = nbt.getBoolean("IGNORENBT");
        if (nbt.hasKey("BLACKLIST"))
            blacklist = nbt.getBoolean("BLACKLIST");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("IGNOREDV", ignoreDV);
        nbt.setBoolean("IGNORENBT", ignoreNBT);
        nbt.setBoolean("BLACKLIST", blacklist);
        return nbt;
    }

    public void setIgnoreDV(boolean ignoreDV) {
        this.ignoreDV = ignoreDV;
        causeUpdate(0);
    }

    public void setIgnoreNBT(boolean ignoreNBT) {
        this.ignoreNBT = ignoreNBT;
        causeUpdate(0);
    }

    public void setBlacklist(boolean blacklist) {
        this.blacklist = blacklist;
        causeUpdate(0);
    }
}
