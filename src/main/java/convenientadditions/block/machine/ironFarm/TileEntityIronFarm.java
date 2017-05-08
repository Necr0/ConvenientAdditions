package convenientadditions.block.machine.ironFarm;

import convenientadditions.api.block.tileentity.ItemStackHandlerAutoSaveOutputOnly;
import convenientadditions.base.block.CATileEntity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityIronFarm extends CATileEntity implements ITickable {
    public int tier=0;
    public int progress=0;
    public ItemStackHandlerAutoSaveOutputOnly inv;
    public ItemStack iron=new ItemStack(Items.IRON_INGOT);

    public TileEntityIronFarm(){
        inv=new ItemStackHandlerAutoSaveOutputOnly(this);
    }

    public TileEntityIronFarm(int tier){
        inv=new ItemStackHandlerAutoSaveOutputOnly(this);
        this.tier=tier;
    }

    @Override
    public void update() {
        if (world.isRemote)
            return;
        int timeNeeded=3000/(int)Math.pow(4,tier);
        int count=inv.getStackInSlot(0).getCount();

        if(count<iron.getMaxStackSize() && ++progress >= timeNeeded){
            progress=0;
            inv.setStackInSlot(0,new ItemStack(Items.IRON_INGOT,count+1));
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inv : super.getCapability(capability, facing);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("INV") && nbt.getTag("INV") instanceof NBTTagCompound)
            inv.deserializeNBT((NBTTagCompound) nbt.getTag("INV"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("INV", inv.serializeNBT());
        return nbt;
    }
}
