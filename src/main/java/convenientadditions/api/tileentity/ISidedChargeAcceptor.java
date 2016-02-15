package convenientadditions.api.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public interface ISidedChargeAcceptor {
	public int chargeAcceptionRate(ForgeDirection f);
	public boolean isAcceptingCharge(ForgeDirection f,int amount);
	public int chargeAcceptionRate();
	public boolean isAcceptingCharge(int amount);
}
