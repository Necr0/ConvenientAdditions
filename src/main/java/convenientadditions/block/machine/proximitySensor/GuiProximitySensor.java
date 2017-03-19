package convenientadditions.block.machine.proximitySensor;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiScreen;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.Slider;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.io.IOException;

/**
 * Created by Necro on 3/12/2017.
 */
public class GuiProximitySensor extends CAGuiScreen {
    private static final ImageResourceLocation sensorGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/proximitysensor.png",0,0,168,34);

    public TileEntityProximitySensor te;
    public Slider range;

    public GuiProximitySensor(TileEntityProximitySensor sensor) {
        super(sensorGuiTextures);
        te=sensor;
    }

    @Override
    public void initGui(){
        super.initGui();
        range=addWidget(new Slider(leftX+9,topY+9,120,16,.5d, 15d, te.range, .5d).setPrefix(Helper.localize(ModConstants.Mod.MODID+":range")+": ").setSuffix(" "+Helper.localize(ModConstants.Mod.MODID+":unit.blocks")));
        addButton(new GuiButtonExt(0,leftX+129,topY+9, 30, 15, Helper.localize(ModConstants.Mod.MODID+":done")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        ModNetworking.INSTANCE.sendToServer(new MessageProximitySensor(te.getPos(),range.value));
        Minecraft.getMinecraft().player.closeScreen();
    }
}
