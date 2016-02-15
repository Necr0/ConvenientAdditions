package convenientadditions.api.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public interface ISidedChargeContainer {
	public int getContainedCharge(ForgeDirection f);
	public int getChargeCapacity(ForgeDirection f);
	public boolean isChargable(ForgeDirection f);
	public void setContainedCharge(ForgeDirection f,int amount);
	public int drainCharge(ForgeDirection f,int amount);
	public int fillCharge(ForgeDirection f,int amount);
	public int getContainedCharge();
	public int getChargeCapacity();
	public boolean isChargable();
	public void setContainedCharge(int amount);
	public int drainCharge(int amount);
	public int fillCharge(int amount);
}
