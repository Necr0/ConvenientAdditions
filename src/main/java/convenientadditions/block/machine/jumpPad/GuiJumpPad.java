package convenientadditions.block.machine.jumpPad;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.label.CenteredLabel;
import convenientadditions.api.util.Helper;

public class GuiJumpPad extends CAGuiContainer {

    private static final ImageResourceLocation jumpPadGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/jumppad.png",0,0,175,114);

    public GuiJumpPad(ContainerJumpPad container) {
        super(container,jumpPadGuiTextures);
    }

    @Override
    public void initGui() {
        super.initGui();
        addWidget(new CenteredLabel(leftX+52,topY+22, Helper.localize("key.jump")));
        addWidget(new CenteredLabel(leftX+124,topY+22, Helper.localize("key.sneak")));
    }
}
