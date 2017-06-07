package convenientadditions.block.machine.itemTransmitter;

import convenientadditions.base.block.tileentity.CAContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerItemTransmitter extends CAContainerTileEntity {

    public ContainerItemTransmitter(TileEntityItemTransmitter ent, EntityPlayer p) {
        super(ent);
        //input
        for (int i = 0; i < 3; i++) {
            addSlotToContainer(new SlotItemHandler(ent.channels, i, i * 18 + 62, 8));
        }
        //buffer
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new SlotItemHandler(ent.buffer, k, 8 + k * 18, 30));
        }
        //player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(p.inventory, j + i * 9 + 9, 8 + j * 18, 52 + i * 18));
            }
        }
        //player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(p.inventory, k, 8 + k * 18, 110));
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

            if (fromSlot < 12) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 12, 48, true))
                    return ItemStack.EMPTY;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 12, false))
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
