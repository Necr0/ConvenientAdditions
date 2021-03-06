package convenientadditions.item.module;

import convenientadditions.api.IMatcher;
import convenientadditions.api.util.Helper;
import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItemChannelModule;
import convenientadditions.item.module.matchers.MatcherPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemPlayerChannelModule extends CAItemChannelModule {
    public ItemPlayerChannelModule() {
        super(ModConstants.ItemNames.modulePlayer);
    }

    @Override
    public boolean hasMatcher(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("MATCHER_PLAYER_ID");
    }

    @Override
    public IMatcher getMatcher(ItemStack stack) {
        return new MatcherPlayer(stack.getTagCompound().getString("MATCHER_PLAYER_ID"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack item=player.getHeldItem(hand);
        if (!world.isRemote) {
            if (!item.hasTagCompound())
                item.setTagCompound(new NBTTagCompound());
            NBTTagCompound t=item.getTagCompound();
            if (!player.isSneaking()) {
                t.setString("MATCHER_PLAYER_ID", player.getUniqueID().toString());
                t.setString("MATCHER_PLAYER_NAME", player.getDisplayNameString());
                player.sendMessage(new TextComponentString(Helper.localize("message."+ModConstants.Mod.MODID+":playerSetTo", player.getDisplayNameString())));
                new ActionResult<>(EnumActionResult.SUCCESS, item);
            }else{
                t.removeTag("MATCHER_PLAYER_ID");
                t.removeTag("MATCHER_PLAYER_NAME");
                player.sendMessage(new TextComponentString(Helper.localize("message."+ModConstants.Mod.MODID+":playerReset")));
                new ActionResult<>(EnumActionResult.SUCCESS, item);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, item);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        if (hasMatcher(stack))
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":modulePlayerPlayer", stack.getTagCompound().hasKey("MATCHER_PLAYER_NAME") ? stack.getTagCompound().getString("MATCHER_PLAYER_NAME") : stack.getTagCompound().getString("MATCHER_PLAYER_ID")));
        else
            list.add(TextFormatting.DARK_GRAY + Helper.localize("tooltip." + ModConstants.Mod.MODID + ":modulePlayerNotSet"));
    }

}
