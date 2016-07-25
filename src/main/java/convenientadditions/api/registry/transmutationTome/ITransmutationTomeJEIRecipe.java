package convenientadditions.api.registry.transmutationTome;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ITransmutationTomeJEIRecipe {
	public List<ItemStack> getBase();
	public List<ItemStack> getTransmutator();
	public List<ItemStack> getResult();
	public int getLevel();
}
