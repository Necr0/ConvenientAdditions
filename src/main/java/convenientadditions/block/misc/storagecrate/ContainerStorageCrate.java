package convenientadditions.block.misc.storagecrate;

import convenientadditions.base.block.tileentity.CAContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerStorageCrate extends CAContainerTileEntity {

    public ContainerStorageCrate(TileEntityStorageCrate ent, EntityPlayer p) {
        super(ent);
        //te inv
        for (int k = 0; k < 9; ++k) {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new SlotItemHandler(ent.inv, i1 + k * 9, 8 + i1 * 18, 6 + k * 18));
            }
        }
        //player inventory
        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(p.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 174 + k * 18));
            }
        }
        //hotbar
        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(p.inventory, l, 8 + l * 18, 232));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 72) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 72, 108, true))
                    return ItemStack.EMPTY;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 72, false))
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, current);
        }
        return previous;
    }

}
