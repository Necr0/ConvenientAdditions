package conveniencecore.gui.widget;

import java.util.List;

import org.lwjgl.util.Rectangle;

import conveniencecore.ConvenienceCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ImageButton extends GuiButton {
	public static final ImageResourceLocation BUTTON_IDLE=new ImageResourceLocation(ConvenienceCore.MOD_ID+":textures/gui/imageButtons.png", 0, 0, 18, 18);
	public static final ImageResourceLocation BUTTON_HOVER=new ImageResourceLocation(ConvenienceCore.MOD_ID+":textures/gui/imageButtons.png", 0, 18, 18, 18);
	
	public ImageResourceLocation img;
	String tooltip;

	public ImageButton(int buttonId, ImageResourceLocation image, int x, int y) {
		super(buttonId, x, y, 16, 16, "");
		this.img=image;
	}

	public ImageButton(int buttonId, ImageResourceLocation image,String tooltip , int x, int y) {
		super(buttonId, x, y, 16, 16, "");
		this.img=image;
		this.tooltip=tooltip;
	}
	
	@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
		this.hovered=new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
		drawButtonBackground(mc,mouseX,mouseY);
		mc.renderEngine.bindTexture(img);
		mc.currentScreen.drawTexturedModalRect(xPosition+1, yPosition+1, img.startX, img.startY, img.sizeX, img.sizeY);
    }
	
    public void drawButtonBackground(Minecraft mc, int mouseX, int mouseY)
    {
    	ImageResourceLocation background;
    	if(isMouseOver())
    		background=BUTTON_HOVER;
    	else
    		background=BUTTON_IDLE;
		mc.renderEngine.bindTexture(background);
		mc.currentScreen.drawTexturedModalRect(xPosition, yPosition, background.startX, background.startY, background.sizeX, background.sizeY);
    }
    
    public boolean hasTooltip(){
		return tooltip!=null;
    }
    
    public String getTooltip(){
		return tooltip;
    }
}
