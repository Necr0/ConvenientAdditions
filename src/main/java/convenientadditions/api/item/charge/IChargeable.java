package convenientadditions.api.item.charge;

import net.minecraft.item.ItemStack;

public interface IChargeable {
    public int getCharge(ItemStack item);

    public int getChargeCapacity(ItemStack item);

    public boolean isChargeable(ItemStack item);

    public int chargeItem(ItemStack item, int amount);

    public int dischargeItem(ItemStack item, int amount);

    public void setItemCharge(ItemStack item, int amount);

    public boolean canApplyCapacity(ItemStack item);

    public boolean canApplyChargeEfficiency(ItemStack item);
}
