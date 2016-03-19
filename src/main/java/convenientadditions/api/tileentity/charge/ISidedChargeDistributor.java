package convenientadditions.api.tileentity.charge;


public interface ISidedChargeDistributor extends ISidedChargeContainer {
	public int getChargeDistrubutionRate(ForgeDirection f);
	public boolean isDistributingCharge(ForgeDirection f);
}
