package convenientadditions.api.tileentity;

import java.util.HashMap;

import convenientadditions.api.util.MathHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;


public abstract class TileEntityChargeDistributorConfigurable extends TileEntityChargeDistributor implements IConfigurable {

	public HashMap<ForgeDirection, Boolean> outletSides=new HashMap<ForgeDirection, Boolean>();
	
	public TileEntityChargeDistributorConfigurable(int chargeCapacity,int chargeDistributionRate) {
		super(chargeCapacity, chargeDistributionRate);
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			outletSides.put(f, false);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(nbt.hasKey("OUTLET")){
			byte in=nbt.getByte("OUTLET");
			MathHelper.Bitmask mask=new MathHelper.Bitmask(in);
			for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
				outletSides.put(f,mask.getBit(f.ordinal()));
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		MathHelper.Bitmask mask=new MathHelper.Bitmask(0);
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			mask.setBit(f.ordinal(), outletSides.get(f));
		}
		nbt.setByte("OUTLET", (byte)mask.get());
	}
	

	@Override
	public int getChargeDistrubutionRate(ForgeDirection f) {
		return chargeDistributionRate;
	}

	@Override
	public boolean isDistributingCharge(ForgeDirection f) {
		return outletSides.get(f);
	}

	@Override
	public boolean configureSide(ForgeDirection f) {
		outletSides.put(f, !outletSides.get(f));
		this.markDirty();
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return true;
	}
	
}
