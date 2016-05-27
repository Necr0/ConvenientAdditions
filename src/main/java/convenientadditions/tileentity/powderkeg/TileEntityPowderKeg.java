package convenientadditions.tileentity.powderkeg;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityPowderKeg extends TileEntity {

	public PowderKegItemStackHandler stackHandler=new PowderKegItemStackHandler();
	
	public int getAmount(){
		ItemStack stack=stackHandler.getStackInSlot(0);
		return stack!=null?stack.stackSize:0;
	}
	
	public ItemStack removeStack(int amount){
		ItemStack stack=stackHandler.extractItem(0, amount, false);
		return stack;
	}
	
	public ItemStack insertStack(ItemStack stackIn){
		ItemStack stack=stackHandler.insertItem(0, stackIn, false);
		return stack;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY?true:super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY?(T)stackHandler:super.getCapability(capability, facing);
	}	

	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("INVENTORY")&&nbt.getTag("INVENTORY") instanceof NBTTagCompound)
			stackHandler.deserializeNBT((NBTTagCompound)nbt.getTag("INVENTORY"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setTag("INVENTORY", stackHandler.serializeNBT());
	}
}
