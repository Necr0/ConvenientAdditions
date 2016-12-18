package convenientadditions.api.gui.widget;

import convenientadditions.api.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

public interface IWidgetTooltip extends IWidget {
    <T extends GuiScreen & IGui> boolean hasTooltip(T guiScreen);
    <T extends GuiScreen & IGui> List<String> getTooltip(T guiScreen, float partialTicks, int mouseX, int mouseY);
}
