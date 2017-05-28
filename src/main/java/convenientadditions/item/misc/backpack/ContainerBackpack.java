package convenientadditions.item.misc.backpack;

import convenientadditions.base.CAContainer;
import convenientadditions.config.ModConfigMisc;
import convenientadditions.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;

public class ContainerBackpack extends CAContainer {
    EntityPlayer player;
    ItemStack backpack;

    public ContainerBackpack(EntityPlayer p) {
        backpack=p.getHeldItem(p.getHeldItemMainhand().getItem()==ModItems.itemBackpack?EnumHand.MAIN_HAND:EnumHand.OFF_HAND);
        if(backpack.getItem()!=ModItems.itemBackpack||!backpack.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP)){
            p.closeScreen();
            return;
        }
        player = p;
        IItemHandler handler=backpack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new SlotItemHandler(handler, j + i * 9, 8 + j * 18, 8 + i * 18));
            }
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
    public ItemStack slotClick(int index, int button, ClickType mode, EntityPlayer player) {
        if(!canInteractWith(player)||
                (index>0&&index<inventorySlots.size()&&(getSlot(index).getStack().getItem() instanceof ItemBackpack||Arrays.asList(ModConfigMisc.backpack_blacklist).contains(getSlot(index).getStack().getItem().getRegistryName())))||
                (mode==ClickType.SWAP&&(player.inventory.getStackInSlot(button).getItem() instanceof ItemBackpack||Arrays.asList(ModConfigMisc.backpack_blacklist).contains(player.inventory.getStackInSlot(button).getItem().getRegistryName())))){
            player.closeScreen();
            return ItemStack.EMPTY;
        }
        return super.slotClick(index, button, mode, player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if(playerIn.world.isRemote)
            return true;
        if(backpack.isEmpty())
            return false;
        if(playerIn.inventory.offHandInventory.get(0)==backpack)
            return true;
        for(ItemStack s:playerIn.inventory.mainInventory){
            if(s==backpack)
                return true;
        }
        return false;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 9) {
                if (!this.mergeItemStack(current, 9, 45, true))
                    return ItemStack.EMPTY;
            } else {
                if (!this.mergeItemStack(current, 0, 9, false))
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
