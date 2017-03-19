package convenientadditions.block.machine.remoteInventoryProxy;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiRemoteInventoryProxy extends CAGuiContainer {

    private static final ImageResourceLocation remoteInventoryProxyGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/remoteinventoryproxy.png",0,0,175,114);
    public TileEntityRemoteInventoryProxy te;

    public GuiRemoteInventoryProxy(ContainerRemoteInventoryProxy container) {
        super(container,remoteInventoryProxyGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
