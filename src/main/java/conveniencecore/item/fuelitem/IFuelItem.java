package conveniencecore.item.fuelitem;

import net.minecraft.item.ItemStack;

public interface IFuelItem {
	public boolean isFuelItem(ItemStack item);
	public int getFuelTime(ItemStack item);
}
