package convenientadditions.block.misc.workStation;

import convenientadditions.api.gui.CAGuiContainer;
import convenientadditions.init.ModImageResourceLocations;

public class GuiWorkStation extends CAGuiContainer {
    public GuiWorkStation(ContainerWorkStation container) {
        super(container, ModImageResourceLocations.GUI_WORK_STATION);
    }
}
