package convenientadditions.api.provider.itemnetwork;

import conveniencecore.ProviderSystem;
import conveniencecore.ProviderSystem.IMatcher;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemNetworkProvider {
	public static final String ITEMPROVIDER_CHANNEL_NAME="ITEMPROVIDER";
	
	private static boolean registered=false;
	
	public static void init(){
		if(!registered){
			MinecraftForge.EVENT_BUS.register(new ItemNetworkProvider());
			ProviderSystem.INSTANCE.addChannel(ITEMPROVIDER_CHANNEL_NAME, IItemProvider.class);
			registered=true;
		}
	}
	
	@SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent e)
    {
		ProviderSystem.INSTANCE.wipeChannel(ITEMPROVIDER_CHANNEL_NAME);
    }

	public static void addEntry(IMatcher matcher, IItemProvider provider){
		ProviderSystem.INSTANCE.addChannelEntry(ITEMPROVIDER_CHANNEL_NAME, matcher, provider);
	}
}
