package convenientadditions.api.tileentity.charge;

import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import convenientadditions.api.tileentity.IConfigurable;
import convenientadditions.api.util.MathHelper;


public abstract class TileEntityChargeDistributorConfigurable extends TileEntityChargeDistributor implements IConfigurable {

	public HashMap<EnumFacing, Boolean> outletSides=new HashMap<ForgeDirection, Boolean>();
	
	public TileEntityChargeDistributorConfigurable(int chargeCapacity,int chargeDistributionRate) {
		super(chargeCapacity, chargeDistributionRate);
		for(EnumFacing f:EnumFacing.VALUES){
			outletSides.put(f, false);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("OUTLET")){
			byte in=nbt.getByte("OUTLET");
			MathHelper.Bitmask mask=new MathHelper.Bitmask(in);
			for(EnumFacing f:EnumFacing.VALUES){
				outletSides.put(f,mask.getBit(f.ordinal()));
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		MathHelper.Bitmask mask=new MathHelper.Bitmask(0);
		for(EnumFacing f:EnumFacing.VALUES){
			mask.setBit(f.ordinal(), outletSides.get(f));
		}
		nbt.setByte("OUTLET", (byte)mask.get());
	}
	

	@Override
	public int getChargeDistrubutionRate(EnumFacing f) {
		return chargeDistributionRate;
	}

	@Override
	public boolean isDistributingCharge(EnumFacing f) {
		return outletSides.get(f);
	}

	@Override
	public boolean configureSide(EnumFacing f) {
		outletSides.put(f, !outletSides.get(f));
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.pos);
		return true;
	}
	
}
