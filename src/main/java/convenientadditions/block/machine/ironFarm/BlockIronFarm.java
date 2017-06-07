package convenientadditions.block.machine.ironFarm;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlockMachine;
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

/**
 * Created by Necro on 5/5/2017.
 */
public class BlockIronFarm extends CABlockMachine {
    public BlockIronFarm() {
        super(ModConstants.BlockNames.ironFarm, Material.IRON);
        this.setHardness(4F).setResistance(8F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityIronFarm(getMetaFromState(state));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_IRON_FARM_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityIronFarm) {
            dropItemHandler(world,pos,((TileEntityIronFarm) te).inv,true);
        }
    }
}
