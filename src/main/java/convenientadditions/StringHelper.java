package convenientadditions;

import convenientadditions.api.util.Helper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StringHelper {
    @SideOnly(Side.CLIENT)
    public static String getJoke(ItemStack stack){
        return TextFormatting.YELLOW+Helper.localize("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+"Joke");
    }

    @SideOnly(Side.CLIENT)
    public static String getInfo(ItemStack stack){
        return Helper.localize("tooltip." + stack.getItem().getUnlocalizedName().split("\\.")[1]+"Info");
    }

    @SideOnly(Side.CLIENT)
    public static String getHint(String loc, String... rep){
        return TextFormatting.DARK_GRAY+Helper.localize(loc,rep);
    }
}
