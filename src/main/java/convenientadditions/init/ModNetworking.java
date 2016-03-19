package convenientadditions.init;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.network.CAEntitySpecialItemBehavioursPacket;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditionsMod.MODID);
	
	public static void init(){
		INSTANCE.registerMessage(CAEntitySpecialItemBehavioursPacket.class, CAEntitySpecialItemBehavioursPacket.class, Reference.specialItemEntityBehavioursDisc, Side.CLIENT);
	}
}
