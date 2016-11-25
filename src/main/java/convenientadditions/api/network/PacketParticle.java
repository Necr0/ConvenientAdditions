package convenientadditions.api.network;

import convenientadditions.api.util.Helper;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketParticle extends PacketBase<PacketParticle> {
    public EnumParticleTypes t;
    public double x,y,z,xs,ys,zs;

    public PacketParticle(){}

    public PacketParticle(EnumParticleTypes t,double x,double y,double z,double xs,double ys,double zs){
        this.t=t;
        this.x=x;
        this.y=y;
        this.z=z;
        this.xs=xs;
        this.ys=ys;
        this.zs=zs;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        t=EnumParticleTypes.getParticleFromId(buf.readInt());
        x=buf.readDouble();
        y=buf.readDouble();
        z=buf.readDouble();
        xs=buf.readDouble();
        ys=buf.readDouble();
        zs=buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(t.getParticleID());
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(xs);
        buf.writeDouble(ys);
        buf.writeDouble(zs);
    }

    @Override
    public PacketParticle onMessage(PacketParticle message, MessageContext ctx) {
        Helper.getClientWorld().spawnParticle(message.t,message.x,message.y,message.z,message.xs,message.ys,message.zs);
        return null;
    }
}
