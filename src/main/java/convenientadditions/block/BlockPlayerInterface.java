package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPlayerInterface extends BlockContainer {

	public BlockPlayerInterface() {
		super(Material.iron);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName).setHardness(4F).setResistance(8F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPlayerInterface();
	}
	
    public boolean hasComparatorInputOverride(){return true;}
    
    @Override
    public int getComparatorInputOverride(IBlockState state,World world, BlockPos pos)
    {
    	TileEntity t=world.getTileEntity(pos);
        if(t!=null&&t instanceof TileEntityPlayerInterface)
        	return ((TileEntityPlayerInterface)t).hasTarget()?15:0;
        return 0;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
