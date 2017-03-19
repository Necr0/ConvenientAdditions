package convenientadditions.block.machine.setProvider;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.block.CABlockMachineConfigurable;
import convenientadditions.base.item.EnumItemCategory;
import convenientadditions.block.machine.setProvider.TileEntitySetProvider.EnumOutletMode;
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

public class BlockSetProvider extends CABlockMachineConfigurable {

    public static final PropertyEnum<EnumOutletMode> OUTLET_TOP = PropertyEnum.<EnumOutletMode>create("outlet_top", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_BOTTOM = PropertyEnum.<EnumOutletMode>create("outlet_bottom", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_NORTH = PropertyEnum.<EnumOutletMode>create("outlet_north", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_EAST = PropertyEnum.<EnumOutletMode>create("outlet_east", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_SOUTH = PropertyEnum.<EnumOutletMode>create("outlet_south", EnumOutletMode.class);
    public static final PropertyEnum<EnumOutletMode> OUTLET_WEST = PropertyEnum.<EnumOutletMode>create("outlet_west", EnumOutletMode.class);

    public BlockSetProvider() {
        super(ModConstants.BlockNames.setProvider,Material.IRON);
        this.setHardness(4F).setResistance(8F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OUTLET_TOP, EnumOutletMode.disabled).withProperty(OUTLET_BOTTOM, EnumOutletMode.disabled).withProperty(OUTLET_NORTH, EnumOutletMode.disabled).withProperty(OUTLET_EAST, EnumOutletMode.disabled).withProperty(OUTLET_SOUTH, EnumOutletMode.disabled).withProperty(OUTLET_WEST, EnumOutletMode.disabled));
        this.setCategory(EnumItemCategory.MACHINE).setDefaultAdditionalInfo(true);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntitySetProvider();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote)
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_SET_PROVIDER_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos from) {
        TileEntity t = worldIn.getTileEntity(pos);
        if (t == null || worldIn.isRemote)
            return;
        if (t instanceof TileEntitySetProvider) {
            TileEntitySetProvider te = (TileEntitySetProvider) t;
            te.updateRS(te.getWorld().isBlockIndirectlyGettingPowered(pos) > 0);
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity t = worldIn.getTileEntity(pos);
        IBlockState ret = state;
        if (t != null && t instanceof TileEntitySetProvider) {
            TileEntitySetProvider s = (TileEntitySetProvider) t;
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
    public void dropItems(World world, BlockPos pos) {
        TileEntity te=world.getTileEntity(pos);
        if (!world.isRemote && te != null && te instanceof TileEntitySetProvider) {
            dropItemHandler(world,pos,((TileEntitySetProvider) te).input,true);
            dropItemHandler(world,pos,((TileEntitySetProvider) te).output,true);
        }
    }
}
