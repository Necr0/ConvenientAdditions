package convenientadditions.block.inventoryProxy.filtered;

import convenientadditions.base.CCContainerBase;
import convenientadditions.api.gui.container.SlotFake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInventoryProxyFiltered extends CCContainerBase {

    public TileEntityInventoryProxyFiltered te;

    public ContainerInventoryProxyFiltered(TileEntityInventoryProxyFiltered ent, EntityPlayer p) {
        te = ent;
        //input
        for (int i = 0; i < 3; i++) {
            addSlotToContainer(new SlotFake(ent.filter, i, i * 18 + 62, 8));
        }
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
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }

}
