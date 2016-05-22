package conveniencecore.network;

import conveniencecore.ConvenienceCore;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
	public static SimpleNetworkWrapper INSTANCE=NetworkRegistry.INSTANCE.newSimpleChannel(ConvenienceCore.MOD_ID);
	
	public static void init(){
		INSTANCE.registerMessage(PacketEntitySpecialItemBehaviours.class, PacketEntitySpecialItemBehaviours.class, Reference.specialItemEntityBehavioursDisc, Side.CLIENT);
	}
}
