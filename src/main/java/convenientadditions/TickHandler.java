package convenientadditions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import baubles.api.BaublesApi;
import convenientadditions.api.item.IChargable;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.api.item.ISunlightChargable;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class TickHandler {
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		//BAUBLES SUNLIGHT
		if(e.side==Side.SERVER){
			IInventory baublesInv=BaublesApi.getBaubles(player);
			for(int i=0;i<baublesInv.getSizeInventory();i++){
				ItemStack stack=baublesInv.getStackInSlot(i);
				if(stack!=null && stack.getItem() instanceof ISunlightChargable && stack.getItem() instanceof IChargable){
					ISunlightChargable sitem=(ISunlightChargable)(stack.getItem());
					if(sitem.isSunlightChargable(stack, -i-1)){
						if(player.worldObj.isDaytime() && !player.worldObj.isRaining() && e.player.worldObj.canBlockSeeTheSky((int)player.posX,(int)player.posY,(int)player.posZ)){
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
						if(player.worldObj.isDaytime() && !player.worldObj.isRaining() && e.player.worldObj.canBlockSeeTheSky((int)player.posX,(int)player.posY,(int)player.posZ)){
							((IChargable)sitem).chargeItem(stack, sitem.getSunlightChargeRate(stack, i));
						}
					}
				}
			}
		}
		//VANILLA TICKABLE
		IInventory playerInv=player.inventory;
		for(int i=0;i<playerInv.getSizeInventory();i++){
			ItemStack stack=playerInv.getStackInSlot(i);
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, i, player);;
			}
		}
		
    }

}
