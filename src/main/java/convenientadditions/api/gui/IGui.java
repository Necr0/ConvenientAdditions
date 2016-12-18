package convenientadditions.api.gui;

import convenientadditions.api.gui.widget.IWidget;

import javax.annotation.Nullable;

public interface IGui {
    void messageGui(@Nullable IWidget sender,Object message);
}
