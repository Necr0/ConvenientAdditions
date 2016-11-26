package convenientadditions.api.item;

import net.minecraft.item.ItemStack;

public interface IFuelItem {
    boolean isFuelItem(ItemStack item);

    int getFuelTime(ItemStack item);
}
