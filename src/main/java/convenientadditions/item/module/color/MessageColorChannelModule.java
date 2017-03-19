package convenientadditions.item.module.color;

import convenientadditions.api.network.PacketBase;
import convenientadditions.init.ModItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageColorChannelModule extends PacketBase<MessageColorChannelModule> {
    boolean mainhand;
    byte panel;
    byte value;

    public MessageColorChannelModule() {
    }

    //panels: 0,1,2
    public MessageColorChannelModule(boolean mainhand, byte panel, byte value) {
        this.mainhand = mainhand;
        this.panel = panel;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mainhand = buf.readBoolean();
        this.panel = buf.readByte();
        this.value = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(mainhand);
        buf.writeByte(panel);
        buf.writeByte(value);
    }

    @Override
    public MessageColorChannelModule onMessage(MessageColorChannelModule message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            ItemStack stack = ctx.getServerHandler().player.getHeldItem(message.mainhand ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
            if (!stack.isEmpty() && stack.getItem() == ModItems.itemModuleColor && stack.hasTagCompound() && (message.panel >= 0 && message.panel <= 2)) {
                stack.getTagCompound().setInteger("MATCHER_DYE_" + message.panel, (message.value >= 0 && message.value <= 15) ? message.value : 0);
            }
        }
        return null;
    }

}
