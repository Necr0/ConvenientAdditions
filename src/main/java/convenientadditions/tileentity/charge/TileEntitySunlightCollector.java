package convenientadditions.tileentity.charge;

import convenientadditions.api.tileentity.charge.TileEntityChargeDistributor;
import net.minecraft.util.BlockPos;
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
    	if(this.worldObj.isDaytime() && !this.worldObj.isRaining() && this.worldObj.canBlockSeeSky(new BlockPos(pos.getX(), pos.getY(), pos.getZ()))){
    		this.fillCharge(32);
    	}
	}
}
