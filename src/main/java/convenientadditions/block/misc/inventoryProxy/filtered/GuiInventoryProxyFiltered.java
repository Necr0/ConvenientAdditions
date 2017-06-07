package convenientadditions.block.misc.inventoryProxy.filtered;

import convenientadditions.ModConstants;
import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.api.gui.ImageResourceLocation;
import convenientadditions.api.gui.widget.button.ButtonIconCycle;
import convenientadditions.api.util.Helper;
import convenientadditions.init.ModImageResourceLocations;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.gui.GuiButton;

public class GuiInventoryProxyFiltered extends CAGuiContainer {

    private static final ImageResourceLocation filteredProxyGuiTextures = new ImageResourceLocation(ModConstants.Mod.MODID + ":textures/gui/container/filteredproxy.png",0,0,174,114);
    public TileEntityInventoryProxyFiltered te;

    public GuiInventoryProxyFiltered(ContainerInventoryProxyFiltered container) {
        super(container,filteredProxyGuiTextures);
        this.te = (TileEntityInventoryProxyFiltered) container.tile;
    }

    @Override
    public void initGui() {
        super.initGui();

        String[] dvButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":match", Helper.localize(ModConstants.Mod.MODID + ":damageValue")),
                Helper.localize(ModConstants.Mod.MODID + ":ignore", Helper.localize(ModConstants.Mod.MODID + ":damageValue"))
        };

        String[] nbtButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":match", Helper.localize(ModConstants.Mod.MODID + ":NBT")),
                Helper.localize(ModConstants.Mod.MODID + ":ignore", Helper.localize(ModConstants.Mod.MODID + ":NBT"))
        };

        String[] blacklistButtonList = new String[]{
                Helper.localize(ModConstants.Mod.MODID + ":whitelist"),
                Helper.localize(ModConstants.Mod.MODID + ":blacklist")
        };

        int buttonIndex = 0;

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.DV, ModImageResourceLocations.NODV}, dvButtonList, guiLeft + 133 + (buttonIndex++) * 18, guiTop + 7)
                        .setCycleIndex(te.ignoreDV ? 1 : 0));

        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.NBT, ModImageResourceLocations.NONBT}, nbtButtonList, guiLeft + 133 + (buttonIndex++) * 18, guiTop + 7)
                        .setCycleIndex(te.ignoreNBT ? 1 : 0));
        addButton(
                new ButtonIconCycle(buttonIndex, new ImageResourceLocation[]{ModImageResourceLocations.WHITELIST, ModImageResourceLocations.BLACKLIST}, blacklistButtonList, guiLeft + 7, guiTop + 7)
                        .setCycleIndex(te.blacklist ? 1 : 0));
    }


    @Override
    protected void actionPerformed(GuiButton btn) {
        if (btn.id >= 0 || btn.id <= 2) {
            ModNetworking.INSTANCE.sendToServer(new MessageInventoryProxyFiltered(te.getPos(), (byte) btn.id, (byte) ((ButtonIconCycle) btn).getNextIndex()));
            ((ButtonIconCycle) btn).setCycleIndex(((ButtonIconCycle) btn).getNextIndex());
        }
    }
}