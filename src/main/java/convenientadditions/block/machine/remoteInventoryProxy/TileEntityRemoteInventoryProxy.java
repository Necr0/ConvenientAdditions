package convenientadditions.block.machine.remoteInventoryProxy;

import convenientadditions.api.block.tileentity.IItemProxy;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSave;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.base.block.CATileEntity;
import convenientadditions.config.ModConfigMisc;
import convenientadditions.item.module.ItemLocationModule;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Arrays;

public class TileEntityRemoteInventoryProxy extends CATileEntity implements IItemProxy{

    ItemStackHandlerAutoSave target = addAutoSavable(new ItemStackHandlerAutoSaveRestricted(this,1,ItemLocationModule.class).setName("LOCATION"));

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        BlockPos pos=getTargetLocation();
        if(pos==null||!getWorld().isBlockLoaded(pos))
            return false;
        if (Arrays.asList(ModConfigMisc.inventoryProxies_blacklist).contains(getWorld().getBlockState(pos).getBlock().getRegistryName().toString()))
            return false;
        TileEntity te = getWorld().getTileEntity(pos);
        if (te != null && capability == ITEM_HANDLER_CAPABILITY) {
            if (!(te instanceof IItemProxy))
                return te.hasCapability(capability, facing);
            else
                return ((IItemProxy) te).tryFetchItemHandler(facing , 1) != null;
        } else
            return super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        BlockPos pos=getTargetLocation();
        if(pos==null||!getWorld().isBlockLoaded(pos))
            return null;
        if (Arrays.asList(ModConfigMisc.inventoryProxies_blacklist).contains(getWorld().getBlockState(pos).getBlock().getRegistryName().toString()))
            return null;
        TileEntity te = getWorld().getTileEntity(pos);
        if (te != null && capability == ITEM_HANDLER_CAPABILITY) {
            if (!(te instanceof IItemProxy))
                return te.getCapability(capability, facing);
            else
                return (T) ((IItemProxy) te).tryFetchItemHandler(facing, 1);
        } else
            return super.getCapability(capability, facing);
    }

    @Nullable
    public BlockPos getTargetLocation() {
        ItemStack module=target.getStackInSlot(0);
        if(!module.isEmpty()){
            ItemLocationModule item=((ItemLocationModule)module.getItem());
            if(item.hasLocation(module)&&item.getDimension(module)==world.provider.getDimension())
                return item.getLocation(module);
        }
        return null;
    }

    @Override
    public IItemHandler tryFetchItemHandler(EnumFacing f, int proxyIndex) {
        BlockPos pos=getTargetLocation();
        if(pos==null||!getWorld().isBlockLoaded(pos))
            return null;
        TileEntity te = getWorld().getTileEntity(getTargetLocation());
        if (te != null && !(te instanceof IItemProxy))
            return te.getCapability(ITEM_HANDLER_CAPABILITY, f);
        else if (te != null && proxyIndex < ModConfigMisc.inventoryProxies_chainLimit)
            return ((IItemProxy) te).tryFetchItemHandler(f, proxyIndex + 1);
        else
            return super.getCapability(ITEM_HANDLER_CAPABILITY, f);
    }
}
