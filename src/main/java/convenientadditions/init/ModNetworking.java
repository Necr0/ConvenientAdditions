package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditionsMod.MODID);
	
	public static void init(){
		//
	}
}
