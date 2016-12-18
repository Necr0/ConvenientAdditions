package convenientadditions.api.gui.widget;

import convenientadditions.api.gui.IGui;
import convenientadditions.api.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class TextBox extends GuiTextField implements IWidgetDrawable,IWidgetClickListener,IWidgetKeyboardListener {

    public TextBox(int id, int posX, int posY, int width, int height) {
        super(id,GuiHelper.getFontRenderer(),posX,posY,width,height);
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return isVisible();
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        drawTextBox();
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
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public void onClick(GuiScreen guiScreen, int mouseX, int mouseY, int mouseButton) {
        mouseClicked(mouseX,mouseY,mouseButton);
    }

    @Override
    public <T extends GuiScreen & IGui> boolean onKey(T guiScreen, char typedChar, int keyCode) {
        String text=getText();
        boolean flag=textboxKeyTyped(typedChar,keyCode);
        if(!getText().equals(text))
            guiScreen.messageGui(this,getText());
        return flag;
    }
}
