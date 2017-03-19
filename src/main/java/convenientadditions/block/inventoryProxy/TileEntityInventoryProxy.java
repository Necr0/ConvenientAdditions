package convenientadditions.block.inventoryProxy;

import convenientadditions.api.block.tileentity.IItemProxy;
import convenientadditions.base.block.CATileEntity;
import convenientadditions.init.ModConfig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityInventoryProxy extends CATileEntity implements IItemProxy {

    public boolean sided = false;

    public TileEntityInventoryProxy() {
    }

    public TileEntityInventoryProxy(boolean sided) {
        this.sided = sided;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (ModConfig.inventoryProxies_blacklist.contains(getWorld().getBlockState(getTarget()).getBlock().getRegistryName().toString()))
            return false;
        TileEntity te = getWorld().getTileEntity(getTarget());
        if (te != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (!(te instanceof IItemProxy))
                return te.hasCapability(capability, (sided ? facing : getFacing().getOpposite()));
            else
                return ((IItemProxy) te).tryFetchItemHandler(sided ? facing : getFacing().getOpposite(), 1) != null;
        } else
            return super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (ModConfig.inventoryProxies_blacklist.contains(getWorld().getBlockState(getTarget()).getBlock().getRegistryName().toString()))
            return null;
        TileEntity te = getWorld().getTileEntity(getTarget());
        if (te != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (!(te instanceof IItemProxy))
                return te.getCapability(capability, (sided ? facing : getFacing().getOpposite()));
            else
                return (T) ((IItemProxy) te).tryFetchItemHandler(sided ? facing : getFacing().getOpposite(), 1);
        } else
            return super.getCapability(capability, facing);
    }

    public EnumFacing getFacing() {
        return getWorld().getBlockState(getPos()).getValue(BlockInventoryProxy.FACING);
    }

    public BlockPos getTarget() {
        return new BlockPos(getPos().getX() + getFacing().getFrontOffsetX(), getPos().getY() + getFacing().getFrontOffsetY(), getPos().getZ() + getFacing().getFrontOffsetZ());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("SIDED_PROXY"))
            this.sided = nbt.getBoolean("SIDED_PROXY");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("SIDED_PROXY", sided);
        return nbt;
    }

    @Override
    public IItemHandler tryFetchItemHandler(EnumFacing f, int proxyIndex) {
        TileEntity te = getWorld().getTileEntity(getTarget());
        if (te != null && !(te instanceof IItemProxy))
            return te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (sided ? f : getFacing().getOpposite()));
        else if (te != null && proxyIndex < ModConfig.inventoryProxies_chainLimit)
            return ((IItemProxy) te).tryFetchItemHandler(sided ? f : getFacing().getOpposite(), proxyIndex + 1);
        else
            return super.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f);
    }
}
