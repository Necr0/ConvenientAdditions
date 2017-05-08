package convenientadditions.item.module.color;

import convenientadditions.api.IMatcher;
import convenientadditions.api.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.ModConstants;
import convenientadditions.base.item.CAItemChannelModule;
import convenientadditions.handler.ModGuiHandler;
import convenientadditions.item.module.matchers.MatcherEnumDyeColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemColorChannelModule extends CAItemChannelModule {

    public static ItemStack DEFAULT_STACK;

    public ItemColorChannelModule() {
        super(ModConstants.ItemNames.moduleColor);
        ItemStack tmp_stack = new ItemStack(this);
        NBTTagCompound tmp_nbt = new NBTTagCompound();
        tmp_nbt.setInteger("MATCHER_DYE_0", 15);
        tmp_nbt.setInteger("MATCHER_DYE_1", 15);
        tmp_nbt.setInteger("MATCHER_DYE_2", 15);
        tmp_stack.setTagCompound(tmp_nbt);
        ItemColorChannelModule.DEFAULT_STACK = tmp_stack;
    }

    @Override
    public boolean hasMatcher(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey("MATCHER_DYE_0") && stack.getTagCompound().hasKey("MATCHER_DYE_1") && stack.getTagCompound().hasKey("MATCHER_DYE_2");
    }

    @Override
    public IMatcher getMatcher(ItemStack stack) {
        return new MatcherEnumDyeColor(getDyeColors(stack)[0], getDyeColors(stack)[1], getDyeColors(stack)[2]);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        if (hasMatcher(stack)) {
            list.add(Helper.localize("tooltip." + ModConstants.Mod.MODID + ":moduleColorsPrefix",
                    Helper.localize("color." + ModConstants.Mod.MODID + ":" + getDyeColors(stack)[0].getUnlocalizedName()),
                    Helper.localize("color." + ModConstants.Mod.MODID + ":" + getDyeColors(stack)[1].getUnlocalizedName()),
                    Helper.localize("color." + ModConstants.Mod.MODID + ":" + getDyeColors(stack)[2].getUnlocalizedName())));
        }
    }

    public EnumDyeColor[] getDyeColors(ItemStack stack) {
        return new EnumDyeColor[]{
                EnumDyeColor.byDyeDamage(stack.getTagCompound().getInteger("MATCHER_DYE_0")),
                EnumDyeColor.byDyeDamage(stack.getTagCompound().getInteger("MATCHER_DYE_1")),
                EnumDyeColor.byDyeDamage(stack.getTagCompound().getInteger("MATCHER_DYE_2"))};
    }

    public int[] getColorInts(ItemStack stack) {
        return new int[]{
                stack.getTagCompound().getInteger("MATCHER_DYE_0"),
                stack.getTagCompound().getInteger("MATCHER_DYE_1"),
                stack.getTagCompound().getInteger("MATCHER_DYE_2")};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs c, NonNullList<ItemStack> l) {
        l.add(ItemColorChannelModule.DEFAULT_STACK.copy());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            player.openGui(ConvenientAdditions.INSTANCE, ModGuiHandler.GUI_COLOR_MODULE_ID, world, hand.ordinal(), 0, 0);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

}
