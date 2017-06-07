package convenientadditions.item.trinket.doubleJump;

import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.EnumSubInventory;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.network.PacketBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Necro on 2/28/2017.
 */
public class PacketParachute extends PacketBase<PacketParachute> {

    public SlotNotation slot;

    public PacketParachute(){}

    public PacketParachute(SlotNotation slot){
        this.slot=slot;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        slot=new SlotNotation(null,EnumInventory.values()[buf.readByte()],EnumSubInventory.values()[buf.readByte()],buf.readByte());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(slot.getInventory().ordinal());
        buf.writeByte(slot.getSubInventory().ordinal());
        buf.writeByte(slot.getSlot());
    }

    @Override
    public PacketParachute onMessage(PacketParachute message, MessageContext ctx) {
        EntityPlayer player=ctx.getServerHandler().player;
        message.slot.player=player;
        ItemStack stack=message.slot.getItem();
        if(stack.getItem() instanceof IDoubleJumpProvider){
            IDoubleJumpProvider item=(IDoubleJumpProvider) stack.getItem();
            if(item.isParachute(player,stack,message.slot)){
                player.world.playSound(null,player.posX,player.posY,player.posZ, SoundEvents.BLOCK_GRASS_HIT, SoundCategory.PLAYERS,.1f,1.8f);
                item.onParachuteOpen(player,stack,message.slot);
            }
        }
        return null;
    }
}
