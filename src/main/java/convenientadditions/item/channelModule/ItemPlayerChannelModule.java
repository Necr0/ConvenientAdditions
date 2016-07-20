package convenientadditions.item.channelModule;

import java.util.List;

import conveniencecore.IMatcher;
import conveniencecore.item.resourceprovider.IModelResourceLocationProvider;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.Reference;
import convenientadditions.api.item.ItemChannelModule;
import convenientadditions.item.channelModule.matchers.MatcherPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemPlayerChannelModule extends ItemChannelModule implements IModelResourceLocationProvider {
	public ItemPlayerChannelModule() {
		super();
		this.setUnlocalizedName(ConvenientAdditions.MODID+":"+Reference.modulePlayerItemName);
	}

	@Override
	public boolean hasMatcher(ItemStack stack) {
		return stack.hasTagCompound()&&stack.getTagCompound().hasKey("MATCHER_PLAYER_ID");
	}

	@Override
	public IMatcher getMatcher(ItemStack stack) {
		return new MatcherPlayer(stack.getTagCompound().getString("MATCHER_PLAYER_ID"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand)
	{
		if(!world.isRemote){
			if(!item.hasTagCompound())
				item.setTagCompound(new NBTTagCompound());
			if(!item.getTagCompound().hasKey("MATCHER_PLAYER_ID")){
				item.getTagCompound().setString("MATCHER_PLAYER_ID",player.getName());
				player.addChatMessage(new TextComponentString("Successfully set player!"));
				new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,item);
	}
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4)
	{
		if(hasMatcher(stack))
			list.add(Helper.localize("tooltip."+ConvenientAdditions.MODID+":modulePlayerPrefix","%p",stack.getTagCompound().getString("MATCHER_PLAYER_ID")));
		else
			list.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ConvenientAdditions.MODID+":modulePlayerNotSet"));
		super.addInformation(stack,player,list,par4);
	}
	
}
