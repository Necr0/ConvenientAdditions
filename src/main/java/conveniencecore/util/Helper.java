package conveniencecore.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.server.FMLServerHandler;

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
    	return world.getBlockState(new BlockPos(x+1, y, z)).getBlock()==Blocks.FIRE||
    			world.getBlockState(new BlockPos(x, y+1, z)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x, y, z+1)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x-1, y, z)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x, y-1, z)).getBlock()==Blocks.FIRE||
				world.getBlockState(new BlockPos(x, y, z-1)).getBlock()==Blocks.FIRE;
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
	
	public static World getServerOverworld(){
		return FMLServerHandler.instance().getServer().getEntityWorld();
	}
	
	public static String localize(String in,String... replace){
		String tmp=I18n.translateToLocal(in);
		String match = null;
		for(String s:replace){
			if(match==null){
				match=s;
			}else{
				tmp=tmp.replace(match, s);
				match=null;
			}
		}
		return tmp;
	}
}
