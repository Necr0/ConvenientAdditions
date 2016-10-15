package convenientadditions.api.item.charge;

import conveniencecore.api.item.IChargeable;
import net.minecraft.item.ItemStack;

public interface ISunlightChargeable extends IChargeable {
	public int getSunlightChargeRate(ItemStack item,int slot);
	public boolean isSunlightChargeable(ItemStack item,int slot);
	public boolean canApplyDrain(ItemStack item);
}
