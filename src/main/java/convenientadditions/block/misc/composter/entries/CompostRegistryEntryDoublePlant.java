package convenientadditions.block.misc.composter.entries;

import convenientadditions.api.registry.compost.ICompostRegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CompostRegistryEntryDoublePlant implements ICompostRegistryEntry {

    @Override
    public boolean doesMatch(ItemStack stack) {
        return stack.getItem() instanceof ItemBlock && Block.getBlockFromItem(stack.getItem()) instanceof BlockDoublePlant;
    }

    @Override
    public int getCompostingMass(ItemStack stack) {
        return 350;
    }
}
