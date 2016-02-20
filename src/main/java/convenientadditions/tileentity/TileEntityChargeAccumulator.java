package convenientadditions.tileentity;

import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.api.tileentity.ISidedChargeAcceptor;
import convenientadditions.api.tileentity.ISidedChargeDistributor;
import convenientadditions.api.tileentity.TileEntityChargeDistributor;

public class TileEntityChargeAccumulator extends TileEntityChargeDistributor implements ISidedChargeDistributor, ISidedChargeAcceptor {

	public TileEntityChargeAccumulator() {
		super(240000,256);
	}

	@Override
	public int getChargeAcceptionRate(ForgeDirection f) {
		return 256;
	}

	@Override
	public boolean isAcceptingCharge(ForgeDirection f) {
		return true;
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
