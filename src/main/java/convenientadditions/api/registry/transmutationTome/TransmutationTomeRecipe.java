package convenientadditions.api.registry.transmutationTome;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Tuple;

public class TransmutationTomeRecipe implements ITransmutationTomeRecipe, ITransmutationTomeJEIRecipe {
    ItemStack base, transmutator, result;
    int time, level;

    public TransmutationTomeRecipe(ItemStack base, ItemStack transmutator, ItemStack result, int time, int level) {
        this.base = base;
        this.transmutator = transmutator;
        this.result = result;
        this.time = time;
        this.level = level;
    }

    @Override
    public ItemStack getResult(ItemStack base, ItemStack transmutator) {
        return result;
    }

    @Override
    public Tuple<ItemStack, ItemStack> getLeftovers(ItemStack base, ItemStack transmutator) {
        ItemStack outputBase = null;
        if (!base.isEmpty()) {
            outputBase = base.copy();
            if (outputBase.getCount() - this.base.getCount() < 1) {
                if (outputBase.getItem().hasContainerItem(outputBase))
                    outputBase = outputBase.getItem().getContainerItem(outputBase);
                else
                    outputBase = null;
            } else
                outputBase.shrink(this.base.getCount());
        }
        ItemStack outputTransmutator = null;
        if (transmutator != null) {
            outputTransmutator = transmutator.copy();
            if (outputTransmutator.getCount() - 1 < 1) {
                if (outputTransmutator.getItem().hasContainerItem(outputTransmutator))
                    outputTransmutator = outputTransmutator.getItem().getContainerItem(outputTransmutator);
                else
                    outputTransmutator = null;
            } else
                outputTransmutator.shrink(1);
        }
        return new Tuple<>(outputBase, outputTransmutator);
    }

    @Override
    public boolean doesMatch(ItemStack base, ItemStack transmutator) {
        if (base.isEmpty() || transmutator.isEmpty())
            return false;
        return base.isItemEqual(this.base) && base.getCount() >= this.base.getCount() && transmutator.isItemEqual(this.transmutator) && level >= this.level;
    }

    @Override
    public int getTimeRequired(ItemStack base, ItemStack transmutator) {
        return time;
    }

    @Override
    public int getLevelRequired(ItemStack base, ItemStack transmutator) {
        return level;
    }

    @Override
    public NonNullList<ItemStack> getBase() {
        return NonNullList.withSize(1,this.base);
    }

    @Override
    public NonNullList<ItemStack> getTransmutator() {
        return NonNullList.withSize(1,this.transmutator);
    }

    @Override
    public NonNullList<ItemStack> getResult() {
        return NonNullList.withSize(1,this.result);
    }

    @Override
    public int getLevel() {
        return this.level;
    }

}
