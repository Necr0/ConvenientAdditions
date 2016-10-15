package convenientadditions.block.setProvider;

import conveniencecore.gui.CCGuiContainerBase;
import conveniencecore.gui.widget.ImageButton;
import conveniencecore.gui.widget.ImageCycleButton;
import conveniencecore.gui.widget.ImageResourceLocation;
import conveniencecore.util.Helper;
import convenientadditions.ModConstants;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiSetProvider extends CCGuiContainerBase {
	
	public TileEntitySetProvider te;
	
    private static final ResourceLocation setProviderGuiTextures = new ResourceLocation(ModConstants.Mod.MODID+":textures/gui/container/setProvider.png");
    
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
				Helper.localize(ModConstants.Mod.MODID+":match","%v",Helper.localize(ModConstants.Mod.MODID+":damageValue")),
				Helper.localize(ModConstants.Mod.MODID+":ignore","%v",Helper.localize(ModConstants.Mod.MODID+":damageValue"))
		};

		String[] nbtButtonList=new String[]{
				Helper.localize(ModConstants.Mod.MODID+":match","%v",Helper.localize(ModConstants.Mod.MODID+":NBT")),
				Helper.localize(ModConstants.Mod.MODID+":ignore","%v",Helper.localize(ModConstants.Mod.MODID+":NBT"))
		};
		
		String[] filterButtonList=new String[]{
				Helper.localize(ModConstants.Mod.MODID+":filterNot","%v",Helper.localize(ModConstants.Mod.MODID+":input")),
				Helper.localize(ModConstants.Mod.MODID+":filter","%v",Helper.localize(ModConstants.Mod.MODID+":input"))
		};
		
		String[] rsButtonList=new String[]{
				Helper.localize("tooltip."+ModConstants.Mod.MODID+":resetOn","%v",Helper.localize(ModConstants.Mod.MODID+":highrs")),
				Helper.localize("tooltip."+ModConstants.Mod.MODID+":resetOn","%v",Helper.localize(ModConstants.Mod.MODID+":lowrs")),
				Helper.localize("tooltip."+ModConstants.Mod.MODID+":resetOn","%v",Helper.localize(ModConstants.Mod.MODID+":pulsers")),
				Helper.localize("tooltip."+ModConstants.Mod.MODID+":resetNot"),
				Helper.localize("tooltip."+ModConstants.Mod.MODID+":resetAuto")
		};
		int buttonIndex=0;
		this.buttonList.add(
				new ImageCycleButton(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NOFILTER,ModImageResourceLocations.FILTER}, filterButtonList, guiLeft+170, guiTop+7+(buttonIndex++)*18)
				.setCycleIndex(te.filteredInput?1:0)
			);
		this.buttonList.add(
				new ImageButton(buttonIndex,ModImageResourceLocations.RESET,I18n.format("tooltip."+ModConstants.Mod.MODID+":reset"),guiLeft+170, guiTop+7+(buttonIndex++)*18));
		this.buttonList.add(
			new ImageCycleButton(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.DV,ModImageResourceLocations.NODV},dvButtonList,guiLeft+170, guiTop+7+(buttonIndex++)*18)
			.setCycleIndex(te.ignoreDV?1:0)
		);
		this.buttonList.add(
			new ImageCycleButton(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NBT,ModImageResourceLocations.NONBT},nbtButtonList,guiLeft+170, guiTop+7+(buttonIndex++)*18)
			.setCycleIndex(te.ignoreNBT?1:0)
		);
		this.buttonList.add(
			new ImageCycleButton(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.HIGHRS,ModImageResourceLocations.LOWRS,ModImageResourceLocations.PULSERS,ModImageResourceLocations.NORS,ModImageResourceLocations.AUTO}, rsButtonList, guiLeft+170, guiTop+7+(buttonIndex++)*18)
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
	}


	@Override
	protected void actionPerformed( GuiButton btn )
	{
		if(btn.id==1)
			ModNetworking.INSTANCE.sendToServer(new MessageSetProvider(te.getPos(),(byte)btn.id,(byte)0));
		else if(btn.id==0||btn.id>=2&&btn.id<=4){
			ModNetworking.INSTANCE.sendToServer(new MessageSetProvider(te.getPos(),(byte)btn.id,(byte)((ImageCycleButton)btn).getNextIndex()));
			((ImageCycleButton)btn).setCycleIndex(((ImageCycleButton)btn).getNextIndex());
		}
	}
}
