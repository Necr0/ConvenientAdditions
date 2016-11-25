package convenientadditions.item.transmutationTome;

import convenientadditions.api.network.PacketBase;
import convenientadditions.api.util.Helper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageTransmutationTome extends PacketBase<MessageTransmutationTome> {
    byte type;
    float value;

    public MessageTransmutationTome() {
    }

    //types: 0,1,2
    public MessageTransmutationTome(byte type, float value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readByte();
        this.value = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(type);
        buf.writeFloat(value);
    }

    @Override
    public MessageTransmutationTome onMessage(MessageTransmutationTome message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            if (Helper.getClientPlayer().openContainer instanceof ContainerTransmutationTome) {
                ContainerTransmutationTome c = (ContainerTransmutationTome) Helper.getClientPlayer().openContainer;
                if (message.type == 0)
                    c.setXPOverride(message.value);
                else if (message.type == 1) {
                    c.onOperationFinished();
                }
            }
        }
        return null;
    }

}
