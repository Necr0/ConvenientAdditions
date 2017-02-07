package convenientadditions.block.machine.setProvider;

import convenientadditions.api.gui.container.SlotFakeWithAmount;
import convenientadditions.api.gui.container.SlotOutputOnly;
import convenientadditions.base.CAContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSetProvider extends CAContainer {

    public TileEntitySetProvider te;

    public ContainerSetProvider(TileEntitySetProvider ent, EntityPlayer p) {
        te = ent;
        //input
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new SlotItemHandler(ent.input, i * 9 + j, j * 18 + 8, i * 18 + 8));
            }
        }
        //filter
        for (int j = 0; j < 9; j++) {
            addSlotToContainer(new SlotFakeWithAmount(ent.filter, j, j * 18 + 8, 50));
        }
        //output
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                //System.out.println(i*9+j);
                addSlotToContainer(new SlotOutputOnly(ent.output, i * 9 + j, j * 18 + 8, i * 18 + 74));
            }
        }
        //player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(p.inventory, j + i * 9 + 9, 8 + j * 18, 116 + i * 18));
            }
        }
        //player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(p.inventory, k, 8 + k * 18, 174));
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

            if (fromSlot < 45) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 45, 81, true))
                    return ItemStack.EMPTY;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 18, false))
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
