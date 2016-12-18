package convenientadditions.block.itemReceiver;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiItemReceiver extends CAGuiContainer {

    private static final ImageResourceLocation itemReceiverGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/itemreceiver.png",0,0,174,114);
    public TileEntityItemReceiver te;

    public GuiItemReceiver(ContainerItemReceiver container) {
        super(container,itemReceiverGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
