package convenientadditions.base;

import convenientadditions.api.gui.widget.ImageButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.client.config.GuiUtils;

public abstract class CCGuiContainerBase extends GuiContainer {
	//public List<IClickable> clickables;
	//public List<ITooltip> tooltips;
	
	public CCGuiContainerBase(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		super.drawScreen(mouseX, mouseY, partialTicks);
        drawTooltips(partialTicks, mouseX, mouseY);
    }
	
	public void drawTooltips(float partialTicks, int mouseX, int mouseY) {
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
