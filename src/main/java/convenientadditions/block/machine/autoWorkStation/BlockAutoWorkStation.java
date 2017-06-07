package convenientadditions.block.machine.autoWorkStation;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.block.block.CABlockMachineConfigurable;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.block.machine.setProvider.TileEntitySetProvider;
import convenientadditions.handler.ModGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Necro on 5/7/2017.
 */
public class BlockAutoWorkStation extends CABlockMachineConfigurable {

    public static final PropertyEnum<TileEntitySetProvider.EnumOutletMode> OUTLET_TOP = PropertyEnum.<TileEntitySetProvider.EnumOutletMode>create("outlet_top", TileEntitySetProvider.EnumOutletMode.class);
    public static final PropertyEnum<TileEntitySetProvider.EnumOutletMode> OUTLET_BOTTOM = PropertyEnum.<TileEntitySetProvider.EnumOutletMode>create("outlet_bottom", TileEntitySetProvider.EnumOutletMode.class);
    public static final PropertyEnum<TileEntitySetProvider.EnumOutletMode> OUTLET_NORTH = PropertyEnum.<TileEntitySetProvider.EnumOutletMode>create("outlet_north", TileEntitySetProvider.EnumOutletMode.class);
    public static final PropertyEnum<TileEntitySetProvider.EnumOutletMode> OUTLET_EAST = PropertyEnum.<TileEntitySetProvider.EnumOutletMode>create("outlet_east", TileEntitySetProvider.EnumOutletMode.class);
    public static final PropertyEnum<TileEntitySetProvider.EnumOutletMode> OUTLET_SOUTH = PropertyEnum.<TileEntitySetProvider.EnumOutletMode>create("outlet_south", TileEntitySetProvider.EnumOutletMode.class);
    public static final PropertyEnum<TileEntitySetProvider.EnumOutletMode> OUTLET_WEST = PropertyEnum.<TileEntitySetProvider.EnumOutletMode>create("outlet_west", TileEntitySetProvider.EnumOutletMode.class);

    public BlockAutoWorkStation() {
        super(ModConstants.BlockNames.autoWorkStation, Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OUTLET_TOP, TileEntitySetProvider.EnumOutletMode.disabled).withProperty(OUTLET_BOTTOM, TileEntitySetProvider.EnumOutletMode.disabled).withProperty(OUTLET_NORTH, TileEntitySetProvider.EnumOutletMode.disabled).withProperty(OUTLET_EAST, TileEntitySetProvider.EnumOutletMode.disabled).withProperty(OUTLET_SOUTH, TileEntitySetProvider.EnumOutletMode.disabled).withProperty(OUTLET_WEST, TileEntitySetProvider.EnumOutletMode.disabled));
        this.setDefaultAdditionalInfo(true).setCategory(EnumItemCategory.MACHINE).setHardness(2F).setResistance(.5F);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityAutoWorkStation();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_AUTO_WORK_STATION_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntityAutoWorkStation) {
            dropItemHandler(world,pos,((TileEntityAutoWorkStation) te).grid,true);
            dropItemHandler(world,pos,((TileEntityAutoWorkStation) te).inv,true);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity t = worldIn.getTileEntity(pos);
        IBlockState ret = state;
        if (t != null && t instanceof TileEntityAutoWorkStation) {
            TileEntityAutoWorkStation s = (TileEntityAutoWorkStation) t;
            ret = ret.withProperty(OUTLET_TOP, s.outletSides.get(EnumFacing.UP));
            ret = ret.withProperty(OUTLET_BOTTOM, s.outletSides.get(EnumFacing.DOWN));
            ret = ret.withProperty(OUTLET_NORTH, s.outletSides.get(EnumFacing.NORTH));
            ret = ret.withProperty(OUTLET_EAST, s.outletSides.get(EnumFacing.EAST));
            ret = ret.withProperty(OUTLET_SOUTH, s.outletSides.get(EnumFacing.SOUTH));
            ret = ret.withProperty(OUTLET_WEST, s.outletSides.get(EnumFacing.WEST));
        }
        return ret;
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, OUTLET_TOP, OUTLET_BOTTOM, OUTLET_NORTH, OUTLET_EAST, OUTLET_SOUTH, OUTLET_WEST);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos from) {
        TileEntity t = worldIn.getTileEntity(pos);
        if (t == null || worldIn.isRemote)
            return;
        if (t instanceof TileEntityAutoWorkStation) {
            TileEntityAutoWorkStation te = (TileEntityAutoWorkStation) t;
            te.updateRS(te.getWorld().isBlockIndirectlyGettingPowered(pos) > 0);
        }
    }
}
