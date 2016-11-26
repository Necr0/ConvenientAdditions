package convenientadditions.api.registry.compost;

import net.minecraft.item.ItemStack;

public class CompostRegistryEntry implements ICompostRegistryEntry {
    public ItemStack stack;
    public int mass = 0;
    public boolean ignoreDamage = true, ignoreNBT = true;

    public CompostRegistryEntry(ItemStack stack, int mass) {
        this.stack = stack;
        this.mass = mass;
    }

    public CompostRegistryEntry(ItemStack stack, int mass, boolean ignoreDamage, boolean ignoreNBT) {
        this.stack = stack;
        this.mass = mass;
        this.ignoreDamage = ignoreDamage;
        this.ignoreNBT = ignoreNBT;
    }

    public boolean doesMatch(ItemStack stack) {
        return (stack.getItem() == this.stack.getItem()) &&
                (ignoreDamage || stack.getItemDamage() == stack.getItemDamage()) &&
                (ignoreNBT || (stack.hasTagCompound() == this.stack.hasTagCompound() &&
                (!stack.hasTagCompound() || stack.getTagCompound().equals(this.stack.getTagCompound()))));
    }

    public int getCompostingMass(ItemStack stack) {
        return this.mass;
    }
}
