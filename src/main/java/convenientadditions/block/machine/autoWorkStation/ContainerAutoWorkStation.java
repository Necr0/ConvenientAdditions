package convenientadditions.block.machine.autoWorkStation;

import convenientadditions.api.inventory.InventoryStackHandlerCrafting;
import convenientadditions.base.CAContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAutoWorkStation extends CAContainer {

    public TileEntityAutoWorkStation te;
    public World world;
    public InventoryStackHandlerCrafting craftMatrix;
    public InventoryCraftResult craftResult=new InventoryCraftResult();

    public ContainerAutoWorkStation(TileEntityAutoWorkStation ent, EntityPlayer p) {
        te = ent;
        world = te.getWorld();
        craftMatrix=new InventoryStackHandlerCrafting(this,te.grid,3,3);
        this.addSlotToContainer(new SlotCrafting(p, this.craftMatrix, this.craftResult, 0, 124, 26));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 8 + i * 18));
            }
        }
        //buffer
        for (int k = 0; k < 2; ++k) {
            for (int i1 = 0; i1 < 9; ++i1){
                this.addSlotToContainer(new SlotItemHandler(ent.inv, i1 + k * 9, 8 + i1 * 18, 68 + k * 18));
            }
        }
        //player inventory
        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(p.inventory, i1 + k * 9 + 9, 8 + i1 * 18, 110 + k * 18));
            }
        }
        //hotbar
        for (int l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(p.inventory, l, 8 + l * 18, 168));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
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

            if (fromSlot < 10) {
                if (!this.mergeItemStack(current, 10, 64, false))
                    return ItemStack.EMPTY;
            }else if(fromSlot<28){
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 28, 64, true))
                    return ItemStack.EMPTY;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 10, 28, false))
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

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }

}
