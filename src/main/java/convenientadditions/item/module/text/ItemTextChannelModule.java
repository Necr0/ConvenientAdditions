package convenientadditions.item.module.text;

import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.api.IMatcher;
import convenientadditions.api.util.Helper;
import convenientadditions.base.item.CAItemChannelModule;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.item.module.matchers.MatcherText;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Necro on 5/18/2017.
 */
public class ItemTextChannelModule extends CAItemChannelModule {

    public ItemTextChannelModule() {
        super(ModConstants.ItemNames.moduleText);
    }

    @Override
    public boolean hasMatcher(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("MATCHER_TEXT");
    }

    @Override
    public IMatcher getMatcher(ItemStack stack) {
        return new MatcherText(stack.getTagCompound().getString("MATCHER_TEXT"));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        if (hasMatcher(stack)) {
            list.add(Helper.localize("tooltip."+ModConstants.Mod.MODID+":"+ModConstants.ItemNames.moduleText+".text",stack.getTagCompound().getString("MATCHER_TEXT")));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_TEXT_MODULE_ID, world, hand.ordinal(), 0, 0);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

}
