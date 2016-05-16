package convenientadditions.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityProximitySensor extends TileEntity implements ITickable {
	@Override
	public void update(){
		/*int s=getStenght();
		int m=worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if(s!=m&&!worldObj.isRemote)
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, s, 3);*/
	}
	
/*	public int getStenght(){
		boolean power=worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		EntityPlayer p;
		if(power)
			p=this.worldObj.getClosestPlayer((double)xCoord+.5, (double)yCoord+.5, (double)zCoord+.5, 7.5D);
		else
			p=this.worldObj.getClosestPlayer((double)xCoord+.5, (double)yCoord+.5, (double)zCoord+.5, 15D);
		if(p!=null){
			if(power)
				return (int)(15-(2*getSqrt(p.getDistanceSq((double)xCoord+.5, (double)yCoord+.5, (double)zCoord+.5))));
			else
				return (int)(15-getSqrt(p.getDistanceSq((double)xCoord+.5, (double)yCoord+.5, (double)zCoord+.5)));
		}
		return 0;
	}
	
	//Because square roots are heavy
	public double getSqrt(double in){
		int ret=0;
		for(int i=1;i<16;i++){
			if(in>(i*i))
				ret=i;
			else
				break;
		}
		return ret;
	}*/
}
