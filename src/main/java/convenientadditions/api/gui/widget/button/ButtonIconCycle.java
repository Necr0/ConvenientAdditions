package convenientadditions.api.gui.widget.button;

import convenientadditions.api.gui.ImageResourceLocation;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.util.Rectangle;

import net.minecraft.client.Minecraft;

import java.util.Collections;
import java.util.List;

public class ButtonIconCycle extends ButtonIcon {
	public int c_index;
	public ImageResourceLocation[] imgs;
	public String[] tooltips;
	
	public ButtonIconCycle(int buttonId, ImageResourceLocation[] images, int x, int y) {
		super(buttonId, null, x, y);
		this.imgs=images;
	}
	
	public ButtonIconCycle(int buttonId, ImageResourceLocation[] images, String[] tooltips, int x, int y) {
		super(buttonId, null, x, y);
		this.imgs=images;
		if(images.length!=tooltips.length)
			throw new ArrayIndexOutOfBoundsException();
		this.tooltips=tooltips;
	}

	@Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
		this.hovered=new Rectangle(xPosition, yPosition, width, height).contains(mouseX, mouseY);
		drawButtonBackground(mc,mouseX,mouseY);
		mc.renderEngine.bindTexture(imgs[c_index]);
		mc.currentScreen.drawTexturedModalRect(xPosition+1, yPosition+1, imgs[c_index].startX, imgs[c_index].startY, imgs[c_index].sizeX, imgs[c_index].sizeY);
    }
	
	public ButtonIconCycle setCycleIndex(int i){
		i=i%imgs.length;
		this.c_index=i;
		return this;
	}
	
	public int getNextIndex(){
		return (c_index+1)%imgs.length;
	}

	@Override
    public boolean hasTooltip(GuiScreen guiScreen){
		return tooltips!=null&&tooltips[c_index]!=null;
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY){
		return Collections.singletonList(tooltips[c_index]);
    }
}
