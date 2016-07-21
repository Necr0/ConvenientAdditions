package convenientadditions.block.itemReceiver;

import conveniencecore.gui.CCGuiContainerBase;
import convenientadditions.ConvenientAdditions;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiItemReceiver extends CCGuiContainerBase {
	
	public TileEntityItemReceiver te;
	
    private static final ResourceLocation itemReceiverGuiTextures = new ResourceLocation(ConvenientAdditions.MODID+":textures/gui/container/itemReceiver.png");
    
	public GuiItemReceiver(ContainerItemReceiver container) {
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
        this.mc.getTextureManager().bindTexture(itemReceiverGuiTextures);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
}
