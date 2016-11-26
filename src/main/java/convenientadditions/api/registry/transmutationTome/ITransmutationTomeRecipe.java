package convenientadditions.api.registry.transmutationTome;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public interface ITransmutationTomeRecipe {
    ItemStack getResult(ItemStack base, ItemStack transmutator);

    Tuple<ItemStack, ItemStack> getLeftovers(ItemStack base, ItemStack transmutator);

    boolean doesMatch(ItemStack base, ItemStack transmutator);

    int getTimeRequired(ItemStack base, ItemStack transmutator);

    int getLevelRequired(ItemStack base, ItemStack transmutator);
}
