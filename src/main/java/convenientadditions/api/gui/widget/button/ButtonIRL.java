package convenientadditions.api.gui.widget.button;

import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.IWidgetTooltip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ButtonIRL extends GuiButton implements IWidgetTooltip {
    public ImageResourceLocation normal,hovered;
    String tooltip;

    public ButtonIRL(int id,int posX,int posY,ImageResourceLocation normal,@Nullable ImageResourceLocation hovered){
        super(id,posX,posY,"");
        this.normal=normal;
        this.hovered=hovered!=null?hovered:normal;
        this.width=normal.sizeX;
        this.height=normal.sizeY;
    }

    public ButtonIRL(int id,int posX,int posY,ImageResourceLocation normal,@Nullable ImageResourceLocation hovered,String tooltip){
        this(id,posX,posY,normal,hovered);
        this.tooltip=tooltip;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        ImageResourceLocation texture=isHovered(null, mouseX, mouseY)?hovered:normal;
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        mc.currentScreen.drawTexturedModalRect(xPosition, yPosition, texture.startX, texture.startY, texture.sizeX, texture.sizeY);
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen){
        return tooltip!=null;
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY){
        return Collections.singletonList(tooltip);
    }

    @Override
    public int getX() {
        return xPosition;
    }

    @Override
    public int getY() {
        return yPosition;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
