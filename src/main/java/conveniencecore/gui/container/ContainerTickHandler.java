package conveniencecore.gui.container;

import conveniencecore.api.container.IContainerTickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ContainerTickHandler {
	private static boolean registered=false;
	
	@SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e)
    {
		if(e.player.openContainer instanceof IContainerTickable)
			((IContainerTickable)e.player.openContainer).tickContainer(e.player, e.side);
    }
	
	public static void init(){
		if(!registered){
			MinecraftForge.EVENT_BUS.register(new ContainerTickHandler());
			registered=true;
		}
	}
}
