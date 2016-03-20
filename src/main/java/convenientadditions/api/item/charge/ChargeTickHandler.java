package convenientadditions.api.item.charge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import baubles.api.BaublesApi;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.api.util.Helper;

public class ChargeTickHandler {
	private static boolean chargeTickHandlerRegistered=false;
	
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		//BAUBLES SUNLIGHT
		if(e.side==Side.SERVER){
			IInventory baublesInv=BaublesApi.getBaubles(player);
			for(int i=0;i<baublesInv.getSizeInventory();i++){
				ItemStack stack=baublesInv.getStackInSlot(i);
				if(stack!=null && stack.getItem() instanceof ISunlightChargeable){
					ISunlightChargeable sitem=(ISunlightChargeable)(stack.getItem());
					if(sitem.isSunlightChargeable(stack, -i-1)){
						if(player.worldObj.isDaytime() && !player.worldObj.isRaining() && Helper.canEntitySeeSky(player)){
							sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, -i-1));
						}
					}
				}
			}
			//VANILLA SUNLIGHT
			IInventory playerInv=player.inventory;
			for(int i=0;i<playerInv.getSizeInventory();i++){
				ItemStack stack=playerInv.getStackInSlot(i);
				if(stack!=null && stack.getItem() instanceof ISunlightChargeable){
					ISunlightChargeable sitem=(ISunlightChargeable)(stack.getItem());
					if(sitem.isSunlightChargeable(stack, i)){
						if(player.worldObj.isDaytime() && !player.worldObj.isRaining() && Helper.canEntitySeeSky(player)){
							sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, i));
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
	
	public static void init(){
		if(!chargeTickHandlerRegistered){
			FMLCommonHandler.instance().bus().register(new ChargeTickHandler());
			chargeTickHandlerRegistered=true;
		}
	}

}
