package convenientadditions.block.setProvider;

import java.util.ArrayList;
import java.util.List;

import conveniencecore.gui.widget.ImageButton;
import conveniencecore.gui.widget.ImageCycleButton;
import conveniencecore.gui.widget.ImageResourceLocation;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import scala.actors.threadpool.Arrays;

public class GuiSetProvider extends GuiContainer {
	
	public TileEntitySetProvider te;
	
    private static final ResourceLocation setProviderGuiTextures = new ResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/setProvider.png");
    
	public GuiSetProvider(ContainerSetProvider container) {
		super(container);
		this.te=container.te;
		this.xSize=193;
		this.ySize=198;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		String[] dvButtonList=new String[]{
				Helper.localize(ConvenientAdditions.MODID+":match","%v",Helper.localize(ConvenientAdditions.MODID+":damageValue")),
				Helper.localize(ConvenientAdditions.MODID+":ignore","%v",Helper.localize(ConvenientAdditions.MODID+":damageValue"))
		};

		String[] nbtButtonList=new String[]{
				Helper.localize(ConvenientAdditions.MODID+":match","%v",Helper.localize(ConvenientAdditions.MODID+":NBT")),
				Helper.localize(ConvenientAdditions.MODID+":ignore","%v",Helper.localize(ConvenientAdditions.MODID+":NBT"))
		};
		
		String[] rsButtonList=new String[]{
				Helper.localize("tooltip."+ConvenientAdditions.MODID+":resetOn","%v",Helper.localize(ConvenientAdditions.MODID+":highrs")),
				Helper.localize("tooltip."+ConvenientAdditions.MODID+":resetOn","%v",Helper.localize(ConvenientAdditions.MODID+":lowrs")),
				Helper.localize("tooltip."+ConvenientAdditions.MODID+":resetOn","%v",Helper.localize(ConvenientAdditions.MODID+":pulsers")),
				Helper.localize("tooltip."+ConvenientAdditions.MODID+":resetNot"),
				Helper.localize("tooltip."+ConvenientAdditions.MODID+":resetAuto")
		};
		
		this.buttonList.add(
				new ImageButton(0,ModImageResourceLocations.RESET,I18n.format("tooltip."+ConvenientAdditions.MODID+":reset"),guiLeft+170, guiTop+7));
		this.buttonList.add(
			new ImageCycleButton(1, new ImageResourceLocation[]{ModImageResourceLocations.DV,ModImageResourceLocations.NODV},dvButtonList,guiLeft+170, guiTop+25)
			.setCycleIndex(te.ignoreDV?1:0)
		);
		this.buttonList.add(
			new ImageCycleButton(2, new ImageResourceLocation[]{ModImageResourceLocations.NBT,ModImageResourceLocations.NONBT},nbtButtonList,guiLeft+170,guiTop+43)
			.setCycleIndex(te.ignoreNBT?1:0)
		);
		this.buttonList.add(
			new ImageCycleButton(3, new ImageResourceLocation[]{ModImageResourceLocations.HIGHRS,ModImageResourceLocations.LOWRS,ModImageResourceLocations.PULSERS,ModImageResourceLocations.NORS,ModImageResourceLocations.AUTO}, rsButtonList, guiLeft+170, guiTop+25+18+18)
			.setCycleIndex(te.resetMode)
		);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(setProviderGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        
        
        for(GuiButton btn:buttonList){
        	if(btn instanceof ImageButton){
    			ImageButton ibtn=(ImageButton)btn;
        		if(ibtn.visible&&ibtn.hasTooltip()&&ibtn.isMouseOver()){
                    GuiUtils.drawHoveringText(java.util.Arrays.asList(new String[]{ibtn.getTooltip()}), mouseX, mouseY, width, height, -1, fontRendererObj);
        		}
        	}
        }
	}


	@Override
	protected void actionPerformed( GuiButton btn )
	{
		if(btn.id==0)
			ModNetworking.INSTANCE.sendToServer(new MessageSetProvider(te.getPos(),(byte)0,(byte)0));
		else if(btn.id>=1&&btn.id<=3){
			ModNetworking.INSTANCE.sendToServer(new MessageSetProvider(te.getPos(),(byte)btn.id,(byte)((ImageCycleButton)btn).getNextIndex()));
			((ImageCycleButton)btn).setCycleIndex(((ImageCycleButton)btn).getNextIndex());
		}
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		super.drawScreen(mouseX, mouseY, partialTicks);
        
        for(GuiButton btn:buttonList){
        	if(btn instanceof ImageButton){
    			ImageButton ibtn=(ImageButton)btn;
        		if(ibtn.visible&&ibtn.hasTooltip()&&ibtn.isMouseOver()){
                    GuiUtils.drawHoveringText(java.util.Arrays.asList(new String[]{ibtn.getTooltip()}), mouseX, mouseY, width, height, -1, fontRendererObj);
        		}
        	}
        }
		
    }
}
