package convenientadditions.block.setProvider;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModGuiHandler;
import convenientadditions.Reference;
import convenientadditions.block.BlockMachineConfigurable;
import convenientadditions.block.setProvider.TileEntitySetProvider.EnumOutletMode;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSetProvider extends BlockMachineConfigurable implements IModelResourceLocationProvider {

	public static final PropertyEnum<EnumOutletMode> OUTLET_TOP = PropertyEnum.<EnumOutletMode>create("outlet_top", EnumOutletMode.class);
	public static final PropertyEnum<EnumOutletMode> OUTLET_BOTTOM = PropertyEnum.<EnumOutletMode>create("outlet_bottom", EnumOutletMode.class);
	public static final PropertyEnum<EnumOutletMode> OUTLET_NORTH = PropertyEnum.<EnumOutletMode>create("outlet_north", EnumOutletMode.class);
	public static final PropertyEnum<EnumOutletMode> OUTLET_EAST = PropertyEnum.<EnumOutletMode>create("outlet_east", EnumOutletMode.class);
	public static final PropertyEnum<EnumOutletMode> OUTLET_SOUTH = PropertyEnum.<EnumOutletMode>create("outlet_south", EnumOutletMode.class);
	public static final PropertyEnum<EnumOutletMode> OUTLET_WEST = PropertyEnum.<EnumOutletMode>create("outlet_west", EnumOutletMode.class);
	
	public BlockSetProvider() {
		super(Material.rock);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.setProviderBlockName).setHardness(4F).setResistance(8F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OUTLET_TOP,EnumOutletMode.disabled).withProperty(OUTLET_BOTTOM,EnumOutletMode.disabled).withProperty(OUTLET_NORTH,EnumOutletMode.disabled).withProperty(OUTLET_EAST,EnumOutletMode.disabled).withProperty(OUTLET_SOUTH,EnumOutletMode.disabled).withProperty(OUTLET_WEST,EnumOutletMode.disabled));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySetProvider();
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote)
    		player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_SET_PROVIDER_ID, world, pos.getX(), pos.getY(), pos.getZ());
    	return true;
    }
    
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    	if(worldIn.isRemote)
    		return;
    	TileEntity t=worldIn.getTileEntity(pos);
		if(t!=null && t instanceof TileEntitySetProvider){
			TileEntitySetProvider te=(TileEntitySetProvider)t;
			te.updateRS( worldIn.isBlockIndirectlyGettingPowered(pos) > 0 );
		}
    }
    
    @Override
    public IBlockState getActualState(IBlockState state,IBlockAccess worldIn,BlockPos pos){
    	TileEntity t = worldIn.getTileEntity(pos);
    	IBlockState ret=state;
        if(t!=null && t instanceof TileEntitySetProvider){
        	TileEntitySetProvider s=(TileEntitySetProvider)t;
        	ret=ret.withProperty(OUTLET_TOP, s.outletSides.get(EnumFacing.UP));
        	ret=ret.withProperty(OUTLET_BOTTOM, s.outletSides.get(EnumFacing.DOWN));
        	ret=ret.withProperty(OUTLET_NORTH, s.outletSides.get(EnumFacing.NORTH));
        	ret=ret.withProperty(OUTLET_EAST, s.outletSides.get(EnumFacing.EAST));
        	ret=ret.withProperty(OUTLET_SOUTH, s.outletSides.get(EnumFacing.SOUTH));
        	ret=ret.withProperty(OUTLET_WEST, s.outletSides.get(EnumFacing.WEST));
        }
        return ret;
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{OUTLET_TOP,OUTLET_BOTTOM,OUTLET_NORTH,OUTLET_EAST,OUTLET_SOUTH,OUTLET_WEST});
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
