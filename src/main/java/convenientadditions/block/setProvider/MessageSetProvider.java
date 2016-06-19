package convenientadditions.block.setProvider;

import conveniencecore.network.PacketBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageSetProvider extends PacketBase<MessageSetProvider> {
	BlockPos pos;
	byte type;
	byte value;

	public MessageSetProvider() {}

	//types: 0=reset, 1=dv, 2=nbt, 3=mode 
	public MessageSetProvider(BlockPos pos,byte type,byte value) {
		this.pos=pos;
		this.type=type;
		this.value=value;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos=new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
		this.type=buf.readByte();
		this.value=buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeByte(type);
		buf.writeByte(value);
	}

	@Override
	public MessageSetProvider onMessage(MessageSetProvider message, MessageContext ctx) {
		if(ctx.side==Side.SERVER){
			World w=ctx.getServerHandler().playerEntity.worldObj;
			TileEntity t=w.getTileEntity(message.pos);
			if(t!=null&&t instanceof TileEntitySetProvider){
				TileEntitySetProvider te=((TileEntitySetProvider)t);
				switch(message.type){
					case 0:
						te.reset();
						break;
					case 1:
						te.setIgnoreDV(message.value==0?false:true);
						break;
					case 2:
						te.setIgnoreNBT(message.value==0?false:true);
						break;
					case 3:
						te.setResetMode(message.value);
						break;
					default:
						break;
				}
			}
		}
		return null;
	}

}
