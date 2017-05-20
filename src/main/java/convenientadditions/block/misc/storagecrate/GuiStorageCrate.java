package convenientadditions.block.misc.storagecrate;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiStorageCrate extends CAGuiContainer {

    private static final ImageResourceLocation storageCrateGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/storagecrate.png",0,0,175,240);
    public TileEntityStorageCrate te;

    public GuiStorageCrate(ContainerStorageCrate container) {
        super(container,storageCrateGuiTextures);
        this.te = container.te;
    }
}
