package conveniencecore.network;

import conveniencecore.ConvenienceCore;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenienceCore.MOD_ID);
	
	public static void init(){
		INSTANCE.registerMessage(PacketExtendedExplosion.class, PacketExtendedExplosion.class, 0, Side.CLIENT);
	}
}
