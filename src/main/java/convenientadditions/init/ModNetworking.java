package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.network.CAEntitySpecialItemBehavioursPacket;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditionsMod.MODID);
	
	public static void init(){
		INSTANCE.registerMessage(CAEntitySpecialItemBehavioursPacket.class, CAEntitySpecialItemBehavioursPacket.class, Reference.specialItemEntityBehavioursDisc, Side.CLIENT);
	}
}
