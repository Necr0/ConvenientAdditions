package convenientadditions.block.itemTransmitter;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiItemTransmitter extends CAGuiContainer {

    private static final ImageResourceLocation itemTransmitterGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/itemtransmitter.png",0,0,175,134);
    public TileEntityItemTransmitter te;

    public GuiItemTransmitter(ContainerItemTransmitter container) {
        super(container,itemTransmitterGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
