package convenientadditions.api.gui.widget;

import convenientadditions.api.gui.IGui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.util.Rectangle;

public interface IWidget {
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    boolean isVisible();
    default <T extends GuiScreen & IGui> boolean isHovered(T guiScreen, int mouseX, int mouseY){
        return new Rectangle(getX(), getY(), getWidth(), getHeight()).contains(mouseX, mouseY);
    }
}
