package convenientadditions.base;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.util.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by Necro on 5/16/2017.
 */
public class CAPotion extends Potion {
    public ImageResourceLocation icon;

    public CAPotion(String name, boolean isBadEffectIn, int liquidColorIn, @Nullable ImageResourceLocation icon) {
        super(isBadEffectIn, liquidColorIn);
        this.setPotionName("potion."+ ModConstants.Mod.MODID+":"+name+".name").setRegistryName(ModConstants.Mod.MODID+":"+name);
        this.icon=icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        if(icon!=null)
            GuiHelper.renderIRL(mc, icon, x+6, y+7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        if(icon!=null)
            GuiHelper.renderIRL(mc, icon, x+3, y+3, alpha);
    }
}
