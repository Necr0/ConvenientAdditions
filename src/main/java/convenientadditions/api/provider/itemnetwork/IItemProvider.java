package convenientadditions.api.provider.itemnetwork;

import conveniencecore.IMatcher;
import net.minecraftforge.items.IItemHandler;

public interface IItemProvider {
	public IItemHandler getItemHandler();
	public IMatcher[] getAccess();
}
