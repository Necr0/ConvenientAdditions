package convenientadditions.init;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class TickHandler {
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		if(e.side==Side.CLIENT)
			return;
        EntityPlayer player = e.player;
		WorldServer world = (WorldServer)player.worldObj;
		Random random = new Random();
        InventoryPlayer inv = player.inventory;
        for(int i=0;i<9;i++){
        	ItemStack stack=inv.getStackInSlot(i);
        	if(stack!=null&&stack.getItem()==ModItems.itemSunstone){
        		if(ModItems.itemSunstone.isActive(stack)){
	        		ModItems.itemSunstone.chargeItem(stack, -1);
	        		for(int x=0;x<9;x++){
	        			for(int y=0;y<9;y++){
	        				for(int z=0;z<9;z++){
	        					int 	x1=x-4+(int)player.posX,
	        							y1=y-4+(int)player.posY,
	        							z1=z-4+(int)player.posZ;
	        					Block b=world.getBlock(x1, y1, z1);
	        					if(b.isAir(world,x1,y1,z1)&&b!=ModBlocks.tempLightBlock){
	        						world.setBlock(x1, y1, z1, ModBlocks.tempLightBlock, 0, 3);
	        						world.scheduleBlockUpdate(x1, y1, z1, ModBlocks.tempLightBlock, 20+random.nextInt(20));
	        					}
	                		}
	            		}
	        		}
        		}else{
        			if(!world.provider.hasNoSky&&!world.isRaining()&&world.isDaytime()&&world.canBlockSeeTheSky((int)player.posX,(int)player.posY,(int)player.posZ)){
        				ModItems.itemSunstone.chargeItem(stack, 14);
        			}
        		}
        	}
        }
    }

}
