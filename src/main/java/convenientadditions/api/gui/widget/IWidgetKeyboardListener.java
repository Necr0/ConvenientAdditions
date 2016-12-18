package convenientadditions.api.gui.widget;

import convenientadditions.api.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

public interface IWidgetKeyboardListener extends IWidget {
    <T extends GuiScreen & IGui> boolean onKey(T guiScreen, char typedChar, int keyCode);
}
