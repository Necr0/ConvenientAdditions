package convenientadditions.api.item.charge;

import convenientadditions.api.inventory.SlotNotation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public interface ISunlightChargeable extends IChargeable {
    int getSunlightChargeRate(ItemStack item, SlotNotation slot, @Nullable EntityPlayer player);

    boolean isSunlightChargeable(ItemStack item, SlotNotation slot, @Nullable EntityPlayer player);

    boolean canApplyDrain(ItemStack item);
}
