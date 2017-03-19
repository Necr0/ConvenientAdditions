package convenientadditions.block.machine.itemTransmitter;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.block.CABlockMachine;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockItemTransmitter extends CABlockMachine {
    public BlockItemTransmitter() {
        super(ModConstants.BlockNames.itemTransmitter, Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultAdditionalInfo(true);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityItemTransmitter();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_ITEM_TRANSMITTER_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityItemTransmitter) {
            dropItemHandler(world,pos,((TileEntityItemTransmitter) te).buffer,true);
            dropItemHandler(world,pos,((TileEntityItemTransmitter) te).channels,true);
        }
    }
}
