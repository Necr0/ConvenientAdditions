package convenientadditions.api.tileentity.charge;

import net.minecraftforge.common.util.ForgeDirection;

public interface ISidedChargeAcceptor extends ISidedChargeContainer {
	public int getChargeAcceptionRate(ForgeDirection f);
	public boolean isAcceptingCharge(ForgeDirection f);
	public int getChargeAcceptionRate();
	public boolean isAcceptingCharge();
}
