package convenientadditions.item;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFertilizer extends Item implements IModelResourceLocationProvider {
	public ItemFertilizer(){
		super();
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.fertilizerItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
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
    		for(int i=0;i<(2+world.rand.nextInt(1));i++){
    			if(grow.canGrow(world, pos, state, world.isRemote) && !world.isRemote && grow.canUseBonemeal(world, world.rand, pos, state))
    					grow.grow(world, world.rand, pos, state);
    		}
    	}
    	return ret;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("tooltip.convenientadditions:fertilizer"));
	}
}
