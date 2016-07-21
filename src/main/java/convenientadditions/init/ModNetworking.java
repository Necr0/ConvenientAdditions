package convenientadditions.init;

import convenientadditions.ConvenientAdditions;
import convenientadditions.block.inventoryProxy.filtered.MessageInventoryProxyFiltered;
import convenientadditions.block.setProvider.MessageSetProvider;
import convenientadditions.item.channelModule.color.MessageColorChannelModule;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditions.MODID);
	
	public static void init(){
    	NetworkRegistry.INSTANCE.registerGuiHandler(ConvenientAdditions.INSTANCE, new ModGuiHandler());
    	int i=0;
    	INSTANCE.registerMessage(MessageSetProvider.class, MessageSetProvider.class, i++, Side.SERVER);
    	INSTANCE.registerMessage(MessageColorChannelModule.class, MessageColorChannelModule.class, i++, Side.SERVER);
    	INSTANCE.registerMessage(MessageInventoryProxyFiltered.class, MessageInventoryProxyFiltered.class, i++, Side.SERVER);
	}
}
