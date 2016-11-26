package convenientadditions.api.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.NonNullList;

public class InventoryIterator {
    public static Iterable<SlotNotation> getIterable(EntityPlayer player, EnumInventory... inventories){
        NonNullList<SlotNotation> l=NonNullList.create();
        for(EnumInventory i:inventories){
            switch(i){
                case MAIN:
                    for(int s=0;s<9;s++){
                        l.add(new SlotNotation(player, i, EnumSubInventory.HOTBAR, s));
                    }
                    for(int s=0;s<27;s++){
                        l.add(new SlotNotation(player, i, EnumSubInventory.NONE, s));
                    }
                    for(int s=0;s<4;s++){
                        l.add(new SlotNotation(player, i, EnumSubInventory.ARMOR, s));
                    }
                    for(int s=0;s<1;s++){
                        l.add(new SlotNotation(player, i, EnumSubInventory.OFFHAND, s));
                    }
                    break;
                case BAUBLES:
                    for(int s=0;s<7;s++){
                        l.add(new SlotNotation(player, i, EnumSubInventory.NONE, s));
                    }
                    break;
                case ENDER:
                    for(int s=0;s<27;s++){
                        l.add(new SlotNotation(player, i, EnumSubInventory.NONE, s));
                    }
                    break;
                default:
                    break;
            }
        }
        return l;
    }
}
