package convenientadditions.block.machine.jumpPad;

import convenientadditions.api.network.PacketBase;
import convenientadditions.init.ModBlocks;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageJumpPad extends PacketBase<MessageJumpPad> {
    public boolean jump;

    public MessageJumpPad(){}

    public MessageJumpPad(boolean jump){
        this.jump=jump;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        jump=buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(jump);
    }

    @Override
    public MessageJumpPad onMessage(MessageJumpPad message, MessageContext ctx) {
        if(ctx.side== Side.CLIENT)
            return null;
        EntityPlayer p=ctx.getServerHandler().player;
        BlockPos pos=new BlockPos(p).down();
        if(p.world.getBlockState(pos).getBlock()==ModBlocks.jumpPadBlock){
            BlockPos target=ModBlocks.jumpPadBlock.getTargetLocation(pos,p.world,!message.jump);
            if(target!=null){
                if(!MinecraftForge.EVENT_BUS.post(new EnderTeleportEvent(p,target.getX()+.5,target.getY()+1,target.getZ()+.5, 0f))){
                    p.world.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, .15F, 2F);
                    p.setPositionAndUpdate(target.getX()+.5,target.getY()+1,target.getZ()+.5);
                }
            }
        }
        return null;
    }
}
