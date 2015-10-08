package convenientadditions.item;

import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.init.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class ItemCompost extends Item {
	public ItemCompost(){
		super();
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.compostItemName).setTextureName(ConvenientAdditionsMod.MODID+":"+Reference.compostItemName);
	}
	

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int no_idea, float no_clue, float what, float are_these)
    {
    	boolean ret=false;
		Random rnd=new Random();
    	Block b=world.getBlock(x, y, z);
    	if(b instanceof IGrowable){
    		IGrowable grow=(IGrowable)b;
    		if(grow.func_149851_a(world, x, y, z, true)){
    			ret=true;
    			if(!world.isRemote)
    				itemStack.stackSize--;
    		}
    		for(int i=0;i<(3+rnd.nextInt(1));i++){
    			if(grow.func_149851_a(world, x, y, z, true)&&!world.isRemote)
    					grow.func_149853_b(world, rnd, x, y, z);
    		}
    	}else if(b instanceof IPlantable){
			if(!world.isRemote){
				itemStack.stackSize--;
	    		for(int i=0;i<(16+rnd.nextInt(1));i++){
    	    		b.updateTick(world, x, y, z, rnd);
	    		}
			}
    		ret=true;
    	}
    	return ret;
    }
}
