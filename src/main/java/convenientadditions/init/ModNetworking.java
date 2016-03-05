package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ModNetworking {
	public static SimpleNetworkWrapper registry;
	
	public static void init(){
		registry=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditionsMod.MODID);
		
		//registry.registerMessage(messageHandler, requestMessageType, discriminator, side);
	}
}
