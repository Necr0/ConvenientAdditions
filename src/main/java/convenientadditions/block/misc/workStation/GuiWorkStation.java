package convenientadditions.block.misc.workStation;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiWorkStation extends CAGuiContainer {

    private static final ImageResourceLocation itemTransmitterGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/workstation.png",0,0,176,172);
    public TileEntityWorkStation te;

    public GuiWorkStation(ContainerWorkStation container) {
        super(container,itemTransmitterGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
