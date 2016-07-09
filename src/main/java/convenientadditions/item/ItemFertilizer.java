package convenientadditions.item;

import java.util.List;

import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFertilizer extends Item implements IModelResourceLocationProvider {
	public ItemFertilizer(){
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.fertilizerItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB);
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	boolean ret=false;
		IBlockState state=world.getBlockState(pos);
    	Block b=state.getBlock();
    	if(b instanceof IGrowable){
    		IGrowable grow=(IGrowable)b;
    		if(grow.canGrow(world, pos, state, world.isRemote)){
    			ret=true;
    			if(!world.isRemote)
    				itemStack.stackSize--;
    		}
    		for(int i=0;i<(2+world.rand.nextInt(2));i++){
    			if(grow.canGrow(world, pos, state, world.isRemote) && !world.isRemote && grow.canUseBonemeal(world, world.rand, pos, state))
    					grow.grow(world, world.rand, pos, state);
    		}
    	}
    	if(ret)
    		return EnumActionResult.SUCCESS;
    	return EnumActionResult.FAIL;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		list.add(Helper.localize("tooltip.convenientadditions:fertilizer"));
	}
}
