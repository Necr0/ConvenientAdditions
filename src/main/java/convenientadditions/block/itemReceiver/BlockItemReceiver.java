package convenientadditions.block.itemReceiver;

import java.util.ArrayList;

import conveniencecore.block.IDismantleable;
import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.init.ModGuiHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockItemReceiver extends BlockContainer implements IModelResourceLocationProvider, IDismantleable {
	public BlockItemReceiver() {
		super(Material.IRON);
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.itemReceiverBlockName).setHardness(4F).setResistance(8F).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemReceiver();
	}

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote)
    		player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_ITEM_RECEIVER_ID, world, pos.getX(), pos.getY(), pos.getZ());
    	return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        dropItems(world, pos);
        super.breakBlock(world, pos, state);
    }
    
    private void dropItems(World world, BlockPos pos)
    {
    	if (world.getTileEntity(pos)!=null && world.getTileEntity(pos) instanceof TileEntityItemReceiver && !world.isRemote){
    		TileEntityItemReceiver p = (TileEntityItemReceiver)world.getTileEntity(pos);
        	for(ItemStack item:p.channels.getStacks()){
        		if(item!=null){
		            float rx = world.rand.nextFloat() * 0.8F + 0.1F;
		            float ry = world.rand.nextFloat() * 0.8F + 0.1F;
		            float rz = world.rand.nextFloat() * 0.8F + 0.1F;
		            EntityItem entityItem = new EntityItem(world, pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz, item);
		            float factor = 0.05F;
		            entityItem.motionX = world.rand.nextGaussian() * factor;
		            entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
		            entityItem.motionZ = world.rand.nextGaussian() * factor;
		            world.spawnEntityInWorld(entityItem);
        		}
	        }
        	p.channels.setStacks(new ItemStack[p.channels.getSlots()]);
        }
    }

	@Override
	public boolean canDismantle(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player,World world, BlockPos pos, boolean returnDrops) {
		dropItems(world,pos);
		ItemStack stack=new ItemStack(this);
		world.spawnEntityInWorld(new EntityItem(world, pos.getX()+.5, pos.getY()+.5, pos.getZ()+.5, stack));
		ArrayList<ItemStack> arr=new ArrayList<ItemStack>();
		arr.add(stack);
		world.setBlockToAir(pos);
		return arr;
	}
}
