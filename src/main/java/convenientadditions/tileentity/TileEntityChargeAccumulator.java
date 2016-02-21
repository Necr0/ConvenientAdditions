package convenientadditions.tileentity;

import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.api.tileentity.ISidedChargeAcceptor;
import convenientadditions.api.tileentity.ISidedChargeDistributor;
import convenientadditions.api.tileentity.TileEntityChargeDistributorConfigurable;

public class TileEntityChargeAccumulator extends TileEntityChargeDistributorConfigurable implements ISidedChargeDistributor, ISidedChargeAcceptor {

	public TileEntityChargeAccumulator() {
		super(240000,256);
	}

	@Override
	public int getChargeAcceptionRate(ForgeDirection f) {
		return 256;
	}

	@Override
	public boolean isAcceptingCharge(ForgeDirection f) {
		return !isDistributingCharge(f);
	}

	@Override
	public int getChargeAcceptionRate() {
		return 256;
	}

	@Override
	public boolean isAcceptingCharge() {
		return true;
	}
	
}
