package convenientadditions.api.tileentity.charge;

import net.minecraft.util.EnumFacing;


public interface ISidedChargeContainer {
	public int getContainedCharge(EnumFacing f);
	public int getChargeCapacity(EnumFacing f);
	public boolean isChargable(EnumFacing f);
	public void setContainedCharge(EnumFacing f,int amount);
	public int drainCharge(EnumFacing f,int amount);
	public int fillCharge(EnumFacing f,int amount);
	public int getContainedCharge();
	public int getChargeCapacity();
	public boolean isChargable();
	public void setContainedCharge(int amount);
	public int drainCharge(int amount);
	public int fillCharge(int amount);
	public double getChargePercentage(EnumFacing f);
	public double getChargePercentage();
}
