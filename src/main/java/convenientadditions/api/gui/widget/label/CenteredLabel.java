package convenientadditions.api.gui.widget.label;

import convenientadditions.api.gui.widget.IWidgetDrawable;
import convenientadditions.api.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;

public class CenteredLabel implements IWidgetDrawable {
    int posX,posY,color;
    String text;

    public CenteredLabel(int posX, int posY, String text ){
        this.text=text;
        this.color=0xFFFFFF;
        this.posX=posX;
        this.posY=posY;
    }

    public CenteredLabel(int posX, int posY, String text,int color ){
        this.text=text;
        this.color=color;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return true;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        guiScreen.drawCenteredString(GuiHelper.getFontRenderer(),text,posX,posY,color);
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getWidth() {
        return GuiHelper.getFontRenderer().getStringWidth(text);
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
