package conveniencecore.network;

import net.minecraftforge.fml.relauncher.Side;

public class ModNetworking {
	
	public static void init(){
		convenientadditions.init.ModNetworking.INSTANCE.registerMessage(PacketExtendedExplosion.class, PacketExtendedExplosion.class, 255, Side.CLIENT);
	}
}
