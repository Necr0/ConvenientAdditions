package convenientadditions.item.module.text;

import convenientadditions.api.network.PacketBase;
import convenientadditions.init.ModItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.io.UnsupportedEncodingException;

public class MessageTextChannelModule extends PacketBase<MessageTextChannelModule> {
    boolean mainhand;
    String value;

    public MessageTextChannelModule(){}

    //panels: 0,1,2
    public MessageTextChannelModule(boolean mainhand, String value) {
        this.mainhand = mainhand;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mainhand = buf.readBoolean();
        int length=Math.min(20,buf.readInt());
        byte[] tmp=new byte[length];
        buf.readBytes(tmp);
        try {
            value=new String(tmp,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            value="";
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(mainhand);
        buf.writeInt(value.length());
        try {
            buf.writeBytes(value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MessageTextChannelModule onMessage(MessageTextChannelModule message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            ItemStack stack = ctx.getServerHandler().player.getHeldItem(message.mainhand ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
            if (!stack.isEmpty() && stack.getItem() == ModItems.itemModuleText) {
                if(!stack.hasTagCompound())
                    stack.setTagCompound(new NBTTagCompound());
                stack.getTagCompound().setString("MATCHER_TEXT",message.value);
            }
        }
        return null;
    }

}
