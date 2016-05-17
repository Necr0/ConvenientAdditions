package convenientadditions.item.tools;

import convenientadditions.ConvenientAdditionsMod;
import convenientadditions.Reference;
import convenientadditions.api.block.IDismantleable;
import convenientadditions.api.item.IModelResourceLocationProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTitaniumWrench extends Item implements IModelResourceLocationProvider {
	public ItemTitaniumWrench(){
		this.setUnlocalizedName(ConvenientAdditionsMod.MODID+":"+Reference.titaniumWrenchItemName).setCreativeTab(ConvenientAdditionsMod.CREATIVETAB);
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack is, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		boolean ret=false;
		Block b = world.getBlockState( pos ).getBlock();
		if( b != null )
		{
			if(!player.isSneaking()){
				if(!world.isRemote)
					b.rotateBlock(world, pos, side);
				player.swingItem();
			}else{
				if(b instanceof IDismantleable){
					IDismantleable d=(IDismantleable)b;
					if(d.canDismantle(player, world, pos)&&!world.isRemote){
						d.dismantleBlock(player, world, pos, false);
					}
					player.swingItem();
				}
			}
		}
		return ret;
	}
}
