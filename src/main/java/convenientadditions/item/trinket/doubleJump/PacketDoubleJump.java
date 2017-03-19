package convenientadditions.item.trinket.doubleJump;

import convenientadditions.api.inventory.EnumInventory;
import convenientadditions.api.inventory.EnumSubInventory;
import convenientadditions.api.inventory.SlotNotation;
import convenientadditions.api.network.PacketBase;
import convenientadditions.init.ModNetworking;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Necro on 2/28/2017.
 */
public class PacketDoubleJump extends PacketBase<PacketDoubleJump> {

    public SlotNotation slot;

    public PacketDoubleJump(){}

    public PacketDoubleJump(SlotNotation slot){
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
    public PacketDoubleJump onMessage(PacketDoubleJump message, MessageContext ctx) {
        EntityPlayer player=ctx.getServerHandler().player;
        message.slot.player=player;
        ItemStack stack=message.slot.getItem();
        if(stack.getItem() instanceof IDoubleJumpProvider){
            IDoubleJumpProvider item=(IDoubleJumpProvider) stack.getItem();
            if(item.canDoubleJump(player,stack)){
                player.motionY=0;
                player.fallDistance=0;
                player.jump();
                ModNetworking.spawnParticle(player.world,EnumParticleTypes.CLOUD,player.posX,player.posY,player.posZ,0,0,0);
                player.world.playSound(null,player.posX,player.posY,player.posZ, SoundEvents.BLOCK_GRASS_HIT, SoundCategory.PLAYERS,.1f,1.8f);
                item.onDoubleJump(player,stack);
            }
        }
        return null;
    }
}
