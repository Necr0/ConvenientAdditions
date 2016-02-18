package convenientadditions.tileentity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.Helper;
import convenientadditions.api.tileentity.ISidedChargeDistributor;
import convenientadditions.api.tileentity.TileEntityChargeContainer;
import convenientadditions.init.ModItems;

public class TileEntitySunlightCollector extends TileEntityChargeContainer implements ISidedChargeDistributor {
	public TileEntitySunlightCollector() {
		super(30000);
	}
	
	@Override
	public void updateEntity(){
		if(this.worldObj.isRemote)
			return;
    	if(this.worldObj.isDaytime() && !this.worldObj.isRaining() && this.worldObj.canBlockSeeTheSky(this.xCoord,this.yCoord+1,this.zCoord)){
    		if(this.fillCharge(32)!=32){
    			this.markDirty();
    		}
    	}
	}

	@Override
	public int chargeDistrubutionRate(ForgeDirection f) {
		return 64;
	}

	@Override
	public boolean isDistributingCharge(ForgeDirection f) {
		return true;
	}
}
