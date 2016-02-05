package convenientadditions.api;

import net.minecraft.item.ItemStack;

public interface IChargable {
	public int getCharge(ItemStack item);
	public int getChargeCapacity(ItemStack item);
	public boolean isChargable(ItemStack item);
	public int chargeItem(ItemStack item, int amount);
}
