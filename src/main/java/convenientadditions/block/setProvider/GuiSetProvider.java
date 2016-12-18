package convenientadditions.block.setProvider;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.button.ButtonIconCycle;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.gui.GuiButton;

public class GuiSetProvider extends CAGuiContainer {

    private static final ImageResourceLocation setProviderGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/setprovider.png",0,0,193,198);
    public TileEntitySetProvider te;

    public GuiSetProvider(ContainerSetProvider container) {
        super(container,setProviderGuiTextures);
        this.te = container.te;
    }

    @Override
    public void initGui() {
        super.initGui();

        String[] ignoutButtonList = new String[]{
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":dontPushIfOutputsNotEmpty"),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":pushEvenIfOutputsNotEmpty")
        };

        String[] dvButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":match", "%v", Helper.localize(ModConstants.Mod.MODID + ":damageValue")),
                Helper.localize(ModConstants.Mod.MODID + ":ignore", "%v", Helper.localize(ModConstants.Mod.MODID + ":damageValue"))
        };

        String[] nbtButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":match", "%v", Helper.localize(ModConstants.Mod.MODID + ":NBT")),
                Helper.localize(ModConstants.Mod.MODID + ":ignore", "%v", Helper.localize(ModConstants.Mod.MODID + ":NBT"))
        };

        String[] filterButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":filterNot", "%v", Helper.localize(ModConstants.Mod.MODID + ":input")),
                Helper.localize(ModConstants.Mod.MODID + ":filter", "%v", Helper.localize(ModConstants.Mod.MODID + ":input"))
        };

        String[] pushButtonList = new String[]{
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":pushOn", "%v", Helper.localize(ModConstants.Mod.MODID + ":pulsers")),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":pushOn", "%v", Helper.localize(ModConstants.Mod.MODID + ":highrs")),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":pushOn", "%v", Helper.localize(ModConstants.Mod.MODID + ":lowrs")),
                Helper.localize("tooltip." + ModConstants.Mod.MODID + ":pushAlways")
        };

        int buttonIndex = 0;

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NOFILTER, ModImageResourceLocations.FILTER}, filterButtonList, guiLeft + 170, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.filteredInput ? 1 : 0));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.DV, ModImageResourceLocations.NODV}, dvButtonList, guiLeft + 170, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.ignoreDV ? 1 : 0));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NBT, ModImageResourceLocations.NONBT}, nbtButtonList, guiLeft + 170, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.ignoreNBT ? 1 : 0));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.PULSERS, ModImageResourceLocations.HIGHRS, ModImageResourceLocations.LOWRS, ModImageResourceLocations.NORS}, pushButtonList, guiLeft + 170, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.pushMode));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NOINV, ModImageResourceLocations.INV}, ignoutButtonList, guiLeft + 170, guiTop + 7 + (buttonIndex++) * 18)
                        .setCycleIndex(te.ignoreOutput ? 1 : 0));
    }


    @Override
    protected void actionPerformed(GuiButton btn) {
        if (btn.id >= 0 && btn.id <= 4) {
            ModNetworking.INSTANCE.sendToServer(new MessageSetProvider(te.getPos(), (byte) btn.id, (byte) ((ButtonIconCycle) btn).getNextIndex()));
            ((ButtonIconCycle) btn).setCycleIndex(((ButtonIconCycle) btn).getNextIndex());
        }
    }
}
