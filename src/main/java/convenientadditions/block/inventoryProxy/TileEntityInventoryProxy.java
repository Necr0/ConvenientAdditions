package convenientadditions.block.inventoryProxy;

import convenientadditions.block.TileEntityCABase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityInventoryProxy extends TileEntityCABase  {
	public boolean sided=false;
	
	public TileEntityInventoryProxy() {}
	
	public TileEntityInventoryProxy(boolean sided) {
		this.sided=sided;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		TileEntity te=getWorld().getTileEntity(getTarget());
		if(te!=null)
			return te.hasCapability(capability, (sided?facing:getFacing().getOpposite()));
		else
			return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		TileEntity te=getWorld().getTileEntity(getTarget());
		if(te!=null)
			return te.getCapability(capability, (sided?facing:getFacing().getOpposite()));
		else
			return super.getCapability(capability, facing);
	}
	
	public EnumFacing getFacing(){
		return getWorld().getBlockState(getPos()).getValue(BlockInventoryProxy.FACING);
	}

	public BlockPos getTarget(){
		return new BlockPos(getPos().getX()+getFacing().getFrontOffsetX(), getPos().getY()+getFacing().getFrontOffsetY(), getPos().getZ()+getFacing().getFrontOffsetZ());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("SIDED_PROXY"))
			this.sided=nbt.getBoolean("SIDED_PROXY");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("SIDED_PROXY",sided);
		return nbt;
	}
}
