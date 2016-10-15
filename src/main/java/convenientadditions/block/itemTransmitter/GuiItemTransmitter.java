package convenientadditions.block.itemTransmitter;

import conveniencecore.gui.CCGuiContainerBase;
import convenientadditions.ModConstants;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiItemTransmitter extends CCGuiContainerBase {
	
	public TileEntityItemTransmitter te;
	
    private static final ResourceLocation itemTransmitterGuiTextures = new ResourceLocation(ModConstants.Mod.MODID+":textures/gui/container/itemTransmitter.png");
    
	public GuiItemTransmitter(ContainerItemTransmitter container) {
		super(container);
		this.te=container.te;
		this.xSize=174;
		this.ySize=114;
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(itemTransmitterGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
}
