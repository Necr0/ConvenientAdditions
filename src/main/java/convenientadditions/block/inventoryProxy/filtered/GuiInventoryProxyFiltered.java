package convenientadditions.block.inventoryProxy.filtered;

import conveniencecore.gui.CCGuiContainerBase;
import conveniencecore.gui.widget.ImageCycleButton;
import conveniencecore.gui.widget.ImageResourceLocation;
import conveniencecore.util.Helper;
import convenientadditions.ConvenientAdditions;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiInventoryProxyFiltered extends CCGuiContainerBase {
	
	public TileEntityInventoryProxyFiltered te;
	
    private static final ResourceLocation filteredProxyGuiTextures = new ResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/filteredProxy.png");
    
	public GuiInventoryProxyFiltered(ContainerInventoryProxyFiltered container) {
		super(container);
		this.te=container.te;
		this.xSize=174;
		this.ySize=114;
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

		int buttonIndex=0;
		
		this.buttonList.add(
				new ImageCycleButton(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.DV,ModImageResourceLocations.NODV},dvButtonList,guiLeft+133+(buttonIndex++)*18, guiTop+7)
				.setCycleIndex(te.ignoreDV?1:0)
			);
		this.buttonList.add(
				new ImageCycleButton(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NBT,ModImageResourceLocations.NONBT},nbtButtonList,guiLeft+133+(buttonIndex++)*18, guiTop+7)
				.setCycleIndex(te.ignoreNBT?1:0)
			);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(filteredProxyGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}


	@Override
	protected void actionPerformed( GuiButton btn )
	{
		if(btn.id==0||btn.id==1){
			ModNetworking.INSTANCE.sendToServer(new MessageInventoryProxyFiltered(te.getPos(),(byte)btn.id,(byte)((ImageCycleButton)btn).getNextIndex()));
			((ImageCycleButton)btn).setCycleIndex(((ImageCycleButton)btn).getNextIndex());
		}
	}
}