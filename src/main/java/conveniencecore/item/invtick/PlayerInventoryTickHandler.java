package conveniencecore.item.invtick;

import conveniencecore.api.item.IPlayerInventoryTick;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerInventoryTickHandler {
	private static boolean registered=false;
	public static final int[] OFFHAND_SLOTS=new int[]{255};
	public static final int[] HOTBAR_SLOTS=new int[]{0,1,2,3,4,5,6,7,8};
	public static final int[] ARMOR_SLOTS=new int[]{155,156,157,158};
	public static final int[] BAUBLES_SLOTS=new int[]{-1,-2,-3,-4};
	
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
		if(!registered){
			MinecraftForge.EVENT_BUS.register(new PlayerInventoryTickHandler());
			registered=true;
		}
	}
}
