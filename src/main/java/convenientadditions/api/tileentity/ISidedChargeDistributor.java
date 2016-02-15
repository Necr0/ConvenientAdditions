package convenientadditions.api.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public interface ISidedChargeDistributor {
	public int chargeDistrubutionRate(ForgeDirection f);
	public boolean isDistributingCharge(ForgeDirection f);
}
