package convenientadditions.tileentity.charge;

import convenientadditions.api.tileentity.charge.TileEntityChargeDistributor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntitySunlightCollector extends TileEntityChargeDistributor implements ITickable {
	public TileEntitySunlightCollector() {
		super(30000,64);
	}
	
	@Override
	public boolean isDistributingCharge(EnumFacing f) {
		return f!=EnumFacing.UP;
	}
	
	@Override
	public void update(){
		super.update();
		if(this.worldObj.isRemote)
			return;
    	if(this.worldObj.isDaytime() && !this.worldObj.isRaining() && this.worldObj.canBlockSeeTheSky(this.xCoord,this.yCoord+1,this.zCoord)){
    		this.fillCharge(32);
    	}
	}
}
