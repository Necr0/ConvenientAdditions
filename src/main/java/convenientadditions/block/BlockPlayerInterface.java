package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityPlayerInterface;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlayerInterface extends BlockContainer {

	public BlockPlayerInterface() {
		super(Material.iron);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName).setHardness(4F).setResistance(8F).setStepSound(soundTypeMetal).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPlayerInterface();
	}
	
    public boolean hasComparatorInputOverride(){return true;}
    
    @Override
    public int getComparatorInputOverride(World world, BlockPos pos)
    {
    	TileEntity t=world.getTileEntity(pos);
        if(t!=null&&t instanceof TileEntityPlayerInterface)
        	return ((TileEntityPlayerInterface)t).hasTarget()?15:0;
        return 0;
    }

}
