package convenientadditions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Helper;
import convenientadditions.init.Reference;
import convenientadditions.tileentity.TileEntityPowderKeg;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPowderKeg extends BlockContainer {
	@SideOnly(Side.CLIENT)
	public IIcon blockIconTop;
	@SideOnly(Side.CLIENT)
	public IIcon blockIconBottom;

	public BlockPowderKeg() {
		super(Material.wood);
		this.setBlockName(ConvenientAdditionsMod.MODID+":"+Reference.powderKegBlockName).setHardness(2F).setResistance(3F).setStepSound(soundTypeWood).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPowderKeg();
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
	    this.blockIcon = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.powderKegBlockName+"_side");
	    this.blockIconTop = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.powderKegBlockName+"_top");
	    this.blockIconBottom = iconRegister.registerIcon(ConvenientAdditionsMod.MODID+":"+Reference.powderKegBlockName+"_bottom");
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

		ItemStack current=player.inventory.getStackInSlot(player.inventory.currentItem);
    	if (world.getTileEntity(x, y, z) instanceof TileEntityPowderKeg){
        	TileEntityPowderKeg keg = (TileEntityPowderKeg)world.getTileEntity(x, y, z);
    		if (!player.isSneaking()){
	        	if(current==null){
	        		if(world.isRemote)
	        			if(keg.getStackInSlot(0)!=null)	
	        				player.addChatMessage(new ChatComponentText(keg.getStackInSlot(0).stackSize+StatCollector.translateToLocal("message."+ConvenientAdditionsMod.MODID+":gunpowderStored")));
	        			else
	        				player.addChatMessage(new ChatComponentText("0"+StatCollector.translateToLocal("message."+ConvenientAdditionsMod.MODID+":gunpowderStored")));
	        	}else if(!keg.isItemValidForSlot(0, current)){
	        		if(current.getItem()==Items.flint_and_steel){
	        			current.damageItem(1, player);
	        			if(explode(world,x,y,z))
	        				return true;
	        		}
	    			return false;
	        	}else if (!world.isRemote){
	        		if(keg.getStackInSlot(0)==null){
	            		keg.setInventorySlotContents(0, current.splitStack(current.stackSize));     		
	            	}else{
	            		int space=64-keg.getStackInSlot(0).stackSize;
	            		int stack=current.stackSize-space;
	            		if(stack>=0){
	            			keg.setInventorySlotContents(0,new ItemStack(Items.gunpowder,64));
	            			current.splitStack(space);
	            		}else{
	            			keg.setInventorySlotContents(0,new ItemStack(Items.gunpowder,64+stack));
	            			current.splitStack(current.stackSize);
	            		}
	            			
	        		}
		        }
        	}else if(keg.getStackInSlot(0)!=null&&current==null&&!world.isRemote){
	        	Helper.spawnItemInPlace(world, x+.5, y+1.2, z+.5, keg.getStackInSlot(0));
	    		keg.setInventorySlotContents(0, null);
            }
    	}
        return true;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
    {
        if(Helper.checkForFire(world, x, y, z))
        	this.explode(world,x,y,z);
        else if (world.isBlockIndirectlyGettingPowered(x, y, z))
        	this.explode(world,x,y,z);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e)
    {
        if (e instanceof EntityArrow && !w.isRemote)
        {
            EntityArrow entityarrow = (EntityArrow)e;

            if (entityarrow.isBurning())
	            this.explode(w, x, y, z);
        }
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion p_5){explode(world,x,y,z);}
    
    public boolean explode(World w,int x,int y,int z){
    	if(w.getTileEntity(x, y, z)!=null&&w.getTileEntity(x, y, z) instanceof TileEntityPowderKeg){
    		TileEntityPowderKeg k=(TileEntityPowderKeg)w.getTileEntity(x, y, z);
    		if(k.getStackInSlot(0)==null)
    			return false;
    		if(!w.isRemote){
	    		float strenght=(float)k.getStackInSlot(0).stackSize/1.5F;
	    		k.setInventorySlotContents(0, null);
	    		w.setBlockToAir(x, y, z);
	    		w.createExplosion(null, (double)x+.5, (double)y+.5, (double)z+.5, strenght, true);
	    	}
    		return true;
    	}
    	return false;
    }
    
    public boolean canDropFromExplosion(Explosion e){return false;}
}
