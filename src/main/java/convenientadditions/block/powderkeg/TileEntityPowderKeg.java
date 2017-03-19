package convenientadditions.block.powderkeg;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveRestricted;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityPowderKeg extends CATileEntity {

    public ItemStackHandlerAutoSaveRestricted inventory = new ItemStackHandlerAutoSaveRestricted(this, Items.GUNPOWDER);

    public TileEntityPowderKeg() {
        this.inventory.setCauseUpdate(true).setUpdateFlag(6);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
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
