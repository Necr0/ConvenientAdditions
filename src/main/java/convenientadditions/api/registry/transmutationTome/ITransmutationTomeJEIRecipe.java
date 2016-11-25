package convenientadditions.api.registry.transmutationTome;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ITransmutationTomeJEIRecipe {
    public List<ItemStack> getBase();

    public List<ItemStack> getTransmutator();

    public List<ItemStack> getResult();

    public int getLevel();
}
