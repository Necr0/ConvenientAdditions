package convenientadditions.api.network;

import convenientadditions.api.util.Helper;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketParticle extends PacketBase<PacketParticle> {
    public EnumParticleTypes t;
    public double x,y,z,xs,ys,zs;
    public int[] params;

    public PacketParticle(){}

    public PacketParticle(EnumParticleTypes t,double x,double y,double z,double xs,double ys,double zs){
        this.t=t;
        this.x=x;
        this.y=y;
        this.z=z;
        this.xs=xs;
        this.ys=ys;
        this.zs=zs;
        this.params=new int[0];
    }

    public PacketParticle(EnumParticleTypes t,double x,double y,double z,double xs,double ys,double zs, int... params){
        this.t=t;
        this.x=x;
        this.y=y;
        this.z=z;
        this.xs=xs;
        this.ys=ys;
        this.zs=zs;
        this.params=params;
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
        int count=buf.readInt();
        params=new int[count];
        for(int i=0;i<count;i++){
            params[i]=buf.readInt();
        }
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
        buf.writeInt(params.length);
        for (int i:params) {
            buf.writeInt(i);
        }
    }

    @Override
    public PacketParticle onMessage(PacketParticle message, MessageContext ctx) {
        onClientReceive(message,ctx);
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void onClientReceive(PacketParticle message, MessageContext ctx){
        Helper.getClientWorld().spawnParticle(message.t,message.x,message.y,message.z,message.xs,message.ys,message.zs,message.params);
    }
}
