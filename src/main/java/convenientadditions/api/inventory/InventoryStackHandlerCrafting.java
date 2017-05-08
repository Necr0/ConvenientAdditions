package convenientadditions.api.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandlerModifiable;

/**
 * Created by Necro on 5/7/2017.
 */
public class InventoryStackHandlerCrafting extends InventoryCrafting {
    /** the width of the crafting inventory */
    /** Class containing the callbacks for the events on_GUIClosed and on_CraftMaxtrixChanged. */
    private final Container eventHandler;
    public final IItemHandlerModifiable itemHandler;

    public InventoryStackHandlerCrafting(Container eventHandlerIn, IItemHandlerModifiable handler, int width, int height)
    {
        super(eventHandlerIn, width, height);
        this.eventHandler = eventHandlerIn;
        this.itemHandler = handler;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.itemHandler.getSlots();
    }

    public boolean isEmpty()
    {
        for (int i=0;i<this.itemHandler.getSlots();i++)
        {
            if (!itemHandler.getStackInSlot(i).isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return itemHandler.getStackInSlot(index);
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getDisplayName()
    {
        return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        return itemHandler.extractItem(index,64,false);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = itemHandler.extractItem(index,count,false);
        if (!itemstack.isEmpty())
        {
            this.eventHandler.onCraftMatrixChanged(this);
        }
        return itemstack;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        itemHandler.setStackInSlot(index,stack);
        this.eventHandler.onCraftMatrixChanged(this);
    }

    public void clear()
    {
        for (int i=0;i<this.itemHandler.getSlots();i++)
        {
            itemHandler.setStackInSlot(0,ItemStack.EMPTY);
        }
    }
}
