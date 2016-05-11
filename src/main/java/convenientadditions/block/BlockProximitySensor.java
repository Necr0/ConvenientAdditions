package convenientadditions.block;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityProximitySensor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockProximitySensor extends BlockContainer {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconInactive;

	public BlockProximitySensor() {
		super(Material.iron);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.proximitySensorBlockName).setHardness(4F).setResistance(8F).setStepSound(soundTypeMetal).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
    public boolean hasComparatorInputOverride(){return true;}
    
    public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_)
    {
        return world.getBlockMetadata(x, y, z);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
		return meta!=0?blockIcon:blockIconInactive;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+"_top_active");
        this.blockIconInactive = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.playerInterfaceBlockName+"_top_inactive");
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityProximitySensor();
	}
}
