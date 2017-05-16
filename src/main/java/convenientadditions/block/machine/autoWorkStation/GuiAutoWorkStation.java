package convenientadditions.block.machine.autoWorkStation;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.button.ButtonIconCycle;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.gui.GuiButton;

public class GuiAutoWorkStation extends CAGuiContainer {

    private static final ImageResourceLocation itemTransmitterGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/autoworkstation.png",0,0,176,190);
    public TileEntityAutoWorkStation te;

    public GuiAutoWorkStation(ContainerAutoWorkStation container) {
        super(container,itemTransmitterGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();

        String[] filterButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":filterNot", Helper.localize(ModConstants.Mod.MODID + ":input")),
                Helper.localize(ModConstants.Mod.MODID + ":filter", Helper.localize(ModConstants.Mod.MODID + ":input"))
        };

        String[] craftButtonList = new String[]{
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":activeOn", Helper.localize(ModConstants.Mod.MODID + ":pulsers")),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":activeOn", Helper.localize(ModConstants.Mod.MODID + ":highrs")),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":activeOn", Helper.localize(ModConstants.Mod.MODID + ":lowrs")),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":activeAlways"),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":activeNever")
        };

        String[] keepButtonList = new String[]{
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":keepItemNot"),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":keepItem")
        };

        int buttonIndex = 0;

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NOFILTER, ModImageResourceLocations.FILTER}, filterButtonList, guiLeft + 151, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.filteredInput ? 1 : 0));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.PULSERS, ModImageResourceLocations.HIGHRS, ModImageResourceLocations.LOWRS, ModImageResourceLocations.NORS, ModImageResourceLocations.FORBID}, craftButtonList, guiLeft + 151, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.craftMode));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NOINV, ModImageResourceLocations.INV}, keepButtonList, guiLeft + 151, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.keepItem ? 1 : 0));
    }


    @Override
    protected void actionPerformed(GuiButton btn) {
        if (btn.id >= 0 && btn.id <= 2) {
            ModNetworking.INSTANCE.sendToServer(new MessageAutoWorkStation(te.getPos(), (byte) btn.id, (byte) ((ButtonIconCycle) btn).getNextIndex()));
            ((ButtonIconCycle) btn).setCycleIndex(((ButtonIconCycle) btn).getNextIndex());
        }
    }
}
