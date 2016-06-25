package convenientadditions.block.playerInterface;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlayerInterface extends BlockContainer implements IModelResourceLocationProvider {
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public BlockPlayerInterface() {
		super(Material.IRON);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.playerInterfaceBlockName).setHardness(4F).setResistance(8F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE,false));
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPlayerInterface();
	}
	
	@Override
    public boolean hasComparatorInputOverride(IBlockState state){return true;}
    
    @Override
    public int getComparatorInputOverride(IBlockState state,World world, BlockPos pos)
    {
    	TileEntity t=world.getTileEntity(pos);
        if(t!=null&&t instanceof TileEntityPlayerInterface)
        	return ((TileEntityPlayerInterface)t).hasTarget()?15:0;
        return 0;
    }
    
    @Override
    public IBlockState getActualState(IBlockState state,IBlockAccess worldIn,BlockPos pos){
    	TileEntity t = worldIn.getTileEntity(pos);
        if(t!=null && t instanceof TileEntityPlayerInterface)
        	return state.withProperty(ACTIVE, ((TileEntityPlayerInterface)t).hasTarget());
        return state.withProperty(ACTIVE, false);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{ACTIVE});
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
