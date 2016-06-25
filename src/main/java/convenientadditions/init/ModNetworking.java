package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import convenientadditions.block.setProvider.MessageSetProvider;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditions.MODID);
	
	public static void init(){
    	NetworkRegistry.INSTANCE.registerGuiHandler(ConvenientAdditions.INSTANCE, new ModGuiHandler());
    	INSTANCE.registerMessage(MessageSetProvider.class, MessageSetProvider.class, 0, Side.SERVER);
	}
}
