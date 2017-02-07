package convenientadditions.block.itemTransmitter;

import convenientadditions.api.IMatcher;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSave;
import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.api.item.ItemChannelModule;
import convenientadditions.api.provider.itemnetwork.IItemProvider;
import convenientadditions.api.provider.itemnetwork.ItemNetworkProvider;
import convenientadditions.base.CATileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

public class TileEntityItemTransmitter extends CATileEntity implements ITickable, IItemProvider {

    ItemStackHandlerAutoSaveRestricted channels;
    ItemStackHandlerAutoSave buffer;

    public TileEntityItemTransmitter() {
        channels = new ItemStackHandlerAutoSaveRestricted(this, 3, ItemChannelModule.class);
        buffer = new ItemStackHandlerAutoSave(this, 9);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)buffer : super.getCapability(capability, facing);
    }

    @Override
    public IItemHandler getItemHandler(){ return this.buffer; }

    @Override
    public boolean hasItemHandler(){ return true; }

    @Override
    public IMatcher[] getAccess() {
        ArrayList<IMatcher> a = new ArrayList<>();
        for (ItemStack s : channels.getStacks()) {
            if (!s.isEmpty() && ((ItemChannelModule) s.getItem()).hasMatcher(s))
                a.add(((ItemChannelModule) s.getItem()).getMatcher(s));
        }
        return a.toArray(new IMatcher[a.size()]);
    }

    @Override
    public void update() {
        ItemNetworkProvider.addEntry(getWorld(), getPos());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("CHANNELS") && nbt.getTag("CHANNELS") instanceof NBTTagCompound)
            channels.deserializeNBT((NBTTagCompound) nbt.getTag("CHANNELS"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("CHANNELS", channels.serializeNBT());
        return nbt;
    }
}
