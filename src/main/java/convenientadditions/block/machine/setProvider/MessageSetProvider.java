package convenientadditions.block.machine.setProvider;

import convenientadditions.api.network.PacketBase;
import convenientadditions.base.block.tileentity.CAContainerTileEntity;
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

    public MessageSetProvider(){}

    //types: 0=inputfilter 1=reset, 2=dv, 3=nbt, 4=rsmode
    public MessageSetProvider(BlockPos pos, byte type, byte value) {
        this.pos = pos;
        this.type = type;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.type = buf.readByte();
        this.value = buf.readByte();
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
        if(ctx.side==Side.CLIENT || ctx.getServerHandler().player.getDistanceSq(message.pos) > CAContainerTileEntity.MAX_DISTANCE)
            return null;
        World w = ctx.getServerHandler().player.getEntityWorld();
        TileEntity t = w.getTileEntity(message.pos);
        if (t != null && t instanceof TileEntitySetProvider) {
            TileEntitySetProvider te = ((TileEntitySetProvider) t);
            switch (message.type) {
                case 0:
                    te.setFilterInput(message.value != 0);
                    break;
                case 1:
                    te.setIgnoreDV(message.value != 0);
                    break;
                case 2:
                    te.setIgnoreNBT(message.value != 0);
                    break;
                case 3:
                    te.setPushMode(message.value);
                    break;
                case 4:
                    te.setIgnoreOutput(message.value != 0);
                    break;
                default:
                    break;
            }
        }
        return null;
    }

}
