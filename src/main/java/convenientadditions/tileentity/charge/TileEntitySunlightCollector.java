package convenientadditions.tileentity.charge;

import convenientadditions.api.tileentity.charge.TileEntityChargeDistributor;

public class TileEntitySunlightCollector extends TileEntityChargeDistributor {
	public TileEntitySunlightCollector() {
		super(30000,64);
	}
	
	@Override
	public boolean isDistributingCharge(ForgeDirection f) {
		return f!=ForgeDirection.UP;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote)
			return;
    	if(this.worldObj.isDaytime() && !this.worldObj.isRaining() && this.worldObj.canBlockSeeTheSky(this.xCoord,this.yCoord+1,this.zCoord)){
    		this.fillCharge(32);
    	}
	}
}
