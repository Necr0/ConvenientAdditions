package convenientadditions.tileentity.charge;

import convenientadditions.api.tileentity.charge.ISidedChargeAcceptor;
import convenientadditions.api.tileentity.charge.ISidedChargeDistributor;
import convenientadditions.api.tileentity.charge.TileEntityChargeDistributor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityChargeTube extends TileEntityChargeDistributor implements ISidedChargeAcceptor,ITickable {

	public TileEntityChargeTube() {
		super(256, 128);
	}
	
	@Override
	public void update(){
		for(EnumFacing f:EnumFacing.VALUES){
			if(isDistributingCharge(f)&&getChargeDistrubutionRate(f)>0){
				tryPush(f);
			}
		}
		for(EnumFacing f:EnumFacing.VALUES){
			if(isDistributingCharge(f)&&getChargeDistrubutionRate(f)>0){
				tryEvenOut(f);
			}
		}
	}
	
	public void tryPush(EnumFacing f){
		TileEntity te=worldObj.getTileEntity(new BlockPos(pos.getX()+f.getFrontOffsetX(), pos.getY()+f.getFrontOffsetY(), pos.getZ()+f.getFrontOffsetZ()));
		EnumFacing oof=f.getOpposite();
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
	
	public void tryEvenOut(EnumFacing f){
		TileEntity te=worldObj.getTileEntity(new BlockPos(pos.getX()+f.getFrontOffsetX(), pos.getY()+f.getFrontOffsetY(), pos.getZ()+f.getFrontOffsetZ()));
		EnumFacing oof=f.getOpposite();
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
	public int getChargeAcceptionRate(EnumFacing f) {
		return this.chargeDistributionRate;
	}

	@Override
	public boolean isAcceptingCharge(EnumFacing f) {
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
	
	public boolean isConnected(EnumFacing f) {
		TileEntity te=worldObj.getTileEntity(new BlockPos(pos.getX()+f.getFrontOffsetX(), pos.getY()+f.getFrontOffsetY(), pos.getZ()+f.getFrontOffsetZ()));
		return (te!=null)&&((te instanceof ISidedChargeAcceptor&&((ISidedChargeAcceptor)te).isAcceptingCharge(f.getOpposite()))||(te instanceof ISidedChargeDistributor&&((ISidedChargeDistributor)te).isDistributingCharge(f.getOpposite())));
	}
}
