package convenientadditions.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import baubles.api.BaublesApi;
import convenientadditions.api.IChargable;
import convenientadditions.api.IPlayerInventoryTick;
import convenientadditions.api.ISunlightChargable;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class TickHandler {
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		//BAUBLES SUNLIGHT
		IInventory baublesInv=BaublesApi.getBaubles(player);
		for(int i=0;i<baublesInv.getSizeInventory();i++){
			ItemStack stack=baublesInv.getStackInSlot(-i-1);
			if(stack!=null && stack.getItem() instanceof ISunlightChargable && stack.getItem() instanceof IChargable){
				ISunlightChargable sitem=(ISunlightChargable)(stack.getItem());
				if(sitem.isSunlightChargable(stack, -i-1)){
					if(e.player.worldObj.provider.hasNoSky&&!e.player.worldObj.isRaining()&&e.player.worldObj.isDaytime()&&e.player.worldObj.canBlockSeeTheSky((int)player.posX,(int)player.posY,(int)player.posZ)){
						((IChargable)sitem).chargeItem(stack, sitem.getSunlightChargeRate(stack, -i-1));
					}
				}
			}
		}
		//VANILLA SUNLIGHT
		IInventory playerInv=player.inventory;
		for(int i=0;i<playerInv.getSizeInventory();i++){
			ItemStack stack=playerInv.getStackInSlot(i);
			if(stack!=null && stack.getItem() instanceof ISunlightChargable && stack.getItem() instanceof IChargable){
				ISunlightChargable sitem=(ISunlightChargable)(stack.getItem());
				if(sitem.isSunlightChargable(stack, i)){
					if(e.player.worldObj.provider.hasNoSky&&!e.player.worldObj.isRaining()&&e.player.worldObj.isDaytime()&&e.player.worldObj.canBlockSeeTheSky((int)player.posX,(int)player.posY,(int)player.posZ)){
						((IChargable)sitem).chargeItem(stack, sitem.getSunlightChargeRate(stack, i));
					}
				}
			}
		}
		//VANILLA TICKABLE
		for(int i=0;i<playerInv.getSizeInventory();i++){
			ItemStack stack=playerInv.getStackInSlot(i);
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, i, player);;
			}
		}
		
    }

}
