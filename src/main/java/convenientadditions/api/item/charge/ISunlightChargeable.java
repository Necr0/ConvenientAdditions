package convenientadditions.api.item.charge;

import convenientadditions.api.inventory.SlotNotation;
import net.minecraft.item.ItemStack;

public interface ISunlightChargeable extends IChargeable {
    int getSunlightChargeRate(ItemStack item, SlotNotation slot);

    boolean isSunlightChargeable(ItemStack item, SlotNotation slot);

    boolean canApplyDrain(ItemStack item);
}
