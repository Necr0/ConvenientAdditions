package convenientadditions.api.util;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHelper {
    @SideOnly(Side.CLIENT)
    public static RenderItem getRenderItem(){ return FMLClientHandler.instance().getClient().getRenderItem(); }

    @SideOnly(Side.CLIENT)
    public static FontRenderer getFontRenderer(){ return FMLClientHandler.instance().getClient().fontRendererObj; }

    @SideOnly(Side.CLIENT)
    public static void playButtonPressSound(SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
