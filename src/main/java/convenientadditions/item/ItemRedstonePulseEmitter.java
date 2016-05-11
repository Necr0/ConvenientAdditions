package convenientadditions.item;

import java.util.List;
import java.util.Random;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.item.IModelResourceLocationProvider;
import convenientadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRedstonePulseEmitter extends Item implements IModelResourceLocationProvider {
	public ItemRedstonePulseEmitter() {
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.redstonePulseEmitterItemName)
			.setCreativeTab(ConvenientAdditionsMod.CREATIVETAB)
			.setMaxStackSize(1);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	if(!world.isRemote){
    		Random random = new Random();
    		for(int x=0;x<11;x++){
    			for(int y=0;y<11;y++){
    				for(int z=0;z<11;z++){
    					BlockPos pos=new BlockPos(x-5+(int)player.posX,y-5+(int)player.posY,z-5+(int)player.posZ);
    					Block b=world.getBlockState(pos).getBlock();
    					if(b.isAir(world, pos)&&b!=ModBlocks.redstonePulseBlock){
    						world.setBlockState(pos, ModBlocks.redstonePulseBlock.getStateFromMeta(0), 3);
    						world.scheduleBlockUpdate(pos, ModBlocks.redstonePulseBlock, 8, 0);
    					}
            		}
        		}
    		}
    	}
    	player.swingItem();
    	return itemStack;
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(EnumChatFormatting.DARK_GRAY+StatCollector.translateToLocal("tooltip."+ConvenientAdditionsMod.MODID+":"+Reference.redstonePulseEmitterItemName));
	}
}
