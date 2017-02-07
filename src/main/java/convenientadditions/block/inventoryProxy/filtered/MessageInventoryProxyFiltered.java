package convenientadditions.block.inventoryProxy.filtered;

import convenientadditions.api.network.PacketBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageInventoryProxyFiltered extends PacketBase<MessageInventoryProxyFiltered> {
    BlockPos pos;
    byte type;
    byte value;

    public MessageInventoryProxyFiltered() {
    }

    //types: 0=dv, 1=nbt
    public MessageInventoryProxyFiltered(BlockPos pos, byte type, byte value) {
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
    public MessageInventoryProxyFiltered onMessage(MessageInventoryProxyFiltered message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            World w = ctx.getServerHandler().player.getEntityWorld();
            TileEntity t = w.getTileEntity(message.pos);
            if (t != null && t instanceof TileEntityInventoryProxyFiltered) {
                TileEntityInventoryProxyFiltered te = ((TileEntityInventoryProxyFiltered) t);
                switch (message.type) {
                    case 0:
                        te.setIgnoreDV(message.value != 0);
                        break;
                    case 1:
                        te.setIgnoreNBT(message.value != 0);
                        break;
                    case 2:
                        te.setBlacklist(message.value != 0);
                        break;
                    default:
                        break;
                }
            }
        }
        return null;
    }

}
