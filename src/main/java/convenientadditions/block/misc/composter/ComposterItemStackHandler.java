package convenientadditions.block.misc.composter;

import convenientadditions.api.item.ICompostable;
import convenientadditions.config.ModConfigMisc;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ComposterItemStackHandler implements IItemHandler, IItemHandlerModifiable {
    TileEntityComposter composter;

    public ComposterItemStackHandler(TileEntityComposter composterIn) {
        composter = composterIn;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        int simulate_content = composter.getContentValue(stack);
        stack = stack.copy();
        while (!stack.isEmpty()) {
            if (!(composter.content < ModConfigMisc.composter_capacity && composter.getContentValue(stack) > 0) || (simulate && simulate_content > ModConfigMisc.composter_capacity))
                return stack;
            if (!simulate) {
                composter.content += composter.getContentValue(stack);
                composter.processing = (composter.content >= ModConfigMisc.composter_progressContent);
                if (stack.getItem() instanceof ICompostable && ((ICompostable) stack.getItem()).hasShroomSpores(stack))
                    composter.spores = true;
                else if (stack.getItem() == Items.MUSHROOM_STEW || stack.getItem() == ItemBlock.getItemFromBlock(Blocks.RED_MUSHROOM) || stack.getItem() == ItemBlock.getItemFromBlock(Blocks.BROWN_MUSHROOM))
                    composter.spores = true;
                composter.markDirty();
                IBlockState state = composter.getWorld().getBlockState(composter.getPos());
                composter.getWorld().notifyBlockUpdate(composter.getPos(), state, state, 2);
            } else {
                simulate_content += composter.getContentValue(stack);
            }
            stack.shrink(1);
        }
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        insertItem(slot, stack, false);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

}
