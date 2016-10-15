package convenientadditions.api.provider.itemnetwork;

import conveniencecore.api.IMatcher;
import net.minecraftforge.items.IItemHandler;

public interface IItemProvider {
	public IItemHandler getItemHandler();
	public boolean hasItemHandler();
	public IMatcher[] getAccess();
}
