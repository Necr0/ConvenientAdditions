package convenientadditions.block.charge;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.charge.TileEntitySunlightCollector;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSunlightCollector extends BlockMachine {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconTop;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconSide1;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconSide2;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconSide3;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconBottom;

	public BlockSunlightCollector() {
		super(Material.rock);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySunlightCollector();
	}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	TileEntity te=blockAccess.getTileEntity(x, y, z);
    	double chargePercentage=0;
    	if(te!=null&&te instanceof TileEntitySunlightCollector)
    		chargePercentage=((TileEntitySunlightCollector)te).getChargePercentage();
        return side==0?blockIconBottom:
        	side==1?blockIconTop:
        		chargePercentage<20d?blockIcon:
        			chargePercentage<53.3d?blockIconSide1:
            			chargePercentage<86.6d?blockIconSide2:
            				blockIconSide3;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side,int meta)
	{
		return side==0?blockIconBottom:(side==1?blockIconTop:blockIcon);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
	    this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_side_0");
	    this.blockIconSide1 = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_side_1");
	    this.blockIconSide2 = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_side_2");
	    this.blockIconSide3 = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_side_3");
	    this.blockIconTop = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_top");
	    this.blockIconBottom = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_bottom");
	}
}
