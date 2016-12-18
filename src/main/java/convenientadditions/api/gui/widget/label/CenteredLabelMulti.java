package convenientadditions.api.gui.widget.label;

import convenientadditions.api.gui.widget.IWidgetDrawable;
import convenientadditions.api.util.GuiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public class CenteredLabelMulti implements IWidgetDrawable {
    int posX,posY,color;
    List<String> texts;

    public CenteredLabelMulti(int posX, int posY, List<String> texts){
        this.texts=texts;
        this.color=0xFFFFFF;
        this.posX=posX;
        this.posY=posY;
    }

    public CenteredLabelMulti(int posX, int posY, List<String> texts, int color ){
        this.texts=texts;
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
        int i= (int) ((Minecraft.getSystemTime()/1000)%texts.size());
        guiScreen.drawCenteredString(GuiHelper.getFontRenderer(),texts.get(i),posX,posY,color);
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
        return GuiHelper.getFontRenderer().getStringWidth(texts.get(0));
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
