package convenientadditions.block.machine.jumpPad;

import convenientadditions.base.block.tileentity.CAContainerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerJumpPad extends CAContainerTileEntity {

    public ContainerJumpPad(TileEntityJumpPad ent, EntityPlayer p) {
        super(ent);
        //input
        addSlotToContainer(new SlotItemHandler(ent.location, 0, 44, 8));
        addSlotToContainer(new SlotItemHandler(ent.location, 1, 116, 8));
        //player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(p.inventory, j + i * 9 + 9, 8 + j * 18, 32 + i * 18));
            }
        }
        //player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(p.inventory, k, 8 + k * 18, 90));
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

            if (fromSlot < 2) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 2, 38, true))
                    return ItemStack.EMPTY;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 2, false))
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
