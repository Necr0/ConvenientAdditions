package convenientadditions.api.gui;

import convenientadditions.api.gui.widget.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.client.config.GuiUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CAGuiContainer extends GuiContainer implements IGui {
	public List<IWidget> widgetList;
	public ImageResourceLocation background;
	public int leftX,topY;
	
	public CAGuiContainer(Container inventorySlotsIn,ImageResourceLocation bg) {
		super(inventorySlotsIn);
		widgetList=new ArrayList<>();
		background=bg;
	}

	@Override
	public void initGui(){
		this.xSize = background.sizeX;
		this.ySize = background.sizeY;
		super.initGui();
		this.leftX = (this.width - background.sizeX) / 2;
		this.topY = (this.height - background.sizeY) / 2;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawDrawables(partialTicks, mouseX, mouseY);
		drawTooltips(partialTicks, mouseX, mouseY);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		drawBackgroundTexture(partialTicks, mouseX, mouseY);
	}

	public void drawBackgroundTexture(float partialTicks, int mouseX, int mouseY){
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(background);
		this.drawTexturedModalRect(leftX, topY, background.startX, background.startY, background.sizeX, background.sizeY);
	}

	public void drawDrawables(float partialTicks, int mouseX, int mouseY) {
		for(IWidget widget:widgetList){
			if(widget instanceof IWidgetDrawable){
				if(widget.isVisible()&&((IWidgetDrawable) widget).isDrawable(this)){
					((IWidgetDrawable) widget).draw(this, partialTicks, mouseX, mouseY);
				}
			}
		}
	}

	public void drawTooltips(float partialTicks, int mouseX, int mouseY) {
		for(IWidget widget:widgetList){
			if(widget instanceof IWidgetTooltip){
				if(widget.isVisible()&&((IWidgetTooltip) widget).hasTooltip(this)&&widget.isHovered(this,mouseX,mouseY)){
					GuiUtils.drawHoveringText(((IWidgetTooltip)widget).getTooltip(this,partialTicks,mouseX,mouseY), mouseX, mouseY, width, height, -1, fontRendererObj);
				}
			}
		}
	}

	@Override
	public <T extends GuiButton> T addButton(T btn){
		if(btn instanceof IWidget&&!widgetList.contains(btn))
			widgetList.add((IWidget) btn);
		return super.addButton(btn);
	}

	public <T extends GuiLabel> T addLabel(T label){
		if(label instanceof IWidget&&!widgetList.contains(label))
			widgetList.add((IWidget) label);
		labelList.add(label);
		return label;
	}

	public <T extends IWidget> T addWidget(T widget){
		widgetList.add(widget);
		return widget;
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height)
	{
		this.mc = mc;
		this.itemRender = mc.getRenderItem();
		this.fontRendererObj = mc.fontRendererObj;
		this.width = width;
		this.height = height;
		setupGui();
	}

	public void setupGui(){
		if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Pre(this, this.buttonList)))
		{
			cleanGui();
			this.initGui();
		}
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post(this, this.buttonList));
	}

	public void cleanGui(){
		this.buttonList.clear();
		this.labelList.clear();
		this.widgetList.clear();
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX,mouseY,mouseButton);
		for(IWidget widget:widgetList){
			if(widget instanceof IWidgetClickListener){
				((IWidgetClickListener) widget).onClick(this,mouseX,mouseY,mouseButton);
			}
			if(widget instanceof IWidgetClickable){
				if(((IWidgetClickable) widget).isClickable(this)&&widget.isHovered(this,mouseX,mouseY)){
					((IWidgetClickable) widget).onClicked(this,mouseX,mouseY,mouseButton);
				}
			}
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) throws IOException
	{
		boolean flag=false;
		for(IWidget widget:widgetList){
			if(widget instanceof IWidgetKeyboardListener){
				if(((IWidgetKeyboardListener) widget).onKey(this,typedChar,keyCode))
					flag=true;
			}
		}
		if(!flag)
			super.keyTyped(typedChar,keyCode);
	}

	@Override
	public void messageGui(@Nullable IWidget sender, Object message){}
}
