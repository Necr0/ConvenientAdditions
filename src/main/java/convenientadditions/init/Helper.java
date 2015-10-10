package convenientadditions.init;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Helper {
	public static void spawnItemInPlace(World w,double x,double y,double z,ItemStack i){
		EntityItem e=new EntityItem(w, x, y, z, i);
		e.setVelocity(0, 0, 0);
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
}
