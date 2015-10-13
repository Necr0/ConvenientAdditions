package convenientadditions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Helper;
import convenientadditions.init.Reference;
import convenientadditions.tileentity.TileEntityCub3dFrame;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCub3dFrame extends BlockContainer {

	public BlockCub3dFrame() {
		super(Material.glass);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.frameBlockName).setHardness(.5F).setResistance(4F).setStepSound(soundTypeGlass).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
		.setBlockTextureName(ConvenientAdditionsMod.MODID+":"+Reference.textureNoneBlockName);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityCub3dFrame();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = null;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityCub3dFrame)
        {
        	TileEntityCub3dFrame frame = (TileEntityCub3dFrame)world.getTileEntity(x, y, z);
            if (!player.isSneaking()){
            	if(frame.getStackInSlot(0)==null){
            		ItemStack current=player.inventory.getStackInSlot(player.inventory.currentItem);
            		if(current!=null){
	            		frame.setInventorySlotContents(0, current.splitStack(1));
            		}
            	}
            }else{
            	if(frame.getStackInSlot(0)!=null){
            		Helper.spawnItemInPlace(world, x+.5, y+1.2, z+.5, frame.getStackInSlot(0));
            		frame.setInventorySlotContents(0, null);
            	}
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z)
    {
        Random rand = new Random();
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
        {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0)
            {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
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
