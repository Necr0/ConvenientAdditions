package convenientadditions.init;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.network.CAEntitySpecialItemBehavioursPacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenientAdditionsMod.MODID);
	
	public static void init(){
		INSTANCE.registerMessage(CAEntitySpecialItemBehavioursPacket.class, CAEntitySpecialItemBehavioursPacket.class, Reference.specialItemEntityBehavioursDisc, Side.CLIENT);
	}
}
