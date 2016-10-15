package convenientadditions.item.tools;

import conveniencecore.api.block.IDismantleable;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemIronWrench extends Item {
	public ItemIronWrench(){
		this.setUnlocalizedName(ModConstants.Mod.MODID+":"+ModConstants.ItemNames.ironWrenchItemName).setCreativeTab(ConvenientAdditions.CREATIVETAB).setMaxStackSize(1);
	}
	
	@Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ,EnumHand hand)
	{
		Block b = world.getBlockState( pos ).getBlock();
		if( b != null )
		{
			if(!player.isSneaking()){
				if(!world.isRemote){
					b.rotateBlock(world, pos, side);
					return EnumActionResult.SUCCESS;
				}
				player.swingArm(hand);
				return EnumActionResult.PASS;
			}else{
				if(b instanceof IDismantleable){
					IDismantleable d=(IDismantleable)b;
					if(d.canDismantle(player, world, pos)&&!world.isRemote){
						d.dismantleBlock(player, world, pos, false);
						return EnumActionResult.SUCCESS;
					}
					player.swingArm(hand);
					return EnumActionResult.PASS;
				}
			}
		}
		return EnumActionResult.FAIL;
	}
}
