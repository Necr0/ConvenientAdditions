package convenientadditions.api.provider.itemnetwork;

import convenientadditions.api.IMatcher;
import net.minecraftforge.items.IItemHandler;

public interface IItemProvider {
    IItemHandler getItemHandler();

    boolean hasItemHandler();

    IMatcher[] getAccess();
}
