package convenientadditions.api.tileentity.charge;

import net.minecraft.util.EnumFacing;


public interface ISidedChargeAcceptor extends ISidedChargeContainer {
	public int getChargeAcceptionRate(EnumFacing f);
	public boolean isAcceptingCharge(EnumFacing f);
	public int getChargeAcceptionRate();
	public boolean isAcceptingCharge();
}
