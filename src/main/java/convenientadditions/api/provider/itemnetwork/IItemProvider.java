package convenientadditions.api.provider.itemnetwork;

import net.minecraftforge.items.IItemHandler;

public interface IItemProvider {
	public IItemHandler getItemHandler();
	public boolean hasItemHandler();
	public IMatcher[] getAccess();
}
