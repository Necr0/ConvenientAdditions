package convenientadditions.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityComposter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockComposter extends BlockContainer {

	public BlockComposter() {
		super(Material.wood);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.composterBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeWood).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.textureNoneBlockName);
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (world.getTileEntity(x, y, z) instanceof TileEntityComposter && !player.isSneaking())
        {
        	TileEntityComposter t=(TileEntityComposter)world.getTileEntity(x, y, z);
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
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = null;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }
}
