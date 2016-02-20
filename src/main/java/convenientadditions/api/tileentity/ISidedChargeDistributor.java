package convenientadditions.api.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public interface ISidedChargeDistributor extends ISidedChargeContainer {
	public int getChargeDistrubutionRate(ForgeDirection f);
	public boolean isDistributingCharge(ForgeDirection f);
}
