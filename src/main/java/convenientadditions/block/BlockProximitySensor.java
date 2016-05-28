package convenientadditions.block;

import conveniencecore.item.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityProximitySensor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockProximitySensor extends BlockContainer implements IModelResourceLocationProvider {
    public static final PropertyInteger STRENGTH = PropertyInteger.create("strength",0,15);

	public BlockProximitySensor() {
		super(Material.iron);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.proximitySensorBlockName).setHardness(4F).setResistance(8F).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STRENGTH, Integer.valueOf(0)));
	}
	
	@Override
    public boolean hasComparatorInputOverride(IBlockState state){return true;}
    
    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
    	TileEntity te=world.getTileEntity(pos);
    	if(te!=null&&te instanceof TileEntityProximitySensor)
    		return ((TileEntityProximitySensor)te).getComp();
        return 0;
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityProximitySensor();
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
    @Override
    public IBlockState getActualState(IBlockState state,IBlockAccess worldIn,BlockPos pos){
    	TileEntity t = worldIn.getTileEntity(pos);
        if(t!=null && t instanceof TileEntityProximitySensor)
        	return state.withProperty(STRENGTH, ((TileEntityProximitySensor)t).getComp());
        return state.withProperty(STRENGTH, 0);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{STRENGTH});
    }
}
