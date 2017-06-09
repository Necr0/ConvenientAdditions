package convenientadditions.block.machine.proximitySensor;

import convenientadditions.api.network.PacketBase;
import convenientadditions.base.block.tileentity.CAContainerTileEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Necro on 3/12/2017.
 */
public class MessageProximitySensor extends PacketBase<MessageProximitySensor> {
    public BlockPos pos;
    public double range;

    public MessageProximitySensor(){}

    public MessageProximitySensor(BlockPos pos, double range) {
        this.pos = pos;
        this.range = range;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.range = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        buf.writeDouble(range);
    }

    @Override
    public MessageProximitySensor onMessage(MessageProximitySensor message, MessageContext ctx) {
        if(ctx.side==Side.CLIENT || ctx.getServerHandler().player.getDistanceSq(message.pos) > CAContainerTileEntity.MAX_DISTANCE)
            return null;
        if(message.range>=.5d&&message.range<=15d&&message.range%.5==0){
            TileEntity te=ctx.getServerHandler().player.world.getTileEntity(message.pos);
            if(te!=null && te instanceof TileEntityProximitySensor){
                ((TileEntityProximitySensor) te).range=message.range;
                ((TileEntityProximitySensor) te).recalculateStrenghts();
                te.markDirty();
                IBlockState state = te.getWorld().getBlockState(te.getPos());
                te.getWorld().notifyBlockUpdate(te.getPos(), state, state, 3);
            }
        }
        return null;
    }
}
