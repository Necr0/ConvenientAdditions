package convenientadditions.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Helper {
	public static void spawnItemInPlace(World w,double x,double y,double z,ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
		e.motionX = 0;
		e.motionY = 0;
		e.motionZ = 0;
		if(!w.isRemote)
			w.spawnEntityInWorld(e);
	}
	
	public static boolean checkForFire(IBlockAccess world,BlockPos pos){
		int x=pos.getX(),y=pos.getY(),z=pos.getZ();
    	return world.getBlockState(new BlockPos(x+1, y, z)).getBlock()==Blocks.fire||
    			world.getBlockState(new BlockPos(x, y+1, z)).getBlock()==Blocks.fire||
				world.getBlockState(new BlockPos(x, y, z+1)).getBlock()==Blocks.fire||
				world.getBlockState(new BlockPos(x-1, y, z)).getBlock()==Blocks.fire||
				world.getBlockState(new BlockPos(x, y-1, z)).getBlock()==Blocks.fire||
				world.getBlockState(new BlockPos(x, y, z-1)).getBlock()==Blocks.fire;
    }
	
	public static boolean canEntitySeeSky(Entity e){
		return e.worldObj.canBlockSeeSky(new BlockPos(MathHelper.floor_double(e.posX), MathHelper.floor_double(e.posY), MathHelper.floor_double(e.posZ)));
	}
	
	public static EntityPlayer getClientPlayer(){
		return FMLClientHandler.instance().getClient().thePlayer;
	}
	
	public static World getClientWorld(){
		return FMLClientHandler.instance().getClient().theWorld;
	}
}
