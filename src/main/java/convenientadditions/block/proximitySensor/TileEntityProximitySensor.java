package convenientadditions.block.proximitySensor;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityProximitySensor extends TileEntity implements ITickable {
	int strength;
	
	@Override
	public void update(){
		int str=getComp();
		if(str!=strength){
			IBlockState state=worldObj.getBlockState(pos);
			worldObj.updateComparatorOutputLevel(pos,state.getBlock());
			worldObj.notifyBlockUpdate(pos, state, state, (strength==0||str==0)?3:(3+4));
		}
		strength=str;
	}
	
	public int getComp(){
		boolean power=worldObj.isBlockIndirectlyGettingPowered(pos)>0;
		double range=power?7.5D:15D;
		EntityPlayer p=worldObj.getClosestPlayer((double)pos.getX()+.5, (double)pos.getY()+.5, (double)pos.getZ()+.5, range, false);
		if(p!=null){
			return getStr(p.getDistanceSq((double)pos.getX()+.5, (double)pos.getY()+.5, (double)pos.getZ()+.5),power?.5D:1D);
		}
		return 0;
	}
	
	public int getStr(double in,double stepsize){
		int ret=(int)(getSqrt(in, 0D, stepsize, 15)/stepsize);
		return 15-ret;
	}

	//Because square roots are heavy
	public double getSqrt(double in,double start,double stepsize,double steps){
		int ret=0;
		for(int i=0;i<steps;i++){
			if(in>=(start+(stepsize*i))*(start+(stepsize*i)))
				ret=i;
			else
				break;
		}
		return start+(ret*stepsize);
	}
}
