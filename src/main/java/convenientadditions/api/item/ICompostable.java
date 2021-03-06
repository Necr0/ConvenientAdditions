package convenientadditions.api.item;

import net.minecraft.item.ItemStack;

public interface ICompostable {
    /* For use on Items or ItemBlocks
     * return the amount of composting mass
     * produced by one item
     */
    int getCompostingMass(ItemStack itemStack);

    /* Returns rather the item can be used in the
     * composter or not so you can create items that are
     * only compostable with a specific damage or nbt value
     */
    boolean isCompostable(ItemStack itemStack);

    /* Returns rather the item can be used in the
     * composter to generate spores at random
     */
    boolean hasShroomSpores(ItemStack itemStack);
}
