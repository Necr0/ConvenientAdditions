package convenientadditions.block.workStation;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.block.CABlockContainer;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Necro on 5/7/2017.
 */
public class BlockWorkStation extends CABlockContainer {

    public BlockWorkStation() {
        super(ModConstants.BlockNames.workStation, Material.WOOD);
        this.setSoundType(SoundType.WOOD).setHardness(2F).setResistance(.5F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityWorkStation();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_WORK_STATION_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityWorkStation) {
            dropItemHandler(world,pos,((TileEntityWorkStation) te).grid,true);
            dropItemHandler(world,pos,((TileEntityWorkStation) te).inv,true);
        }
    }
}
