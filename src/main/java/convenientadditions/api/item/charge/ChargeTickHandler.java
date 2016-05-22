package convenientadditions.api.item.charge;

import baubles.api.BaublesApi;
import convenientadditions.api.item.IPlayerInventoryTick;
import convenientadditions.api.util.Helper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ChargeTickHandler {
	private static boolean chargeTickHandlerRegistered=false;
	public static final int[] OFFHAND_SLOTS=new int[]{255};
	public static final int[] HOTBAR_SLOTS=new int[]{0,1,2,3,4,5,6,7,8};
	public static final int[] ARMOR_SLOTS=new int[]{155,156,157,158};
	public static final int[] BAUBLES_SLOTS=new int[]{-1,-2,-3,-4};
	
	@SubscribeEvent
    public void onPlayerSunlightChargeTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		InventoryPlayer playerInv=player.inventory;

		if(player.worldObj.isDaytime() && !player.worldObj.isRaining() && Helper.canEntitySeeSky(player) && e.side==Side.SERVER){
			//BAUBLES SUNLIGHT
			IInventory baublesInv=BaublesApi.getBaubles(player);
			for(int i=0;i<baublesInv.getSizeInventory();i++){
				ItemStack stack=baublesInv.getStackInSlot(i);
				if(stack!=null && stack.getItem() instanceof ISunlightChargeable){
					ISunlightChargeable sitem=(ISunlightChargeable)(stack.getItem());
					if(sitem.isSunlightChargeable(stack, BAUBLES_SLOTS[i])){
						sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, BAUBLES_SLOTS[i]));
					}
				}
			}
			//VANILLA SUNLIGHT
			//MAIN
			for(int i=0;i<playerInv.mainInventory.length;i++){
				ItemStack stack=playerInv.mainInventory[i];
				if(stack!=null && stack.getItem() instanceof ISunlightChargeable){
					ISunlightChargeable sitem=(ISunlightChargeable)(stack.getItem());
					if(sitem.isSunlightChargeable(stack, i)){
						sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, i));
					}
				}
			}
			//ARMOR
			for(int i=0;i<playerInv.armorInventory.length;i++){
				ItemStack stack=playerInv.armorInventory[i];
				if(stack!=null && stack.getItem() instanceof ISunlightChargeable){
					ISunlightChargeable sitem=(ISunlightChargeable)(stack.getItem());
					if(sitem.isSunlightChargeable(stack, ARMOR_SLOTS[i])){
						sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, ARMOR_SLOTS[i]));
					}
				}
			}
			//OFFHAND
			for(ItemStack stack:playerInv.offHandInventory){
				if(stack!=null && stack.getItem() instanceof ISunlightChargeable){
					ISunlightChargeable sitem=(ISunlightChargeable)(stack.getItem());
					if(sitem.isSunlightChargeable(stack, OFFHAND_SLOTS[0])){
						sitem.chargeItem(stack, sitem.getSunlightChargeRate(stack, OFFHAND_SLOTS[0]));
					}
				}
			}
		}
    }
	
	@SubscribeEvent
    public void onPlayerInventoryTick(TickEvent.PlayerTickEvent e)
    {
		EntityPlayer player = e.player;
		InventoryPlayer playerInv=player.inventory;
		//MAIN
		for(int i=0;i<playerInv.mainInventory.length;i++){
			ItemStack stack=playerInv.mainInventory[i];
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, i, player);;
			}
		}
		//ARMOR
		for(int i=0;i<playerInv.armorInventory.length;i++){
			ItemStack stack=playerInv.armorInventory[i];
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, ARMOR_SLOTS[i], player);;
			}
		}
		//OFFHAND
		for(ItemStack stack:playerInv.offHandInventory){
			if(stack!=null && stack.getItem() instanceof IPlayerInventoryTick){
				((IPlayerInventoryTick)stack.getItem()).onPlayerInventoryTick(stack, OFFHAND_SLOTS[0], player);;
			}
		}
    }
	
	public static void init(){
		if(!chargeTickHandlerRegistered){
			MinecraftForge.EVENT_BUS.register(new ChargeTickHandler());
			chargeTickHandlerRegistered=true;
		}
	}

}
