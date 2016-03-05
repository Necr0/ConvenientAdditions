package convenientadditions.api.network;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import org.apache.commons.lang3.ArrayUtils;

import convenientadditions.api.entity.EntitySpecialItem;
import convenientadditions.api.util.Helper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public abstract class PacketEntitySpecialItemBehaviours extends PacketBase<PacketEntitySpecialItemBehaviours> {
	int id;
	int count;
	long[] behaviours;
	
	public PacketEntitySpecialItemBehaviours(){}
	
	public PacketEntitySpecialItemBehaviours(EntitySpecialItem ent,long... behaviours){
		this.id=ent.getEntityId();
		this.count=behaviours.length;
		this.behaviours=behaviours;
	}
	
	public PacketEntitySpecialItemBehaviours(EntitySpecialItem ent,List<Long> behaviours){
		this.id=ent.getEntityId();
		Long[] out=behaviours.toArray(new Long[behaviours.size()]);
		this.behaviours=ArrayUtils.toPrimitive(out);
		this.count=this.behaviours.length;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id=buf.readInt();
		this.count=buf.readInt();
		this.behaviours=new long[count];
		for(int i=0;i<count;i++){
			behaviours[i]=buf.readLong();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.id);
		buf.writeInt(this.count);
		for(int i=0;i<count;i++){
			buf.writeLong(this.behaviours[i]);
		}
	}

	@Override
	public PacketEntitySpecialItemBehaviours onMessage(PacketEntitySpecialItemBehaviours message, MessageContext ctx) {
		if(ctx.side==Side.CLIENT){
			Entity ent=Helper.getClientWorld().getEntityByID(id);
			if(ent!=null&&ent instanceof EntitySpecialItem){
				EntitySpecialItem item=(EntitySpecialItem)ent;
				item.addBehaviour(this.behaviours);
			}
		}
		return null;
	}
}
