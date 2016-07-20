package convenientadditions.block.inventoryProxy;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInventoryProxy extends BlockContainer implements IModelResourceLocationProvider {
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	
	public BlockInventoryProxy() {
		super(Material.WOOD);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.inventoryProxyBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
		this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityInventoryProxy(false);
	}
	
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer).getOpposite());
    }
	
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer).getOpposite()), 2);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
    //BLOCKSTATE
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}
