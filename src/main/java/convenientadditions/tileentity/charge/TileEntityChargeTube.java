package convenientadditions.tileentity.charge;

import net.minecraft.tileentity.TileEntity;
import convenientadditions.api.tileentity.charge.ISidedChargeAcceptor;
import convenientadditions.api.tileentity.charge.ISidedChargeDistributor;
import convenientadditions.api.tileentity.charge.TileEntityChargeDistributor;

public class TileEntityChargeTube extends TileEntityChargeDistributor implements ISidedChargeAcceptor {

	public TileEntityChargeTube() {
		super(256, 128);
	}
	
	@Override
	public void updateEntity(){
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			if(isDistributingCharge(f)&&getChargeDistrubutionRate(f)>0){
				tryPush(f);
			}
		}
		for(ForgeDirection f:ForgeDirection.VALID_DIRECTIONS){
			if(isDistributingCharge(f)&&getChargeDistrubutionRate(f)>0){
				tryEvenOut(f);
			}
		}
	}
	
	public void tryPush(ForgeDirection f){
		TileEntity te=worldObj.getTileEntity(xCoord+f.offsetX, yCoord+f.offsetY, zCoord+f.offsetZ);
		ForgeDirection oof=f.getOpposite();
		if(te!=null&&te instanceof ISidedChargeAcceptor&&!(te instanceof TileEntityChargeTube)){
			ISidedChargeAcceptor ac=(ISidedChargeAcceptor)te;
			if(ac.isAcceptingCharge(oof)){
				int rate=Math.min(ac.getChargeAcceptionRate(oof),getChargeDistrubutionRate(f));
				rate=Math.min(rate,getContainedCharge(f));
				int overflow=ac.fillCharge(oof,rate);
				this.drainCharge(f,rate-overflow);
			}
		}
	}
	
	public void tryEvenOut(ForgeDirection f){
		TileEntity te=worldObj.getTileEntity(xCoord+f.offsetX, yCoord+f.offsetY, zCoord+f.offsetZ);
		ForgeDirection oof=f.getOpposite();
		if(te!=null&&te instanceof ISidedChargeAcceptor&&te instanceof TileEntityChargeTube){
			TileEntityChargeTube ac=(TileEntityChargeTube)te;
			if(ac.isAcceptingCharge(oof)){
				if(ac.getContainedCharge(oof)<this.getContainedCharge(f)){
					int rate=Math.min(ac.getChargeAcceptionRate(oof),getChargeDistrubutionRate(f));
					rate=Math.min(rate,(getContainedCharge(f)-ac.getContainedCharge(oof))/2);
					int overflow=ac.fillCharge(oof,rate);
					this.drainCharge(f,rate-overflow);
				}
			}
		}
	}

	@Override
	public int getChargeAcceptionRate(ForgeDirection f) {
		return this.chargeDistributionRate;
	}

	@Override
	public boolean isAcceptingCharge(ForgeDirection f) {
		return true;
	}

	@Override
	public int getChargeAcceptionRate() {
		return this.chargeDistributionRate;
	}

	@Override
	public boolean isAcceptingCharge() {
		return true;
	}
	
	public boolean isConnected(ForgeDirection f) {
		TileEntity te=worldObj.getTileEntity(xCoord+f.offsetX, yCoord+f.offsetY, zCoord+f.offsetZ);
		return (te!=null)&&((te instanceof ISidedChargeAcceptor&&((ISidedChargeAcceptor)te).isAcceptingCharge(f.getOpposite()))||(te instanceof ISidedChargeDistributor&&((ISidedChargeDistributor)te).isDistributingCharge(f.getOpposite())));
	}
}
