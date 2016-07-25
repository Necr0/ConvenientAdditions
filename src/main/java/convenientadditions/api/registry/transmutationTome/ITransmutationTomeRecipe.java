package convenientadditions.api.registry.transmutationTome;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public interface ITransmutationTomeRecipe {
	public ItemStack getResult(ItemStack base,ItemStack transmutator);
	public Tuple<ItemStack,ItemStack> getLeftovers(ItemStack base,ItemStack transmutator);
	public boolean doesMatch(ItemStack base,ItemStack transmutator);
	public int getTimeRequired(ItemStack base,ItemStack transmutator);
	public int getLevelRequired(ItemStack base,ItemStack transmutator);
}
