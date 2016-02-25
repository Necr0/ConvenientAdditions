package convenientadditions.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.block.BlockConfigurable;
import convenientadditions.tileentity.TileEntityChargeAccumulator;
import convenientadditions.tileentity.TileEntitySeedBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSeedBox extends BlockConfigurable {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconOutlet;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconSide1;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconSide2;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconSide3;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconBottom;

	public BlockSeedBox() {
		super(Material.wood);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.seedBoxBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeStone).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySeedBox();
	}

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	TileEntity te=blockAccess.getTileEntity(x, y, z);
    	if(te!=null&&te instanceof TileEntitySeedBox){
    		if(((TileEntitySeedBox)te).isOutput(ForgeDirection.getOrientation(side)))
    			return blockIconOutlet;
    	}
        return (side==1||side==0)?blockIconBottom:blockIcon;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side,int meta)
	{
		return (side==0||side==1)?blockIconBottom:blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
	    this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_side_0");
	    this.blockIconBottom = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.sunlightCollectorBlockName+"_bottom");
	    this.blockIconOutlet = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.chargeAccumulatorBlockName+"_outlet");
	}
}
