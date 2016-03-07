package convenientadditions.api.tileentity.charge;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityChargeDistributor extends TileEntityChargeContainer implements ISidedChargeDistributor {

	public int chargeDistributionRate;
	
	public TileEntityChargeDistributor(int chargeCapacity,int chargeDistributionRate) {
		super(chargeCapacity);
		this.chargeDistributionRate=chargeDistributionRate;
	}
	
	@Override
	public void updateEntity(){
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			if(isDistributingCharge(f)&&getChargeDistrubutionRate(f)>0)
				tryPush(f);
		}
	}

	@Override
	public int getChargeDistrubutionRate(ForgeDirection f) {
		return chargeDistributionRate;
	}

	@Override
	public boolean isDistributingCharge(ForgeDirection f) {
		return true;
	}
	
	public void tryPush(ForgeDirection f){
		TileEntity te=worldObj.getTileEntity(xCoord+f.offsetX, yCoord+f.offsetY, zCoord+f.offsetZ);
		ForgeDirection oof=f.getOpposite();
		if(te!=null&&te instanceof ISidedChargeAcceptor){
			ISidedChargeAcceptor ac=(ISidedChargeAcceptor)te;
			if(ac.isAcceptingCharge(oof)){
				int rate=Math.min(ac.getChargeAcceptionRate(oof),getChargeDistrubutionRate(f));
				rate=Math.min(rate,getContainedCharge(f));
				int overflow=ac.fillCharge(oof,rate);
				this.drainCharge(f,rate-overflow);
			}
		}
	}
	
}
