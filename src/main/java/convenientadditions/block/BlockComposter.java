package convenientadditions.block;

import conveniencecore.item.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityComposter;
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
		super(Material.wood);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.composterBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setStepSound(SoundType.WOOD);
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.getTileEntity(pos) instanceof TileEntityComposter && !player.isSneaking())
        {
        	TileEntityComposter t=(TileEntityComposter)world.getTileEntity(pos);
        	ItemStack c=player.inventory.getStackInSlot(player.inventory.currentItem);
        	if(c!=null){
        		if(t.getContentValue(c)>0)
	        		if(t.isItemValidForSlot(0, c)&&!world.isRemote)
	        			t.setInventorySlotContents(0, c.splitStack(1));
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
