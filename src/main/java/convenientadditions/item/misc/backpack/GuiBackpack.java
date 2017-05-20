package convenientadditions.item.misc.backpack;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiBackpack extends CAGuiContainer {

    private static final ImageResourceLocation backpackGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/backpack.png",0,0,175,114);

    public GuiBackpack(ContainerBackpack container) {
        super(container, backpackGuiTextures);
    }
}
