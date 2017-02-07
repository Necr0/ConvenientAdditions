package convenientadditions.block.machine.jumpPad;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;

public class GuiJumpPad extends CAGuiContainer {

    private static final ImageResourceLocation jumpPadGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/jumppad.png",0,0,175,114);
    public TileEntityJumpPad te;

    public GuiJumpPad(ContainerJumpPad container) {
        super(container,jumpPadGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
