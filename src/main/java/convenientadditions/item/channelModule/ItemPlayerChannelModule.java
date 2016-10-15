package convenientadditions.item.channelModule;

import java.util.List;

import conveniencecore.api.IMatcher;
import conveniencecore.util.Helper;
import convenientadditions.ModConstants;
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

public class ItemPlayerChannelModule extends ItemChannelModule {
	public ItemPlayerChannelModule() {
		super();
		this.setUnlocalizedName(ModConstants.Mod.MODID+":"+ModConstants.ItemNames.modulePlayerItemName);
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
				item.getTagCompound().setString("MATCHER_PLAYER_ID",player.getUniqueID().toString());
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
			list.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":modulePlayerPrefix","%p",stack.getTagCompound().getString("MATCHER_PLAYER_ID")));
		else
			list.add(TextFormatting.DARK_GRAY+Helper.localize("tooltip."+ModConstants.Mod.MODID+":modulePlayerNotSet"));
		super.addInformation(stack,player,list,par4);
	}
	
}
