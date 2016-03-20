package convenientadditions.api.tileentity.charge;

import net.minecraft.util.EnumFacing;


public interface ISidedChargeDistributor extends ISidedChargeContainer {
	public int getChargeDistrubutionRate(EnumFacing f);
	public boolean isDistributingCharge(EnumFacing f);
}
