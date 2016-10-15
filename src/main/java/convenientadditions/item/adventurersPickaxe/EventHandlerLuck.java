package convenientadditions.item.adventurersPickaxe;

import conveniencecore.util.Helper;
import convenientadditions.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerLuck {

	@SubscribeEvent
	public void luck(HarvestDropsEvent e)
    {
		//e.getState().getBlock().getDrops(e.getWorld(), e.getPos(), e.getState(), 3);
    	if(e.getHarvester()!=null&&!e.getHarvester().isSneaking()&&e.getHarvester().getHeldItemMainhand()!=null&&e.getHarvester().getHeldItemMainhand().getItem()==ModItems.itemAdventurersPickaxe){
    		int luck=(int)ModItems.itemAdventurersPickaxe.getToolProperty(e.getHarvester().getHeldItemMainhand(), "mining_luck");
			if(Helper.doesOreDictMatch(e.getState(), "ore", true)&&luck>0){
				e.setDropChance(0f);
				for(ItemStack s:e.getState().getBlock().getDrops(e.getWorld(), e.getPos(), e.getState(), luck)){
					double x=e.getPos().getX()+.5,y=e.getPos().getY()+.5,z=e.getPos().getZ()+.5;
					e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(),x,y,z,s));
				}
    		}
    	}
    }
}
