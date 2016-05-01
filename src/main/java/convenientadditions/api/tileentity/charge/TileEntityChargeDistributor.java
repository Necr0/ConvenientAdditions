package convenientadditions.api.tileentity.charge;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TileEntityChargeDistributor extends TileEntityChargeContainer implements ISidedChargeDistributor {

	public int chargeDistributionRate;
	
	public TileEntityChargeDistributor(int chargeCapacity,int chargeDistributionRate) {
		super(chargeCapacity);
		this.chargeDistributionRate=chargeDistributionRate;
	}
	
	@Override
	public void updateEntity(){
		for(EnumFacing f:EnumFacing.VALUES){
			if(isDistributingCharge(f)&&getChargeDistrubutionRate(f)>0)
				tryPush(f);
		}
	}

	@Override
	public int getChargeDistrubutionRate(EnumFacing f) {
		return chargeDistributionRate;
	}

	@Override
	public boolean isDistributingCharge(EnumFacing f) {
		return true;
	}
	
	public void tryPush(EnumFacing f){
		TileEntity te=worldObj.getTileEntity(this.pos);
		EnumFacing oof=f.getOpposite();
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
