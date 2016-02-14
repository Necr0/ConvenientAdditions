package convenientadditions.api.item;

import net.minecraft.item.ItemStack;

public interface ISunlightChargable {
	public int getSunlightChargeRate(ItemStack item,int slot);
	public boolean isSunlightChargable(ItemStack item,int slot);
	public boolean canApplyDrain(ItemStack item);
}
