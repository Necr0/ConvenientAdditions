package convenientadditions.api.tileentity.charge;


public interface ISidedChargeAcceptor extends ISidedChargeContainer {
	public int getChargeAcceptionRate(ForgeDirection f);
	public boolean isAcceptingCharge(ForgeDirection f);
	public int getChargeAcceptionRate();
	public boolean isAcceptingCharge();
}
