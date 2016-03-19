package convenientadditions.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
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
	
	public static boolean checkForFire(World world,int x,int y,int z){
    	return world.getBlock(x+1, y, z)==Blocks.fire||
    			world.getBlock(x, y+1, z)==Blocks.fire||
    			world.getBlock(x, y, z+1)==Blocks.fire||
    			world.getBlock(x-1, y, z)==Blocks.fire||
    			world.getBlock(x, y-1, z)==Blocks.fire||
    			world.getBlock(x, y, z-1)==Blocks.fire;
    }
	
	public static boolean canEntitySeeSky(Entity e){
		return e.worldObj.canBlockSeeTheSky(MathHelper.floor_double(e.posX), MathHelper.floor_double(e.posY), MathHelper.floor_double(e.posZ));
	}
	
	public static EntityPlayer getClientPlayer(){
		return FMLClientHandler.instance().getClient().thePlayer;
	}
	
	public static World getClientWorld(){
		return FMLClientHandler.instance().getClient().theWorld;
	}
}
