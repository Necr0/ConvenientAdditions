package convenientadditions.api.item.charge;

import net.minecraft.item.ItemStack;

public interface IChargeable {
    int getCharge(ItemStack item);

    int getChargeCapacity(ItemStack item);

    boolean isChargeable(ItemStack item);

    int chargeItem(ItemStack item, int amount);

    int dischargeItem(ItemStack item, int amount);

    void setItemCharge(ItemStack item, int amount);

    boolean canApplyCapacity(ItemStack item);

    boolean canApplyChargeEfficiency(ItemStack item);
}
