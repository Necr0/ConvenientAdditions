package convenientadditions.api.network;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public abstract class PacketEntitySpecialItemBehaviours implements IMessage {

	@Override
	public void fromBytes(ByteBuf buf) {
		int count=buf.readInt();
		long[] behaviours=new long[count];
		for(int i=0;i<count;i++){
			behaviours[i]=buf.readLong();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

}
