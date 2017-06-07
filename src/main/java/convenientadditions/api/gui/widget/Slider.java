package convenientadditions.api.gui.widget;

import convenientadditions.api.gui.IGui;
import convenientadditions.api.util.GuiHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;

/**
 * This class is blatantly stolen from iChunUtils with permission.
 *
 * @author iChun
 */
public class Slider implements IWidgetDrawable
{
    public double minValue = 0.0D;
    public double maxValue = 5.0D;
    public double value = 1.0F;
    public double stepSize = 1D;
    public boolean visible = true;

    public String prefix = "",suffix = "";

    public int xPosition,yPosition,width,height;

    public Slider(int xPosition, int yPosition, int width, int height, double minValue, double maxValue, double value, double stepSize) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
        this.stepSize = stepSize;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    public void updateSlider(float position)
    {
        this.value=minValue+(position*(maxValue-minValue));
        this.value=minValue+Math.round((value-minValue)/stepSize)*stepSize;
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

    @Override
    public <T extends GuiScreen & IGui> boolean isDrawable(T guiScreen) {
        return true;
    }

    @Override
    public <T extends GuiScreen & IGui> void draw(T guiScreen, float partialTicks, int mouseX, int mouseY) {
        if(Mouse.isButtonDown(0) && isHovered(guiScreen,mouseX,mouseY)){
            if(new Rectangle(getX(), getY()+1, getWidth(), getHeight()-2).contains(mouseX, mouseY))
                updateSlider(getRelativeXPosition(mouseX));
        }

        drawColoredRect(getX(),getY(),getWidth(),getHeight(),0xaaaaaa);
        drawColoredRect(getX()+1,getY()+1,getWidth()-2,getHeight()-2,0x000000);
        drawColoredRect(getX()+1,getY()+1, (int) ((getWidth()-2)*(value-minValue)/(maxValue-minValue)),getHeight()-2,0x555555);

        guiScreen.drawCenteredString(GuiHelper.getFontRenderer(), prefix+value+suffix, getX()+getWidth()/2, getY()+getHeight()/2-4, 0xffffff);
    }

    public float getRelativeXPosition(int mouseX){
        return Math.min(Math.max((mouseX-getX()+1)/((float)getWidth()-2),0f),1f);
    }

    public void drawColoredRect(int startX, int startY, int width, int height, int color) {
        this.drawGradientRect(startX, startY, startX+width-1, startY+height-1, 0xFF000000 + color, 0xFF000000 + color);
    }

    public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor)
    {
        float f = (float)(startColor >> 24 & 255) / 255.0F;
        float f1 = (float)(startColor >> 16 & 255) / 255.0F;
        float f2 = (float)(startColor >> 8 & 255) / 255.0F;
        float f3 = (float)(startColor & 255) / 255.0F;
        float f4 = (float)(endColor >> 24 & 255) / 255.0F;
        float f5 = (float)(endColor >> 16 & 255) / 255.0F;
        float f6 = (float)(endColor >> 8 & 255) / 255.0F;
        float f7 = (float)(endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double)right, (double)top, 0).color(f1, f2, f3, f).endVertex();
        vertexbuffer.pos((double)left, (double)top, 0).color(f1, f2, f3, f).endVertex();
        vertexbuffer.pos((double)left, (double)bottom, 0).color(f5, f6, f7, f4).endVertex();
        vertexbuffer.pos((double)right, (double)bottom, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public Slider setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Slider setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }
}