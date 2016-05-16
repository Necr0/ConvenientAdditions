package convenientadditions.tileentity.charge;

import convenientadditions.api.tileentity.charge.ISidedChargeAcceptor;
import convenientadditions.api.tileentity.charge.ISidedChargeDistributor;
import convenientadditions.api.tileentity.charge.TileEntityChargeDistributorConfigurable;
import net.minecraft.util.EnumFacing;

public class TileEntityChargeAccumulator extends TileEntityChargeDistributorConfigurable implements ISidedChargeDistributor, ISidedChargeAcceptor {

	public TileEntityChargeAccumulator() {
		super(240000,256);
	}

	@Override
	public int getChargeAcceptionRate(EnumFacing f) {
		return 256;
	}

	@Override
	public boolean isAcceptingCharge(EnumFacing f) {
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
