package convenientadditions.api.item;

import net.minecraft.item.ItemStack;

public interface ISunlightChargeable {
	public int getSunlightChargeRate(ItemStack item,int slot);
	public boolean isSunlightChargeable(ItemStack item,int slot);
	public boolean canApplyDrain(ItemStack item);
}
