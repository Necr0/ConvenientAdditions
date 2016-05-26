package convenientadditions.block;

import java.util.Random;

import conveniencecore.item.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.tileentity.TileEntityPowderKeg;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPowderKeg extends BlockContainer implements IModelResourceLocationProvider {
	
	public BlockPowderKeg() {
		super(Material.wood);
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.powderKegBlockName).setHardness(2F).setResistance(3F).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
		this.setStepSound(SoundType.WOOD);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPowderKeg();
	}

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }

    private void dropItems(World world, BlockPos pos)
    {
        Random rand = new Random();
        TileEntity tileEntity = world.getTileEntity(pos);

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
                EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		ItemStack current=player.inventory.getStackInSlot(player.inventory.currentItem);
    	if (world.getTileEntity(pos) instanceof TileEntityPowderKeg){
        	TileEntityPowderKeg keg = (TileEntityPowderKeg)world.getTileEntity(pos);
    		if (!player.isSneaking()){
	        	if(current==null){
	        		if(world.isRemote)
	        			if(keg.getStackInSlot(0)!=null)	
	        				player.addChatMessage(new TextComponentString(keg.getStackInSlot(0).stackSize+I18n.translateToLocal("message."+ConvenientAdditionsMod.MODID+":gunpowderStored")));
	        			else
	        				player.addChatMessage(new TextComponentString("0"+I18n.translateToLocal("message."+ConvenientAdditionsMod.MODID+":gunpowderStored")));
	        	}else if(!keg.isItemValidForSlot(0, current)){
	        		if(current.getItem()==Items.flint_and_steel){
	        			current.damageItem(1, player);
	        			if(explode(world,pos))
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
	        	Helper.spawnItemInPlace(world, pos.getX()+.5, pos.getY()+1.2, pos.getZ()+.5, keg.getStackInSlot(0));
	    		keg.setInventorySlotContents(0, null);
            }
    	}
        return true;
    }
    
    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbour)
    {
    	if(world instanceof World)
	        if(Helper.checkForFire(world, pos)||((World)world).isBlockIndirectlyGettingPowered(pos)>0)
	        	this.explode((World)world,pos);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World w, BlockPos pos, Entity e)
    {
        if (e instanceof EntityArrow && !w.isRemote)
        {
            EntityArrow entityarrow = (EntityArrow)e;

            if (entityarrow.isBurning())
	            this.explode(w, pos);
        }
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion p_5){explode(world,pos);}
    
    public boolean explode(World w,BlockPos pos){
    	if(w.getTileEntity(pos)!=null&&w.getTileEntity(pos) instanceof TileEntityPowderKeg){
    		TileEntityPowderKeg k=(TileEntityPowderKeg)w.getTileEntity(pos);
    		if(k.getStackInSlot(0)==null)
    			return false;
    		if(!w.isRemote){
	    		float strenght=(float)k.getStackInSlot(0).stackSize/1.5F;
	    		k.setInventorySlotContents(0, null);
	    		w.setBlockToAir(pos);
	    		w.createExplosion(null, (double)pos.getX()+.5, (double)pos.getY()+.5, (double)pos.getZ()+.5, strenght, true);
	    	}
    		return true;
    	}
    	return false;
    }
    
    public boolean canDropFromExplosion(Explosion e){return false;}

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
