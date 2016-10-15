package convenientadditions.block.gateway;

import net.minecraft.client.gui.GuiScreen;

public class GuiGateway extends GuiScreen {
	TileEntityGateway te;
	
	public GuiGateway(TileEntityGateway ent) {
		te=ent;
	}

}
