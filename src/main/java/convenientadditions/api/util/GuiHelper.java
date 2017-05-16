package convenientadditions.api.util;

import convenientadditions.api.gui.ImageResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHelper {
    @SideOnly(Side.CLIENT)
    public static RenderItem getRenderItem(){ return FMLClientHandler.instance().getClient().getRenderItem(); }

    @SideOnly(Side.CLIENT)
    public static FontRenderer getFontRenderer(){ return FMLClientHandler.instance().getClient().fontRenderer; }

    @SideOnly(Side.CLIENT)
    public static void playButtonPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    @SideOnly(Side.CLIENT)
    public static void renderIRL(Minecraft mc, ImageResourceLocation irl, int x, int y){
        renderIRL(mc,irl,x,y,1.0F);
    }

    @SideOnly(Side.CLIENT)
    public static void renderIRL(Minecraft mc, ImageResourceLocation irl, int x, int y, float alpha){
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        mc.renderEngine.bindTexture(irl);
        if(mc.currentScreen!=null)
            mc.currentScreen.drawTexturedModalRect(x, y, irl.startX, irl.startY, irl.sizeX, irl.sizeY);
        else
            mc.ingameGUI.drawTexturedModalRect(x, y, irl.startX, irl.startY, irl.sizeX, irl.sizeY);
    }
}
