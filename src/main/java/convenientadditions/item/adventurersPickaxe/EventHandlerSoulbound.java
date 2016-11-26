package convenientadditions.item.adventurersPickaxe;

import convenientadditions.api.item.ISoulbound;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Iterator;

public class EventHandlerSoulbound {
	
	@SubscribeEvent
	public void onPlayerDrops(PlayerDropsEvent e)
    {
		Iterator<EntityItem> i=e.getDrops().iterator();
    	while(i.hasNext()){
    		EntityItem ent=i.next();
    		ItemStack stack=ent.getEntityItem();
    		if(!stack.isEmpty()&&stack.getItem() instanceof ISoulbound&&((ISoulbound)stack.getItem()).isSoulbound(stack, e.getEntityPlayer())){
    			IItemHandler h=e.getEntityPlayer().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
    			ItemStack out=tryInsert(stack, h);
    			if(out==null)
    				i.remove();
    			else
    				stack.setCount(out.getCount());
    		}
    	}
    }
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone e)
    {
		EntityPlayer original=e.getOriginal();
		EntityPlayer clone=e.getEntityPlayer();
		IItemHandler h=original.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		for(int slot=0;slot<h.getSlots();slot++){
			ItemStack stack=h.getStackInSlot(slot);
    		if(stack!=null&&stack.getItem() instanceof ISoulbound&&((ISoulbound)stack.getItem()).isSoulbound(stack, original)){
    			IItemHandler h2=clone.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
    			ItemStack out=tryInsert(stack, h2);
    			if(out==null){
    				h.extractItem(slot, 64, false);
    			}else{
					stack.setCount(out.getCount());
    			}
    		}
		}
    }
	
	public ItemStack tryInsert(ItemStack s,IItemHandler h){
		ItemStack tmp=s.copy();
		for(int slot=0;slot<h.getSlots();slot++){
			tmp=h.insertItem(slot, tmp, false);
			if(tmp==null||tmp.getCount()==0)
				return null;
		}
		return tmp;
	}
}
