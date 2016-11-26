package convenientadditions.api.registry.transmutationTome;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface ITransmutationTomeJEIRecipe {
    NonNullList<ItemStack> getBase();

    NonNullList<ItemStack> getTransmutator();

    NonNullList<ItemStack> getResult();

    int getLevel();
}
