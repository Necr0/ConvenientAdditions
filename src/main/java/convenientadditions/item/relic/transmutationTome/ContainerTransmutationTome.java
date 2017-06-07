package convenientadditions.item.relic.transmutationTome;

import convenientadditions.api.gui.container.IContainerTickable;
import convenientadditions.base.CAContainer;
import convenientadditions.api.capabilities.stackhandler.ItemStackHandlerOutputOnly;
import convenientadditions.api.registry.transmutationTome.TransmutationTomeRecipeHandler;
import convenientadditions.init.ModItems;
import convenientadditions.init.ModNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTransmutationTome extends CAContainer implements IContainerTickable {

    ItemStackHandler handler;
    ItemStackHandler out;
    EntityPlayer player;
    int currentDuration = 0;
    int currentProcess = 0;
    float xpOverride = 1.0f;

    public ContainerTransmutationTome(EntityPlayer p) {
        handler = new ItemStackHandler(2);
        out = new ItemStackHandlerOutputOnly(1);
        player = p;
        //input
        addSlotToContainer(new SlotItemHandler(handler, 0, 38, 36));
        addSlotToContainer(new SlotItemHandler(handler, 1, 61, 36));
        addSlotToContainer(new SlotItemHandler(out, 0, 107, 36));
        //player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(p.inventory, j + i * 9 + 9, 8 + j * 18, 95 + i * 18));
            }
        }
        //player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(p.inventory, k, 8 + k * 18, 153));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.inventory.hasItemStack(new ItemStack(ModItems.itemTransmutationTome));
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 3) {
                if (!this.mergeItemStack(current, 3, 39, true))
                    return ItemStack.EMPTY;
            } else {
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

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        if (player.getEntityWorld().isRemote)
            return;
        InventoryPlayer inventoryplayer = playerIn.inventory;
        if (!inventoryplayer.getItemStack().isEmpty()) {
            playerIn.dropItem(inventoryplayer.getItemStack(), false);
            inventoryplayer.setItemStack(ItemStack.EMPTY);
        }
        if (!handler.getStackInSlot(0).isEmpty()) {
            playerIn.dropItem(handler.getStackInSlot(0), false);
            handler.setStackInSlot(0, ItemStack.EMPTY);
        }
        if (!handler.getStackInSlot(1).isEmpty()) {
            playerIn.dropItem(handler.getStackInSlot(1), false);
            handler.setStackInSlot(1, ItemStack.EMPTY);
        }
        if (!out.getStackInSlot(0).isEmpty()) {
            playerIn.dropItem(out.getStackInSlot(0), false);
            out.setStackInSlot(0, ItemStack.EMPTY);
        }
    }

    public boolean isRecipeValid() {
        return TransmutationTomeRecipeHandler.INSTANCE.doesMatch(handler.getStackInSlot(0), handler.getStackInSlot(1));
    }

    public int getLevelRequired() {
        return TransmutationTomeRecipeHandler.INSTANCE.getLevelRequired(handler.getStackInSlot(0), handler.getStackInSlot(1));
    }

    public ItemStack getResult() {
        return TransmutationTomeRecipeHandler.INSTANCE.getResult(handler.getStackInSlot(0), handler.getStackInSlot(1));
    }

    public Tuple<ItemStack, ItemStack> getLeftovers() {
        return TransmutationTomeRecipeHandler.INSTANCE.getLeftovers(handler.getStackInSlot(0), handler.getStackInSlot(1));
    }

    public int getDuration() {
        return TransmutationTomeRecipeHandler.INSTANCE.getTimeRequired(handler.getStackInSlot(0), handler.getStackInSlot(1));
    }

    @Override
    public void tickContainer(EntityPlayer p, Side side) {
        if (side != Side.SERVER) {
            if (!isWorking())
                setXPOverride(1.0f);
            return;
        }

        if (isWorking()) {
            if (getDuration() != currentDuration)
                currentProcess = 0;
            currentDuration = getDuration();
            if (currentProcess >= currentDuration) {
                //xp
                player.removeExperienceLevel(getLevelRequired());
                //result
                if (getSlot(2).getHasStack())
                    getSlot(2).getStack().grow(getResult().getCount());
                else
                    getSlot(2).putStack(getResult());
                getSlot(2).onSlotChanged();
                //leftovers
                Tuple<ItemStack, ItemStack> leftovers = getLeftovers();
                getSlot(0).putStack(leftovers.getFirst());
                getSlot(1).putStack(leftovers.getSecond());
                //
                detectAndSendChanges();
                currentProcess = 0;
                ModNetworking.INSTANCE.sendTo(
                        new MessageTransmutationTome((byte) 1, 0f),
                        (EntityPlayerMP) player);
            } else {
                ModNetworking.INSTANCE.sendTo(
                        new MessageTransmutationTome((byte) 0, (float) (currentDuration - currentProcess) / currentDuration),
                        (EntityPlayerMP) player);
            }
            currentProcess++;
        } else {
            currentProcess = 0;
        }
    }

    public boolean isWorking() {
        if (isRecipeValid() && player.experienceLevel >= getLevelRequired()) {
            if (canAddItemToSlot(getSlot(2), getResult(), false)) {
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getXPOverride() {
        return xpOverride;
    }

    @SideOnly(Side.CLIENT)
    public void setXPOverride(float f) {
        xpOverride = f;
    }

    @SideOnly(Side.CLIENT)
    public void onOperationFinished() {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiTransmutationTome)
            ((GuiTransmutationTome) Minecraft.getMinecraft().currentScreen).onOperationFinished();
        setXPOverride(1.0f);
    }
}
