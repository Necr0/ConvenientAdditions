package convenientadditions.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityChargeAccumulator;
import convenientadditions.tileentity.TileEntitySunlightCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChargeAccumulator extends BlockContainer {
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

	public BlockChargeAccumulator() {
		super(Material.rock);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.chargeAccumulatorBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.chargeAccumulatorBlockName);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityChargeAccumulator();
	}

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	TileEntity te=blockAccess.getTileEntity(x, y, z);
    	double chargePercentage=0;
    	if(te!=null&&te instanceof TileEntityChargeAccumulator)
    		chargePercentage=((TileEntityChargeAccumulator)te).getChargePercentage();
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
	    this.blockIconTop = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_bottom");
	    this.blockIconBottom = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_bottom");
	}
}
