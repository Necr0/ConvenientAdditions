package convenientadditions.api.inventory;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SlotNotation{
    //IMPORTANT!!!: THIS IS NOT THE ONLY POSSIBLE INSTANCE OF THE GROUND SLOT!!!
    public static final SlotNotation SLOT_GROUND=new SlotNotation(null, EnumInventory.GROUND, EnumSubInventory.NONE, 0);

    private EntityPlayer player;
    private EnumInventory inventory;
    private EnumSubInventory subInventory;
    private int slot;

    public SlotNotation(EntityPlayer player, EnumInventory inventory, EnumSubInventory subInventory, int slot){
        this.player=player;
        this.inventory=inventory;
        this.subInventory=subInventory;
        this.slot=slot;
    }

    public ItemStack getItem(){
        switch(inventory){
            case MAIN:
                switch (subInventory){
                    case NONE:
                        return player.inventory.mainInventory.get(slot+9);
                    case ARMOR:
                        return player.inventory.armorInventory.get(slot);
                    case HOTBAR:
                        return player.inventory.mainInventory.get(slot);
                    case OFFHAND:
                        return player.inventory.offHandInventory.get(slot);
                    default:
                        break;
                }
                break;
            case BAUBLES:
                IBaublesItemHandler b=BaublesApi.getBaublesHandler(player);
                return b==null?ItemStack.EMPTY:b.getStackInSlot(slot);
            case ENDER:
                return player.getInventoryEnderChest().getStackInSlot(slot);
            default:
                break;
        }
        return ItemStack.EMPTY;
    }

    public void setItem(ItemStack stack){
        switch(inventory){
            case MAIN:
                switch (subInventory){
                    case NONE:
                        player.inventory.mainInventory.set(slot+9,stack);
                        break;
                    case ARMOR:
                        player.inventory.armorInventory.set(slot,stack);
                        break;
                    case HOTBAR:
                        player.inventory.mainInventory.set(slot,stack);
                        break;
                    case OFFHAND:
                        player.inventory.offHandInventory.set(slot,stack);
                        break;
                    default:
                        break;
                }
                break;
            case BAUBLES:
                IBaublesItemHandler b=BaublesApi.getBaublesHandler(player);
                if(b!=null)
                    b.setStackInSlot(slot,stack);
                break;
            case ENDER:
                player.getInventoryEnderChest().setInventorySlotContents(slot,stack);
                break;
            default:
                break;
        }
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public EnumInventory getInventory() {
        return inventory;
    }

    public EnumSubInventory getSubInventory() {
        return subInventory;
    }

    public int getSlot() {
        return slot;
    }

    public boolean isCommonChargable(){
        return getInventory() == EnumInventory.GROUND || getInventory() == EnumInventory.BAUBLES || (getInventory() == EnumInventory.MAIN && getSubInventory() != EnumSubInventory.NONE && getSubInventory()!= EnumSubInventory.ARMOR);
    }

    public boolean isCommonActive(){
        return getInventory() == EnumInventory.BAUBLES || (getInventory() == EnumInventory.MAIN && getSubInventory() != EnumSubInventory.NONE && getSubInventory()!= EnumSubInventory.ARMOR);
    }
}
