package convenientadditions.block.composter;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockComposter extends BlockContainer implements IModelResourceLocationProvider {

	public BlockComposter() {
		super(Material.WOOD);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.composterBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
		this.setSoundType(SoundType.WOOD);
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.getTileEntity(pos) instanceof TileEntityComposter && !player.isSneaking())
        {
        	TileEntityComposter t=(TileEntityComposter)world.getTileEntity(pos);
        	if(held!=null){
	        	player.setHeldItem(hand, t.insertStack(held));
        	}
        	return true;
        }
        return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityComposter();
	}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
